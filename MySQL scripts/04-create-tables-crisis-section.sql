use `disaster_management`;

-- drop table if exists `new_crisis`;
-- drop table if exists `all_crisis`;

create table if not exists `new_crisis`(
`id` int not null auto_increment,
`image` varchar(255) default null,
`title` varchar(255) default null,
`description` varchar(15000) default null,
`location` varchar(255) default null,
`posting_date` datetime(6) default null,
`severity` varchar(255) default null,
`required_help` varchar(255) default null,
primary key(`id`)
) engine=InnoDB auto_increment=1 default charset=utf8mb4;

create table if not exists `all_crisis`(
`id` int not null auto_increment,
`image` varchar(255) default null,
`title` varchar(255) default null,
`description` varchar(15000) default null,
`location` varchar(255) default null,
`posting_date` datetime(6) default null,
`severity` varchar(255) default null,
`required_help` varchar(255) default null,
`status` varchar(25) default null,
primary key(`id`)
) engine=InnoDB auto_increment=1 default charset=utf8mb4;