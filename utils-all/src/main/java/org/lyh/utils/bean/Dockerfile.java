package org.lyh.utils.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/8/30 21:49
 */
public class Dockerfile {

    private List<DockerService> dockerServices = new ArrayList<>();

    private List<String> mountPaths = new ArrayList<>();

    private String containerName;

    public List<DockerService> getDockerServices() {
        return dockerServices;
    }

    public List<String> getMountPaths() {
        return mountPaths;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }


    public static class  DockerService{
        private String sourcePath;
        private String targetPath;

        public String getTargetPath() {
            return targetPath;
        }

        public void setTargetPath(String targetPath) {
            this.targetPath = targetPath;
        }

        public String getSourcePath() {
            return sourcePath;
        }

        public void setSourcePath(String sourcePath) {
            this.sourcePath = sourcePath;
        }
    }
}
