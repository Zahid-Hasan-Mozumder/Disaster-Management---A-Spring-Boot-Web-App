use `disaster_management`;

-- drop table if exists `donation_tracker`;

create table if not exists `donation_tracker`(
`id` int not null auto_increment,
`name` varchar(255) default null,
`email` varchar(255) default null,
`contact_number` varchar(255) default null,
`amount` double default 0,
`donation_date` datetime(6) default null,
primary key(`id`)
) engine=InnoDB auto_increment=1 default charset=latin1;

insert into `donation_tracker` value (1, null, null, null, 0.0, null);