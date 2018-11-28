package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * 资源文件处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class ResourcesHandler implements IMavenProjectHandler
{

    /**
     * 老项目资源文件config
     */
    private final static String OLD_RESOURCE_PATH_1 = "config";

    /**
     * 老项目资源文件src目录下
     */
    private final static String OLD_RESOURCE_PATH_2 = "src";

    /**
     * maven项目资源文件目录
     */
    private final static String MAVEN_RESOURCES_PATH = "src/main/resources";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        return true;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // src/main/resources
        File resourcesDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + MAVEN_RESOURCES_PATH);
        if (!resourcesDir.exists())
        {
            resourcesDir.mkdirs();
        }
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目资源目录
        File mavenResourcesDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + MAVEN_RESOURCES_PATH);

        // 把老项目下config目录拷贝到maven项目
        File oldJavaConfigDir = new File(oldProject.getAbsolutePath() + File.separator + OLD_RESOURCE_PATH_1);
        if (oldJavaConfigDir.exists())
        {
            FileUtils.copyDirectory(oldJavaConfigDir, mavenResourcesDir);
        }

        // 把老项目下src目录中（除com目录）复制到maven项目
        File oldJavaSrcDir = new File(oldProject.getAbsolutePath() + File.separator + OLD_RESOURCE_PATH_2);
        // 只接受非com的目录
        FileUtils.copyDirectory(oldJavaSrcDir, mavenResourcesDir, new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if (pathname.isDirectory() && pathname.getAbsolutePath().contains("com"))
                {
                    return false;
                }
                return true;
            }
        });
    }

}
