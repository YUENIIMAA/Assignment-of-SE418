# Task III

## Step 1 创建dockerfile

在IDE中右键工程文件新建File，文件名为dockerfile。

登陆hub.docker.com搜索openjdk，在官方版本中发现已经有11的版本。

在dockerfile中添加：

```dockerfile
FROM openjdk:11
```

把build/libs中的jar文件复制到工程根目录重命名为task3.jar。

在dockerfile中添加：

```dockerfile
ADD task3.jar task3.jar
```

前者为文件在工程文件夹中的路径，后者为在Docker中的路径

并选择8080作为对外暴露的端口

```dockerfile
EXPOSE 8080
ENTRYPOINT ["java","-jar","task3.jar"]
```

完成了对dockerfile的编辑。

## Step 2 构建docker image

在工程文件夹中打开终端执行

```terminal
docker build -f dockerfile -t task3 .
```

注意最后那个**.**表示生成文件到相同目录

得到如下输出

```terminal
Step 1/4 : FROM openjdk:11
11: Pulling from library/openjdk
6f2f362378c5: Pull complete 
494c27a8a6b8: Pull complete 
7596bb83081b: Pull complete 
372744b62d49: Pull complete 
fb72767f9beb: Pull complete 
c34cefad6b24: Pull complete 
e2b76c865b34: Pull complete 
Digest: sha256:b510eef40fcde8f60f65a2ed382f2a7e31a95ea1c78bc5abea44cf637e37a188
Status: Downloaded newer image for openjdk:11
 ---> f06fdc42d01a
Step 2/4 : ADD task3.jar task3.jar
 ---> 5dae5677a319
Step 3/4 : EXPOSE 8080
 ---> Running in 5f2dc296046f
Removing intermediate container 5f2dc296046f
 ---> 2b340fca0fda
Step 4/4 : ENTRYPOINT ["java","-jar","task3.jar"]
 ---> Running in 49507d2eed98
Removing intermediate container 49507d2eed98
 ---> d1c1d345e5c7
Successfully built d1c1d345e5c7
Successfully tagged task3:latest
```

验证创建的Docker image是否正确

```terminal
docker run -p 1234:8080 task3
```

其中**-p**参数将task 3这个image内的8080端口映射到了本机的1234端口

通过浏览器访问localhost:1234得到和IDE中直接运行相同的结果。

## Step 3 发布docker image到dockerhub

在push之前需要将发布的镜像改到自己的账户下，我的用户名：yueniimaa

```terminal
docker tag task3 yueniimaa/se418-task3
docker push yueniimaa/se418-task3
```

然后可以在[这里](https://cloud.docker.com/repository/docker/yueniimaa/se418-task3)找到发布完毕的docker image。

