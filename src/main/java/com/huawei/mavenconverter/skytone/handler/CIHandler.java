package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * CI目录处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class CIHandler implements IMavenProjectHandler
{

    /**
     * CI目录
     */
    private final static String CI_PATH = "CI";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        // 根据原项目判断是否存在CI目录，如果不存在，则不需要复制
        File oldCIDir = new File(oldProject.getAbsoluteFile() + File.separator + CI_PATH);
        if (oldCIDir.exists())
        {
            return true;
        }
        return false;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // CI
        File ciDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + CI_PATH);
        if (!ciDir.exists())
        {
            ciDir.mkdirs();
        }
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目CI目录
        File mavenCIDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + CI_PATH);

        // 拷贝CI目录
        File oldCIDir = new File(oldProject.getAbsolutePath() + File.separator + CI_PATH);
        FileUtils.copyDirectory(oldCIDir, mavenCIDir);
    }

}
