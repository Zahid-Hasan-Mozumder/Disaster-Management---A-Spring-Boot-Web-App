use `disaster_management`;

-- drop table if exists `users`;
-- drop table if exists `users_type`;

create table if not exists `users_type`(
`user_type_id` int not null auto_increment,
`user_type_name` varchar(255) default null,
primary key (`user_type_id`)
) engine=InnoDB auto_increment=1 default charset=latin1;

insert into `users_type` values (1, "Admin"), (2, "Volunteer");

create table if not exists `users`(
`user_id` int not null auto_increment,
`email` varchar(255) default null,
`password` varchar(255) default null,
`is_active` bit(1) default null,
`user_type_id` int default null,
primary key (`user_id`),
unique key `uk_dm_user` (`email`),
key `fk_dm_user` (`user_type_id`),
constraint `fk_dm_user` foreign key (`user_type_id`) references `users_type` (`user_type_id`)
) engine=InnoDB auto_increment=1 charset=latin1;

insert into `users` value (1, "jahidhasanmozumder@gmail.com", "$2a$10$KmW3xLhDmTju/.dPO5Wj3epwdwo34XVOAlzHPO70QG3EoHgw5gmDu", 1, 1);

