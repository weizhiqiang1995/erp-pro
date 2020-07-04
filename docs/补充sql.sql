CREATE TABLE erp_tenant (
id varchar(32) NOT NULL COMMENT '租户id',
group_id varchar(32) NOT NULL COMMENT '组id',
user_id varchar(32) NOT NULL COMMENT '用户id',
create_id varchar(32) NOT NULL COMMENT '创建人',
create_time datetime NOT NULL COMMENT '创建时间',
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户表';