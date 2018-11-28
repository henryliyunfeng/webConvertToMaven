# webConvertToMaven
把原web的项目结构复制为maven的项目结构
<div align=center>![原项目结构](https://github.com/henryliyunfeng/webConvertToMaven/blob/master/%E5%8E%9Fweb%E9%A1%B9%E7%9B%AE%E7%BB%93%E6%9E%84.PNG?raw=true '原项目结构')
## 1.通过程序，把原项目结构修改为maven项目结构，如下：
### 1）把src下面的代码文件拷贝到maven项目src/main/java目录下
### 2）把src下面的META-INF文件夹拷贝到maven项目src/main/resources目录下
### 3）配置文件全部复制到src/main/resources目录下
### 4）test文件全部复制到src/test/java目录下
### 5）WebContent目录下的WEB-INF文件夹和index.jsp文件复制到webapp目录下
### 6）deploy文件夹复制到maven项目的根目录
### 7）CI文件夹复制到maven项目的根目录
### 8）.git目录拷贝到maven项目根目录

## 2.书写pom.xml文件，由中心仓来管理依赖jar包
