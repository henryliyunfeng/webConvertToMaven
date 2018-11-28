package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * git目录处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class GitHandler implements IMavenProjectHandler
{

    /**
     * git目录
     */
    private final static String GIT_PATH = ".git";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        return true;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // .git
        File gitDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + GIT_PATH);
        if (!gitDir.exists())
        {
            gitDir.mkdirs();
        }
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目.git目录
        File mavenGitDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + GIT_PATH);

        // 拷贝.git目录
        File oldGitDir = new File(oldProject.getAbsolutePath() + File.separator + GIT_PATH);
        FileUtils.copyDirectory(oldGitDir, mavenGitDir);
    }

}
