# Homework III

将原先的服务中负责编码的模块拆了出去，

于是一共有了三个工程文件。

**Eureka-Server：** 服务的注册中心

**Login-Server：** 登陆的前后端

**Service-Server：** 提供哈夫曼编码的API

Dockerfile在工程文件根目录均可找到，具体内容可在Task3中找到。

docker image可通过在dockerhub查找以下内容获得：

yueniimaa/se418-hw3-login-server

yueniimaa/se418-hw3-service-server

yueniimaa/eureka-server

## 注意

请按照Eureka-Server，Service-Server，Login-Server的顺序启动。

另外如果直接跑docker image可能会跪，

建议修改application.properties添加eureka.client.service-url.defaultZone。