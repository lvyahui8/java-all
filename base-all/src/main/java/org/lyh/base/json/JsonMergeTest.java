package org.lyh.base.json;

import com.google.gson.*;
import lombok.Data;
import org.apache.tools.ant.util.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2019/5/2 4:55
 */
public class JsonMergeTest {
    @Data
    public static class DisplaySetting {
        String backgroundColor = "#00ff00";
        Integer componentWidth = 100;
        String noticeUser ;
    }

    @Data
    public static class MenuItem {
        String title;
        String actionUrl;
    }

    @Data
    public static class RootConfig {
        DisplaySetting displaySetting = new DisplaySetting();
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        JsonObject ext = new JsonObject();
    }

    private static RootConfig localConfig = new RootConfig();

    public static void main(String[] args) throws Exception {
        Consumer<File> consumer = new Consumer<File>() {
            @Override
            public void accept(File file) {
                try {
                    String jsonData = FileUtils.readFully(new FileReader(file));
                    onRecived(jsonData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        watchFileModified(consumer);
    }

    private static void onRecived(String jsonData) throws Exception {
        Gson gson = new GsonBuilder().serializeNulls().create();
        /* 实际上这行代码已经将json合并了默认值 */
        RootConfig rootConfig = gson.fromJson(jsonData, RootConfig.class);
        localConfig = merge(rootConfig,localConfig);
        System.out.println(gson.toJson(localConfig));
    }

    private static RootConfig merge(RootConfig sourceObject, RootConfig targetObject) throws Exception {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonElement sourceElement = jsonParser.parse(gson.toJson(sourceObject));
        JsonElement targetElement = jsonParser.parse(gson.toJson(targetObject));
        mergeJson(sourceElement,targetElement);
        return gson.fromJson(targetElement,RootConfig.class);
    }

    private static void mergeJson(JsonElement sourceElement, JsonElement targetElement) {
        if(! targetElement.isJsonObject() || ! sourceElement.isJsonObject()){
            return;
        }
        JsonObject sourceObject = (JsonObject) sourceElement;
        JsonObject targetObject = (JsonObject) targetElement;

        for (Map.Entry<String, JsonElement> entry : targetObject.entrySet()) {
            JsonElement sourceMember = sourceObject.get(entry.getKey());
            JsonElement targetMember = entry.getValue();
            if(sourceMember == null) {
                /* 如果 sourceMember 为null, 说明json文件中没有配置这个字段 , 并且代码默认值也为null */
                targetObject.add(entry.getKey(),null);
                continue;
            }
            if(sourceMember.isJsonPrimitive() || sourceMember.isJsonArray() || sourceMember.isJsonNull() ) {
                targetObject.add(entry.getKey(),sourceMember);
            } else if(sourceMember.isJsonObject()) {
                mergeJson(sourceMember,targetMember);
            }
        }

        for (Map.Entry<String, JsonElement> entry : sourceObject.entrySet()) {
            if(! targetObject.has(entry.getKey())) {
                targetObject.add(entry.getKey(),entry.getValue());
            }
        }
    }

    private static void watchFileModified(Consumer<File> consumer) throws IOException, InterruptedException {
        final Path path = FileSystems.getDefault().getPath( new File("").getAbsolutePath(), "data");
        try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
            final WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                final WatchKey wk = watchService.take();
                for (WatchEvent<?> event : wk.pollEvents()) {
                    //we only register "ENTRY_MODIFY" so the context is always a Path.
                    final Path changed = (Path) event.context();
                    if (changed.endsWith("config.json")) {
                        consumer.accept(new File(path.toAbsolutePath().toString(),changed.toString()));
                    }
                }
                // reset the key
                boolean valid = wk.reset();
                if (!valid) {
                    System.out.println("Key has been unregisterede");
                }
            }
        }
    }
}
