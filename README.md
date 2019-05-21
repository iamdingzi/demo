# EuEast/Pandu backend engineer test.

### subject
create a RESTful API web-app that persists entities in an H2 in-memory DB

### Features
 * 使用aop完成日志打印，不需要在每个方法中都写日志记录的代码。
 * 使用统一异常管理，专注业务逻辑的实现。
 * 使用Hibernate Validator验证属性，验证逻辑与业务逻辑之间进行了分离，降低了程序耦合度，统一且规范的验证方式，无需编写重复的验证代码。
 * 使用mybatis-plus操作数据库相等灵活，在此基础上非常容易的实现了乐观锁，以及非常方便的实体类枚举属性映射数据库字段，无需在手动多次转换。
### Getting started
```
mvn test -Dtest=ServerTests
``` 
```
mvn spring-boot:run
``` 

### directory structure

```src
       ├─main
       │  ├─java
       │  │  └─com
       │  │      └─example
       │  │          └─demo
       │  │              ├─common
       │  │              │  ├─config
       │  │              │  ├─constant
       │  │              │  └─exception
       │  │              ├─controller
       │  │              ├─entity
       │  │              ├─mapper
       │  │              └─service
       │  │                  └─impl
       │  └─resources
       │      ├─db
       │      ├─mybatis.mapper
       │      ├─static
       │      └─templates
       └─test
           └─java
               └─com
                   └─example
                       └─demo
                           ├─controller
                           └─mapper  
```
