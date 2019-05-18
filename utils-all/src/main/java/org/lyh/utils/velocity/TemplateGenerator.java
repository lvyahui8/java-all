package org.lyh.utils.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/8/30 21:42
 */
abstract public class TemplateGenerator {

    private String templateFile;

    private Template template;

    private VelocityEngine velocityEngine;

    public TemplateGenerator(String templateFile) {
        this.templateFile = templateFile;
        template = getEngine().getTemplate(templateFile,"UTF-8");
    }

    public VelocityEngine getEngine(){
        if(velocityEngine == null){
            velocityEngine= new VelocityEngine();
            velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        }
        return velocityEngine;
    }


    public String getString(){
        StringWriter stringWriter = new StringWriter();
        template.merge(getContext(), stringWriter);
        return stringWriter.toString();
    }

    public void generateTo(String targetFileName){
        try {
            FileWriter fileWriter = new FileWriter(targetFileName);
            template.merge(getContext(), fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract public VelocityContext getContext();

}
