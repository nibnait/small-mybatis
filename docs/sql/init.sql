CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                        `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
                        `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
                        `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别 0未知 1男 2女',
                        `address` varchar(40) NOT NULL DEFAULT '0' COMMENT '地址',
                        `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        KEY `ix_mtime` (`mtime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8

INSERT INTO test.user (id, name, age, gender, address) VALUES (1, 'Tom', 11, 1, '上海');
