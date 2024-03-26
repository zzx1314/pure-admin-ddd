package com.pure.infrastructure.persistent.po;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Classname SysUser
 * @Description 系统用户
 * @Author zhangzexin
 * @Date 2022-02-19 11:04
 * @Version 1.0
 */
@Data
public class SysUserPO implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 账号
	 */
	private String username;

	/**
	 * 姓名
	 */
	private String realName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 组织ID
	 */
	private Integer orgId;

	/**
	 * 锁定时间
	 */
	private LocalDateTime lockTime;

	/**
	 * 上次登录时间
	 */
	private LocalDateTime lastLoginTime;

	/**
	 * 尝试次数
	 */
	private Integer tryCount;

	/**
	 * 锁定状态(1-正常，2-锁定)
	 */
	private Integer lockFlag;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime modifiedTime;

	/**
	 * 1 表示删除，0 表示未删除
	 */
	private Integer isDeleted;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 密码修改时间
	 */
	private LocalDateTime passUpdateTime;

	/**
	 * 身份证号
	 */
	private String card;

	/**
	 * 是否显示用户信息
	 */
	private Integer isShow;

	/**
	 * 启用状态(1-启用，2-停用)
	 */
	private Integer enable;

	/**
	 * 首次登录
	 */
	private Integer firstLogin;


	/**
	 * 性别
	 */
	private String sex;

}
