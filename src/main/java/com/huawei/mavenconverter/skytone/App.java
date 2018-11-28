package com.huawei.mavenconverter.skytone;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.huawei.mavenconverter.skytone.handler.common.AnnotationMavenProjectHandler;
import com.huawei.mavenconverter.skytone.handler.common.IMavenProjectHandler;
import com.huawei.mavenconverter.skytone.utils.ClassUtils;

public class App
{

    /**
     * maven项目目录
     */
    private static final String NEW_PROJECT_PATH = "newproject";

    /**
     * 项目处理类列表
     */
    private static List<IMavenProjectHandler> mavenProjectHandlers;

    public static void main(String[] args) throws Exception
    {
        // 扫描所有AnnotationMavenProjectHandler注解的类
        mavenProjectHandlers = getClassesByPackageName("com.huawei.mavenconverter.skytone.handler");

         copy("E:\\project\\VCS-MAVEN\\Config");
//        copyBatch("E:\\project\\VCS-MAVEN");
    }

    /**
     * 拷贝单个工程
     * 
     * @param filePath 工程路径
     */
    public static void copy(String filePath) throws Exception
    {
        // 老项目路径
        File oldProject = new File(filePath);
        // 项目名称
        String fileName = oldProject.getName();
        // 项目路径
        String oldParentPath = oldProject.getParent();

        // 创建新项目文件夹
        File mavenProjectPath = new File(oldParentPath + File.separator + NEW_PROJECT_PATH + File.separator + fileName);
        if (!mavenProjectPath.exists())
        {
            mavenProjectPath.mkdirs();
        }

        // 处理项目拷贝业务逻辑
        handlerProject(oldProject, mavenProjectPath);
    }

    /**
     * 拷贝单个工程
     * 
     * @param filePath 工程路径
     */
    public static void copy(File oldProject) throws Exception
    {
        // 项目名称
        String fileName = oldProject.getName();
        // 项目路径
        String oldParentPath = oldProject.getParent();

        // 创建新项目文件夹
        File mavenProjectPath = new File(oldParentPath + File.separator + NEW_PROJECT_PATH + File.separator + fileName);
        if (!mavenProjectPath.exists())
        {
            mavenProjectPath.mkdirs();
        }

        // 处理项目拷贝业务逻辑
        handlerProject(oldProject, mavenProjectPath);
    }

    /**
     * 批量拷贝工程
     * 
     * @param dirPath 工程主目录
     */
    public static void copyBatch(String dirPath) throws Exception
    {
        File dir = new File(dirPath);
        if (dir.exists())
        {
            // 获取目录下所有目录，去除NEW_PROJECT_PATH的目录
            File[] oldProjects = dir.listFiles(new FileFilter()
            {
                @Override
                public boolean accept(File pathname)
                {
                    if (pathname.isDirectory())
                    {
                        if (pathname.getAbsolutePath().endsWith(NEW_PROJECT_PATH))
                        {
                            return false;
                        }
                    }
                    return true;
                }
            });

            // 遍历所有老项目
            if (oldProjects != null && oldProjects.length != 0)
            {
                for (File oldProject : oldProjects)
                {
                    copy(oldProject);
                }
            }
        }
    }

    /**
     * 处理项目拷贝业务逻辑
     * 
     * @param oldProject 老项目
     * @param mavenProjectPath maven项目
     */
    public static void handlerProject(File oldProject, File mavenProjectPath) throws Exception
    {
        if (mavenProjectHandlers != null && mavenProjectHandlers.size() != 0)
        {
            // 循环执行处理方法
            for (IMavenProjectHandler mavenProjectHandler : mavenProjectHandlers)
            {
                if (mavenProjectHandler.isExisted(oldProject, mavenProjectPath))
                {
                    mavenProjectHandler.create(oldProject, mavenProjectPath);
                    mavenProjectHandler.copy(oldProject, mavenProjectPath);
                }
            }
        }
    }

    /**
     * 获取包下所有的class类，并且根据注解，实例化该类
     * 
     * @param packageName 包名
     * @return IMavenProjectHandler实例
     */
    public static List<IMavenProjectHandler> getClassesByPackageName(String packageName) throws Exception
    {
        Set<Class<?>> classes = ClassUtils.getClasses(packageName);

        if (classes != null && classes.size() != 0)
        {
            // 遍历class
            List<IMavenProjectHandler> mavenProjectHandlers = new ArrayList<>();
            for (Class<?> clazz : classes)
            {
                // 获取注解
                AnnotationMavenProjectHandler annotationMavenProjectHandler = clazz
                        .getAnnotation(AnnotationMavenProjectHandler.class);
                // 如果注解不为空
                if (annotationMavenProjectHandler != null)
                {
                    // 实例化该类
                    IMavenProjectHandler mavenProjectHandler = (IMavenProjectHandler) clazz.newInstance();
                    mavenProjectHandlers.add(mavenProjectHandler);
                }
            }
            return mavenProjectHandlers;
        }

        return null;
    }

}
