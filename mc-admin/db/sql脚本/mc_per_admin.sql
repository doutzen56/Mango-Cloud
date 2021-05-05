CREATE TABLE `mc_per_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `mobile` varchar(255) NOT NULL COMMENT '手机',
  `skype` varchar(255) NOT NULL COMMENT 'Skype',
  `email` varchar(255) NOT NULL COMMENT 'email',
  `language` varchar(255) NOT NULL COMMENT '语言',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  `last_login` datetime NOT NULL COMMENT '最近登录',
  `customer_id` int(11) DEFAULT NULL COMMENT '关联客户',
  `status` TINYINT NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
	) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='账户管理';