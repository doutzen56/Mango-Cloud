CREATE TABLE `mc_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '客户名称',
  `status` tinyint NOT NULL COMMENT '状态',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  `cluster_id` int(11) DEFAULT NULL COMMENT '集群id',
  `domain` int(11) NOT NULL COMMENT 'Domain',
  `bandwidth` int(11) NOT NULL COMMENT '带宽',
  `ddos_bandwidth` int(11) NOT NULL COMMENT 'DDOS带宽',
	PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='客户管理';