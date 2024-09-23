use `disaster_management`;

-- drop table if exists `admin`;
-- drop table if exists `tasks`;
-- drop table if exists `volunteers`;

create table if not exists `admin`(
`id` int not null auto_increment,
`image` varchar(255) default null,
`first_name` varchar(255) default null,
`last_name` varchar(255) default null,
`email` varchar(255) default null,
`address` varchar(255) default null,
`gender` varchar(255) default null,
`age` int default null,
`religion` varchar(255) default null,
`nationality` varchar(255) default null,
`profession` varchar(255) default null,
`contact_number` varchar(255) default null,
`registration_date` datetime(6) default null,
primary key(`id`),
unique key `uk_dm_admin` (`email`)
) engine=InnoDB auto_increment=2 default charset=latin1;

insert into `admin` 
value 
(1, "zahid.jpg", "Md. Zahid Hasan", "Mozumder", "jahidhasanmozumder@gmail.com", "House-03, Road-06, Block-A, Bosila Metro Housing, Bosila, Mohammadpur, Dhaka-1207, Bnagladesh", "male", 24, "Islam", "Bangladeshi", "Software Engineer", "01537508951", null);

create table if not exists `volunteers`(
`id` int not null auto_increment,
`image` varchar(255) default null,
`first_name` varchar(255) default null,
`last_name` varchar(255) default null,
`email` varchar(255) default null,
`address` varchar(255) default null,
`gender` varchar(255) default null,
`age` int default null,
`religion` varchar(255) default null,
`nationality` varchar(255) default null,
`profession` varchar(255) default null,
`contact_number` varchar(255) default null,
`registration_date` datetime(6) default null,
primary key(`id`),
unique key `uk_dm_volunteer`(`email`)
) engine=InnoDB auto_increment=1 default charset=latin1;

create table if not exists `tasks`(
`id` int not null auto_increment,
`task` varchar(15000) default null,
`status` varchar(255) default null,
`volunteer_id` int default null,
primary key(`id`),
key `fk_dm_volunteer` (`volunteer_id`),
constraint `fk_dm_volunteer` foreign key (`volunteer_id`) references `volunteers` (`id`)
) engine=InnoDB auto_increment=1 default charset=utf8mb4;