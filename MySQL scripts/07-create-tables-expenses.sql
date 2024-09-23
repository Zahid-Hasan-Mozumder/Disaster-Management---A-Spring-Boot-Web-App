use `disaster_management`;

-- drop table if exists `expense_tracker`;

create table if not exists `expense_tracker`(
`id` int not null auto_increment,
`memo` varchar(15000) default null,
`total` double default 0,
`purpose` varchar(1000) default null,
`purchase_date` datetime(6) default null,
`purchased_by` varchar(255) default null,
primary key(`id`)
)engine=InnoDB auto_increment=1 default charset=utf8mb4;

