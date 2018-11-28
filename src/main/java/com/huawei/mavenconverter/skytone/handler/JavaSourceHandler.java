package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * Java源码处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class JavaSourceHandler implements IMavenProjectHandler
{

    /**
     * 老项目源码文件
     */
    private final static String OLD_JAVA_PATH = "src";

    /**
     * maven项目源码文件
     */
    private final static String MAVEN_JAVA_PATH = "src/main/java";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        return true;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // src/main/java
        File javaDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + MAVEN_JAVA_PATH);
        if (!javaDir.exists())
        {
            javaDir.mkdirs();
        }
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目java源文件目录
        File mavenJavaDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + MAVEN_JAVA_PATH);
        // 拷贝源文件
        File oldJavaDir = new File(oldProject.getAbsolutePath() + File.separator + OLD_JAVA_PATH);
        // 只接受com的目录
        FileUtils.copyDirectory(oldJavaDir, mavenJavaDir, new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if (pathname.getAbsolutePath().contains("com"))
                {
                    return true;
                }
                return false;
            }
        });
    }

}
