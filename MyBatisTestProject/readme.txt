针对项目中的一些实现方式，还有其他的一些问题


一、Druid 数据源在启动之后也是没办法直接切换的（inited 为true，不能被重新初始化）
这块如何把inited 设置为false之后再执行呢？

二、切换数据源的时候必须执行restart(init 设置为false) 之后才能执行init，中间如果有请求过来怎么办？
---> 可以在前段尝试加锁处理，让请求排队等待之类的

三、切换到本地数据源的时候报错
2019-08-04 18:34:27.150 [Druid-ConnectionPool-Create-159219383] ERROR com.alibaba.druid.pool.DruidDataSource - create connection error, url: jdbc:mysql://localhost:3307/school?allowMultiQueries=true, errorCode 0, state 01S00
java.sql.SQLException: The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:73)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:76)
	at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:835)
	at com.mysql.cj.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:455)
	at com.mysql.cj.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:240)
	at com.mysql.cj.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:199)
	at com.alibaba.druid.pool.DruidAbstractDataSource.createPhysicalConnection(DruidAbstractDataSource.java:1421)
	at com.alibaba.druid.pool.DruidAbstractDataSource.createPhysicalConnection(DruidAbstractDataSource.java:1477)
	at com.alibaba.druid.pool.DruidDataSource$CreateConnectionThread.run(DruidDataSource.java:1998)
Caused by: com.mysql.cj.exceptions.InvalidConnectionAttributeException: The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
	at sun.reflect.GeneratedConstructorAccessor73.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.mysql.cj.exceptions.ExceptionFactory.createException(ExceptionFactory.java:61)
	at com.mysql.cj.exceptions.ExceptionFactory.createException(ExceptionFactory.java:85)
	at com.mysql.cj.util.TimeUtil.getCanonicalTimezone(TimeUtil.java:132)
	at com.mysql.cj.protocol.a.NativeProtocol.configureTimezone(NativeProtocol.java:2243)
	at com.mysql.cj.protocol.a.NativeProtocol.initServerSession(NativeProtocol.java:2267)
	at com.mysql.cj.jdbc.ConnectionImpl.initializePropsFromServer(ConnectionImpl.java:1319)
	at com.mysql.cj.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:966)
	at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:825)
	... 6 common frames omitted

四、mysql启动的时候有一些warn
2019-08-04T09:46:17.397040Z 0 [Warning] [MY-011068] [Server] The syntax 'expire-logs-days' is deprecated and will be removed in a future release. Please use binlog_expire_logs_seconds instead.
2019-08-04T09:46:17.397065Z 0 [Warning] [MY-000081] [Server] option 'read_buffer_size': unsigned value 0 adjusted to 8192.
2019-08-04T09:46:17.397072Z 0 [Warning] [MY-000081] [Server] option 'read_rnd_buffer_size': unsigned value 0 adjusted to 1.
2019-08-04T09:46:17.397113Z 0 [Warning] [MY-010915] [Server] 'NO_ZERO_DATE', 'NO_ZERO_IN_DATE' and 'ERROR_FOR_DIVISION_BY_ZERO' sql modes should be used with strict mode. They will be merged with strict mode in a future release.
2019-08-04T09:46:17.399768Z 0 [System] [MY-010116] [Server] D:\Program Files\MySQL\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) starting as process 5384
2019-08-04T09:46:17.401418Z 0 [Warning] [MY-013242] [Server] --character-set-server: 'utf8' is currently an alias for the character set UTF8MB3, but will be an alias for UTF8MB4 in a future release. Please consider using UTF8MB4 in order to be unambiguous.
2019-08-04T09:46:20.991698Z 0 [Warning] [MY-010068] [Server] CA certificate ca.pem is self signed.
2019-08-04T09:46:21.031338Z 0 [System] [MY-010931] [Server] D:\Program Files\MySQL\mysql-8.0.17-winx64\bin\mysqld.exe: ready for connections. Version: '8.0.17'  socket: ''  port: 3307  MySQL Community Server - GPL.
2019-08-04T09:46:21.251996Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Bind-address: '::' port: 33060
