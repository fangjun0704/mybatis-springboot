CREATE TABLE `t_user` (
  `id` int PRIMARY KEY AUTO_INCREMENT  COMMENT '主键',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` int(20) NOT NULL COMMENT '年龄'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
