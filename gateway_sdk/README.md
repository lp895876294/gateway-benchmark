# 网关SDK

## 技术点

- apache httpclient
- apache PoolingHttpClientConnection
- java ExecutorService



## apache httpclient

> HttpClient功能丰富的支持HTTP协议的客户端编程工具包 , 相对于传统JDK自带的URLConnection，增加了易用性和灵活性 ，它不仅是客户端发送Http请求变得容易，而且也方便了开发人员测试接口

常用配置

| 参数名                                       | 说明 | 默认值 |
| -------------------------------------------- | ---- | ------ |
| readTimeoutMillis                            |      |        |
| writeTimeoutMillis                           |      |        |
| connectionTimeoutMillis（maxIdleTimeMillis） |      |        |





## apache PoolingHttpClientConnection

> http 请求连接池，

常用配置



maxTotal
defaultMaxPerRoute;

