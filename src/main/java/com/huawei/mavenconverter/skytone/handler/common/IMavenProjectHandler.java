package com.huawei.mavenconverter.skytone.handler.common;

import java.io.File;
import java.io.IOException;

public interface IMavenProjectHandler
{

    /**
     * 是否存在某个目录，如果不存在，返回false，如果存在返回true
     * 
     * @param oldProject 老项目
     * @param mavenProjectPath maven项目
     * @return 是否存在
     */
    public boolean isExisted(File oldProject, File mavenProjectPath);

    /**
     * 创建文件或者文件夹
     * 
     * @param oldProject 老项目
     * @param mavenProjectPath maven项目
     */
    public void create(File oldProject, File mavenProjectPath);

    /**
     * 拷贝文件或者文件夹
     * 
     * @param oldProject 老项目
     * @param mavenProjectPath maven项目
     */
    public void copy(File oldProject, File mavenProjectPath) throws IOException;

}
