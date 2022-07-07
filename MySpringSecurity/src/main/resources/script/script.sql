# 建库
create database if not exists springsecurity;
use springsecurity;
# 建表
create table users
(
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);
create table authorities
(
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

# JPA实现用户及角色信息落地需要的建库建表脚本 (建表由后台自动实现 无需执行脚本)
create database if not exists springsecurityJPA;


# 持久化token解决RememberMe引发的安全问题需要的建库建表脚本
create database if not exists springsecurityPersistentToken;
use springsecurityPersistentToken;
CREATE TABLE `persistent_logins_self_created` (
    `username` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
    `series` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
    `token` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
    `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# 整合mybatis作为持久层框架需要的建库建表脚本

create database if not exists springsecurityMybatis;
use springsecurityMybatis;

create table if not exists client(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `phone`varchar(255) DEFAULT NULL,
    `telephone`varchar(255) DEFAULT NULL,
    `address`varchar(255) DEFAULT NULL,
    `userface`varchar(255) DEFAULT NULL,
    `remarks`varchar(255) DEFAULT NULL,
    `account_non_expired` bit(1) NOT NULL DEFAULT 1,
    `account_non_locked` bit(1) NOT NULL DEFAULT 1,
    `credentials_non_expired` bit(1) NOT NULL DEFAULT 1,
    `enable` bit(1) NOT NULL DEFAULT 1,
    `password` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
create table if not exists cos(
   `id` bigint NOT NULL AUTO_INCREMENT,
   `name` varchar(255) DEFAULT NULL,
   `name_zh` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table if not exists client_cos_relation(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `client_id` bigint NOT NULL,
    `cos_id` bigint NOT NULL ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- 插入数据
-- 用户优先级/权限 由大到小 (super > admin > user > guest)
insert into client_cos_relation (id,client_id,cos_id) values
 (1,1,1),(2,1,2),(3,1,3),(4,1,4)
,(5,2,2),(6,2,3),(7,2,4)
,(8,3,3),(9,3,4)
,(10,4,4);
insert into client
    (name, phone, telephone, address, userface, remarks, account_non_expired, account_non_locked, credentials_non_expired, enable, password, username)
    values
     ('Benedict','123','020-123','Manchester','face1','haha',1,1,1,1,'111','Benedict')
    ,('本尼迪克特','123','021-123','曼彻斯特','face2','hehe',1,1,1,1,'111','本尼迪克特')
    ,('卷福','123','027-123','曼城','face3','呵呵',1,1,1,1,'111','卷福');
insert into cos (id, name, name_zh) VALUES (1, 'super','超级管理员'),(2,'admin','管理员'),(3,'user','普通用户'),(4,'guest','访客');
