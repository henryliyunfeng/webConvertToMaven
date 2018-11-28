package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * 测试源码处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class TestSourceHandler implements IMavenProjectHandler
{

    /**
     * 老项目test目录
     */
    private final static String OLD_TEST_PATH = "test";

    /**
     * maven项目test目录
     */
    private final static String MAVEN_TEST_PATH = "src/test/java";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        // 根据原项目判断是否存在test目录，如果不存在，则不需要复制
        File oldTestDir = new File(oldProject.getAbsoluteFile() + File.separator + OLD_TEST_PATH);
        if (oldTestDir.exists())
        {
            return true;
        }
        return false;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // src/test/java
        File testDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + MAVEN_TEST_PATH);
        if (!testDir.exists())
        {
            testDir.mkdirs();
        }
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目测试源文件目录
        File mavenTestDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + MAVEN_TEST_PATH);
        
        // 拷贝测试文件
        File oldTestDir = new File(oldProject.getAbsolutePath() + File.separator + OLD_TEST_PATH);
        FileUtils.copyDirectory(oldTestDir, mavenTestDir);
    }

}
