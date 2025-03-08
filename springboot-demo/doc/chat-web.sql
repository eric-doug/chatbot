create table `chat-web`.t_question_group
(
    id          bigint auto_increment comment '问题组id'
        primary key,
    user_id     bigint              null comment '归属用户id',
    group_name  varchar(500)        null comment '问题组标题',
    is_del      char(2) default '0' null comment '是否删除 0否 1是',
    create_time datetime            null comment '创建时间',
    update_time datetime            null comment '修改时间'
)
    comment '【问题】群组表' collate = utf8mb4_general_ci;

create table `chat-web`.t_question_info
(
    id          bigint auto_increment
        primary key,
    user_id     bigint       null comment '用户id',
    token       int          null comment '消耗token值',
    group_id    bigint       null comment '问题组id',
    question    varchar(500) null comment '问题',
    answer      text         null comment '答案',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '更新时间',
    status      tinyint      null comment '是否删除 0否 1是'
)
    comment '【问题】问题信息表' collate = utf8mb4_general_ci;

create table `chat-web`.t_user_account
(
    user_id      bigint auto_increment comment '主键id'
        primary key,
    user_account varchar(15) null comment '用户帐号',
    email        varchar(32) null comment '登录邮箱',
    login_pwd    varchar(32) null comment '登录密码    md5（sha()）',
    trade_pwd    varchar(32) null comment '交易密码',
    pwd_salt     varchar(32) null comment '密码盐',
    is_enable    tinyint     null comment '是否禁止登录 0=不可用 1=可用 默认1',
    constraint useraccount_index
        unique (user_account)
)
    comment '【用户】账户表' collate = utf8mb4_general_ci;

create index emaill_index
    on `chat-web`.t_user_account (email);

create table `chat-web`.t_user_info
(
    user_id      bigint  default 0               not null comment '用户id  取帐号表id'
        primary key,
    user_account varchar(15)                     null comment '用户帐号',
    user_name    varchar(32)                     null comment '昵称',
    avatar       varchar(150)                    null comment '用户头像',
    link_phone   varchar(15)                     null comment '联系手机',
    email        varchar(32)                     null comment '邮箱',
    sex          tinyint default 1               null comment '性别： 1、男 2、女',
    real_name    varchar(20) collate utf8mb4_bin null comment '真实姓名',
    status       tinyint default 1               null comment '用户状态 1、正常 2、禁止',
    create_date  datetime                        null comment '注册时间',
    update_time  datetime                        null comment '更新时间',
    constraint account_index
        unique (user_account)
)
    comment '【用户】信息表' collate = utf8mb4_general_ci;

create index email_index
    on `chat-web`.t_user_info (email);

