package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * deploy目录处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class DeployHandler implements IMavenProjectHandler
{

    /**
     * deploy目录
     */
    private final static String DEPLOY_PATH = "deploy";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        // 根据原项目判断是否存在deploy目录，如果不存在，则不需要复制
        File oldDeployDir = new File(oldProject.getAbsoluteFile() + File.separator + DEPLOY_PATH);
        if (oldDeployDir.exists())
        {
            return true;
        }
        return false;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // deploy
        File deployDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + DEPLOY_PATH);
        if (!deployDir.exists())
        {
            deployDir.mkdirs();
        }
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目deploy目录
        File mavenDeployDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + DEPLOY_PATH);
        
        // 拷贝deploy目录
        File oldDeployDir = new File(oldProject.getAbsolutePath() + File.separator + DEPLOY_PATH);
        FileUtils.copyDirectory(oldDeployDir, mavenDeployDir);
    }

}
