package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * WebContent源码处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class WebContentHandler implements IMavenProjectHandler
{

    /**
     * 老项目WebContent目录
     */
    private final static String OLD_WEBCONTENT_PATH = "WebContent";

    /**
     * maven项目webapp目录
     */
    private final static String MAVEN_WEBAPP_PATH = "src/main/webapp";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        // 根据原项目判断是否存在WebContent目录，如果不存在，则不需要复制
        File oldWebContentDir = new File(oldProject.getAbsoluteFile() + File.separator + OLD_WEBCONTENT_PATH);
        if (oldWebContentDir.exists())
        {
            return true;
        }
        return false;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // src/main/webapp
        File webappDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + MAVEN_WEBAPP_PATH);
        if (!webappDir.exists())
        {
            webappDir.mkdirs();
        }
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目webapp目录
        File mavenWebappDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + MAVEN_WEBAPP_PATH);
        
        // 拷贝webcontent下的文件
        File oldWebContentDir = new File(oldProject.getAbsolutePath() + File.separator + OLD_WEBCONTENT_PATH);
        // 排除META-INF目录
        FileUtils.copyDirectory(oldWebContentDir, mavenWebappDir, new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if (pathname.getAbsolutePath().contains("META-INF"))
                {
                    return false;
                }
                return true;
            }
        });
    }

}
