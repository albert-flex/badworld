delete from `user`;
insert into `user`(`id`,`user_name`,`password`) values
(100,'albert','moeyui0624'),
(101,'albert flex','moeyui0625'),
(102,'kakarote','moeyui0624'),
(103,'kafka','kafka');

delete from `authority`;
insert into `authority` (`id`,`name`,`detail`) values
(1,'ROLE_USER','普通用户'),
(2,'ROLE_ADMIN','管理员');

delete from `user_authority`;
insert into `user_authority` (`id`,`user_id`,`authority_id`) values
(1,100,2),
(2,101,1),
(3,102,1),
(4,103,1),
(5,100,1);

delete from `profile`;
insert into `profile` (`user_id`,`email`) values
(100,'albert@qq.com'),
(101,'albert-flex@foxmail.com'),
(102,'kakarote@aaa.com'),
(103,'kafka@k.com');

delete from `post`;
insert into `post` (`id`,`user_id`,`content`) values
(100,100,'我不知道你在说什么'),
(101,100,'Kaka'),
(102,101,'Kaka1'),
(103,102,'Kaka2'),
(104,103,'Kaka3');
insert into `post` (`id`,`user_id`,`content`,`reply_count`) values
(105,100,'Who has PC?',2);
insert into `post` (`id`,`user_id`,`content`,`reply_post_id`) values
(106,101,'I has That!',105),
(107,102,'I has That,too!',105);

delete from `artifact_type`;
delete from `artifact`;
insert into `artifact_type` (`id`,`name`, `user_id`) values
(1,'Novel', 100),
(2,'Game', 100),
(3,'Software', 100),
(4,'Music', 100),
(5,'Manga', 100);
insert into `artifact` (`id`,`user_id`,`type_id`,`name`,`version`,`title`,`content`) values
(1,100,1,'世界史','2022年第一版','纵观世界史','aaaaa'),
(2,100,2,'世界寻宝游戏','1.0','寻宝游戏','aaaaa'),
(3,100,3,'Todos','1.0','待办清单','aaaaa'),
(4,100,4,'呜咽','2022年第一版','呜咽','aaaaa'),
(5,101,5,'樱花变奏曲','2022年第一版','樱花变奏曲','aaaaa'),
(6,100,5,'谋杀者Z','2022年第一版','谋杀者Z','aaaaa');

delete from `announce`;
insert into `announce` (`id`,`user_id`,`title`,`content`) values
(100,100,'Official Website','XXXXXXXX'),
(101,100,'Official Website2','XXXXXXXX2'),
(102,100,'Official Website3','XXXXXXXX3'),
(103,100,'Official Website4','XXXXXXXX4'),
(104,100,'Official Website5','XXXXXXXX2'),
(105,100,'Official Website6','XXXXXXXX3'),
(106,100,'Official Website7','XXXXXXXX4'),
(107,100,'Official Website8','XXXXXXXX2'),
(108,100,'Official Website9','XXXXXXXX3'),
(109,100,'Official Website10','XXXXXXXX4'),
(110,100,'Official Website11','XXXXXXXX2'),
(111,100,'Official Website12','XXXXXXXX3'),
(112,100,'Official Website13','XXXXXXXX4'),
(113,100,'Official Website14','XXXXXXXX5');

delete from `board_word`;
insert into `board_word` (`id`,`email`,`title`,`content`) values
(100,'kaka@qq.com','Official Website','XXXXXXXX'),
(101,'kaka0@qq.com','Official Website2','XXXXXXXX2'),
(102,'kaka1@qq.com','Official Website3','XXXXXXXX3'),
(103,'kaka2@qq.com','Official Website4','XXXXXXXX4'),
(104,'kaka3@qq.com','Official Website4','XXXXXXXX4'),
(105,'kaka4@qq.com','Official Website4','XXXXXXXX4'),
(106,'kaka5@qq.com','Official Website4','XXXXXXXX4'),
(107,'kaka6@qq.com','Official Website4','XXXXXXXX4'),
(108,'kaka7@qq.com','Official Website4','XXXXXXXX4'),
(109,'kaka8@qq.com','Official Website4','XXXXXXXX4'),
(110,'kaka9@qq.com','Official Website4','XXXXXXXX4'),
(111,'kaka10@qq.com','Official Website5','XXXXXXXX5');
