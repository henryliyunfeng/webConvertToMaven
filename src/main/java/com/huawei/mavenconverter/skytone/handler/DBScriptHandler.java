package com.huawei.mavenconverter.skytone.handler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;

/**
 * sql脚本处理
 * 
 * @author q00430065
 * @version [maven-converter 0.1， 2018年11月27日]
 */
@AnnotationMavenProjectHandler
public class DBScriptHandler implements IMavenProjectHandler
{

    /**
     * dbscript目录
     */
    private final static String DBSCRIPT_PATH = "dbscript";

    @Override
    public boolean isExisted(File oldProject, File mavenProjectPath)
    {
        // 根据原项目判断是否存在dbscript目录，如果不存在，则不需要复制
        File oldDBScriptDir = new File(oldProject.getAbsoluteFile() + File.separator + DBSCRIPT_PATH);
        if (oldDBScriptDir.exists())
        {
            return true;
        }
        return false;
    }

    @Override
    public void create(File oldProject, File mavenProjectPath)
    {
        // dbscript
        File dbscriptDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + DBSCRIPT_PATH);
        if (!dbscriptDir.exists())
        {
            dbscriptDir.mkdirs();
        }
    }

    @Override
    public void copy(File oldProject, File mavenProjectPath) throws IOException
    {
        // maven项目sql脚本目录
        File mavenDBScriptDir = new File(mavenProjectPath.getAbsolutePath() + File.separator + DBSCRIPT_PATH);

        // 拷贝sql脚本目录
        File oldDBScriptDir = new File(oldProject.getAbsolutePath() + File.separator + DBSCRIPT_PATH);
        FileUtils.copyDirectory(oldDBScriptDir, mavenDBScriptDir);
    }

}
