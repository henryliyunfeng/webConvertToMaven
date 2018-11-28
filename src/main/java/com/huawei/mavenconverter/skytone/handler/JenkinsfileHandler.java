package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * Jenkinsfile文件处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class JenkinsfileHandler implements IMavenProjectHandler
{
    /**
     * Jenkinsfile文件
     */
    private final static String JENKINSFILE_PATH = "Jenkinsfile";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        // 根据原项目判断是否存在Jenkinsfile文件，如果不存在，则不需要复制
        File oldJenkinsfile = new File(oldProject.getAbsoluteFile() + File.separator + JENKINSFILE_PATH);
        if (oldJenkinsfile.exists())
        {
            return true;
        }
        return false;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // 不需要创建，直接复制
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目Jenkinsfile文件
        File mavenJenkinsfile = new File(mavenProjectPath.getAbsolutePath() + File.separator + JENKINSFILE_PATH);

        // 拷贝Jenkinsfile文件
        File oldJenkinsfile = new File(oldProject.getAbsolutePath() + File.separator + JENKINSFILE_PATH);
        FileUtils.copyFile(oldJenkinsfile, mavenJenkinsfile);
    }

}
