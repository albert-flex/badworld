drop table if exists `user`;
create table `user`(
  `id`  bigint not null auto_increment,
  `user_name` varchar(40) not null unique,
  `password`  varchar(40) not null,
  `create_time` datetime not null default now(),
  `last_login` datetime,
  `state` int not null default 1,
  `enabled` boolean not null default TRUE,
  primary key(`id`)
);

drop table if exists `authority`;
create table `authority`(
    `id` bigint not null auto_increment,
    `name` varchar(40) not null,
    `detail` varchar(256) not null,
    primary key(`id`)
);

drop table if exists `user_authority`;
create table `user_authority`(
    `id` bigint not null auto_increment,
    `user_id` bigint not null,
    `authority_id` bigint not null,
    primary key(`id`)
);

drop table if exists `announce`;
create table `announce`(
    `id` bigint not null auto_increment,
    `user_id` bigint not null,
    `title` varchar(40) not null default 'No-Title',
    `publish_time` datetime not null default now(),
    `content` varchar(1024) not null default 'None',
    primary key(`id`)
);

drop table if exists `board_word`;
create table `board_word`(
    `id` bigint not null auto_increment,
    `email` varchar(40) not null,
    `content` varchar,
    `left_time` datetime not null default now(),
    primary key(`id`)
);

drop table if exists `profile`;
create table `profile`(
   `user_id` bigint not null unique,
   `email` varchar(40) not null,
   `phone` varchar(20),
   `address` varchar(40),
   `website` varchar(40),
   `work`   varchar(40),
   `company` varchar(40)
);

drop table if exists `post`;
create table `post`(
    `id` bigint not null auto_increment,
    `user_id` bigint not null,
    `title` varchar(256) not null default 'no-title',
    `create_time` datetime not null default now(),
    `content` varchar(1024) not null,
    `reply_count` int not null default 0,
    `reply_post_id` bigint,
    primary key(`id`)
);

drop table if exists `artifact_type`;
create table `artifact_type`(
    `id` bigint not null auto_increment,
    `name` varchar(40) not null unique,
    `create_time` datetime not null default now(),
    `user_id` bigint not null,
    primary key(`id`)
);

drop table if exists`artifact`;
create table `artifact`(
    `id` bigint not null auto_increment,
    `user_id` bigint not null,
    `type_id` bigint not null,
    `name` varchar(40) not null unique,
    `version` varchar(40) not null,
    `create_time` datetime not null default now(),
    `update_time` datetime not null default now(),
    `title` varchar(40) not null,
    `content` varchar(1024) not null,
    primary key(`id`)
);

drop table if exists `file_resource`;
create table `file_resource`(
    `id` bigint not null auto_increment,
    `name` varchar(256) not null,
    `format` varchar(256) not null,
    `upload_time` datetime not null default now(),
    primary key(`id`)
);