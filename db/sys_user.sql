DROP DATABASE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`               int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
    `org_id`           int(11) NULL DEFAULT NULL COMMENT '组织ID',
    `lock_time`        datetime(0) NULL DEFAULT current_timestamp (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '锁定时间',
    `last_login_time`  datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
    `try_count`        int(11) NULL DEFAULT NULL COMMENT '尝试次数',
    `lock_flag`        int(11) NULL DEFAULT NULL COMMENT '锁定状态(1-正常，2-锁定)',
    `create_time`      datetime(0) NOT NULL COMMENT '创建时间',
    `modified_time`    datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    `is_deleted`       tinyint(4) NULL DEFAULT 0 COMMENT '1 表示删除，0 表示未删除',
    `remarks`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
    `real_name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
    `pass_update_time` datetime(0) NULL DEFAULT NULL COMMENT '密码更新时间',
    `card`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号\r\n',
    `is_show`          tinyint(1) NULL DEFAULT 0 COMMENT '1 表示不显示，0 表示显示',
    `enable`           tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '启用状态：1 启用，2：停用',
    `first_login`      tinyint(1) NULL DEFAULT NULL COMMENT '首次登录计数',
    `person_rank`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职级',
    `sex`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '性别',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic