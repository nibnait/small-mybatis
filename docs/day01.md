## 问：为什么在使用 Mybatis 的时候，只需定义一个接口，不用写实现类就能使用 XML 中或者注解上配置好的 SQL 语句，就能完成对数据库 CRUD 的操作呢？
答：这里最核心的是用到了接口代理类，把每一个数据库操作的 DAO 接口都用操作数据库的代理类实现，并注册到 Spring 容器让用户去使用。

```sql
CREATE TABLE `user`
(
    `id`      bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `name`    varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
    `age`     int(11)     NOT NULL DEFAULT 0 COMMENT '年龄',
    `gender`  tinyint(4)  NOT NULL DEFAULT 0 COMMENT '性别 0未知 1男 2女',
    `address` varchar(40) NOT NULL DEFAULT '' COMMENT '地址',
    `ctime`   datetime    NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
    `mtime`   datetime    NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `ix_mtime` (`mtime`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO test.user (name, age, gender, address) VALUES ('Tom', 11, 1, '上海')
```

