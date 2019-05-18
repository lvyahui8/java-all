package org.lyh.utils.velocity;

import org.apache.velocity.VelocityContext;
import org.lyh.utils.bean.Dockerfile;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/8/30 21:42
 */
public class DockerfileGenerator extends TemplateGenerator{

    private Dockerfile dockerfile;

    public DockerfileGenerator(String templateFile,Dockerfile dockerfile) {
        super(templateFile);
        this.dockerfile = dockerfile;
    }

    @Override
    public VelocityContext getContext() {
        VelocityContext velocityContext  = new VelocityContext();
        velocityContext.put("dockerServices", dockerfile.getDockerServices());
        velocityContext.put("mountPaths",dockerfile.getMountPaths());
        velocityContext.put("containerName",dockerfile.getContainerName());
        return velocityContext;
    }

    public static void main(String[] args) {
        Dockerfile dockerfile = new Dockerfile();
        String dockerFileTemplate = "templates/Dockerfile.vm";
        dockerfile.setContainerName("simpleDocker");
        dockerfile.getMountPaths().add("/data/log");
        Dockerfile.DockerService dockerService = new Dockerfile.DockerService();
        dockerService.setSourcePath("release/cgi-bin/");
        dockerService.setTargetPath("time_svr");
        dockerfile.getDockerServices().add(dockerService);

        DockerfileGenerator dockerfileGenerator = new DockerfileGenerator(dockerFileTemplate,dockerfile);
        dockerfileGenerator.generateTo("Dockerfile");
    }
}
