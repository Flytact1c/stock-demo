CREATE TABLE `stock` (
                         `sku_id` bigint(20) NOT NULL,
                         `name` varchar(50) NOT NULL,
                         `stock_num` bigint(20) NOT NULL DEFAULT '0' COMMENT '库存',
                         `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
                         `create_by` varchar(50) NOT NULL COMMENT ' 创建人',
                         `create_time` datetime NOT NULL COMMENT ' 创建时间',
                         `modify_by` varchar(50) NOT NULL,
                         `modify_time` datetime NOT NULL,
                         PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存表';