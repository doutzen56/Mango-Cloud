CREATE TABLE `mc_clusters`
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
		`name` varchar(30) NOT NULL COMMENT '集群名称',
		`location` varchar(64) NOT NULL COMMENT '地理位置',
		`record` varchar(64) NOT NULL COMMENT '记录',
		`node_id` int(11) NOT NULL COMMENT '节点',
		`created_by` varchar(30) NOT NULL COMMENT '创建人',
		`created_time` datetime  COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='集群管理';