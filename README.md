## 注册中心nacos

​	[下载地址](https://github.com/alibaba/nacos/releases)

​	 [配置中心](https://nacos.io/zh-cn/docs/deployment.html)

​	  防火墙配置

- firewall-cmd --state //查看运行状态 
- firewall-cmd --add-port=8848/tcp --permanent// 开放1024的端口
- firewall-cmd --reload // 重载生效刚才的端口设置	
-  firewall-cmd --list-ports --permanent  //查看所有永久开放的端口 	