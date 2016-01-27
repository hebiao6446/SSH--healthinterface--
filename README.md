# SSH--healthinterface--
sql脚本在resources里面

使用说明
启动或部署项目，这个是必须的

主机地址: 192.168.1.111

端口: 8080

项目名称: healthinterface

Action名称: UserAction（User+Action），OtherAction （Other+Action），YourAction （Your+Action）

方法名: loadBootpageInfo




那么访问这个方法的方式是 

IP地址+端口号+项目名称+User!+方法名.action 

IP地址+端口号+项目名称+Other!+方法名.action 

IP地址+端口号+项目名称+Your!+方法名.action 


将字母边小写，为什么？问度娘


http://192.168.1.111:8080/healthinterface/user!loadBootpageInfo.action

http://192.168.1.111:8080/healthinterface/other!loadBootpageInfo.action

http://192.168.1.111:8080/healthinterface/your!loadBootpageInfo.action



后台代码在此

https://github.com/hebiao6446/SSH--healthserver--
