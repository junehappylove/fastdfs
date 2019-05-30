# fastdfs

v1.2.5 

赶紧重构一遍，以上代码全部由反编译以及参考网上各种开源获得，源代码同事离职了，因此重写一遍防止后续问题

FastDFS_v5.05

# 概述

FastDFS 是余庆老师开发的一个开源的高性能分布式文件系统（DFS）。 它的主要功能包括：文件存储，文件同步和文件访问，以及高容量和负载平衡。 

FastDFS 系统有三个角色：跟踪服务器(Tracker Server)、存储服务器(Storage Server)和客户端(Client)。

- Tracker Server: 跟踪服务器，主要做调度工作，起到均衡的作用；负责管理所有的storage server和group，每个storage在启动后会连接 Tracker，告知自己所属 group 等信息，并保持周期性心跳。多个Tracker之间是对等关系，不存在单点故障。
- Storage Server: 存储服务器，主要提供容量和备份服务；以 group 为单位，每个 group 内可以有多台 storage server，组内的storage server上的数据互为备份。
- Client:客户端，上传下载数据的服务器 

模块之间的主要关系如下：

![FDFS的三个模块间关系](https://github.com/junehappylove/img_lib/blob/master/fastdfs/fdfs_relation.jpg "FDFS的三个模块间关系")

下图是实现统一的对外下载访问入口的高可用架构，其中所有的Nginx只做下载用途，上传通过tracker进行上传。 

![FDFS的高可用架构](https://github.com/junehappylove/img_lib/blob/master/fastdfs/fdfs_high_availability.jpg "FDFS的高可用架构")


# 集群部署

网上有很多部署方案，具体可以参考：[FastDFS高可用集群架构配置搭建](https://www.cnblogs.com/sunnydou/p/49b92d511047f4f9da6cd727cfd415d5.html)、[FastDFS分布式文件系统集群安装与配置](https://blog.csdn.net/xyang81/article/details/52928230)
