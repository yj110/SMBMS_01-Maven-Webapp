package com.yj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yj.entity.SmbmsUser;

public interface SmbmsUserDao {
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	SmbmsUser login(SmbmsUser user);
	/**
	 * 查询所有用户信息
	 * @return
	 */
	List<SmbmsUser> getAllUser(@Param("userName")String userName,@Param("userRole")Long userRole,
			@Param("begin")int begin,@Param("size")int size);
	/**
	 * 根据id查询用户信息
	 * @return
	 */
	SmbmsUser getUserById(Long id);
	/**
	 * 获得总行数
	 * @param userCode
	 * @param userRole
	 * @return
	 */
	int getAllUserCount(@Param("userName")String userName,@Param("userRole")Long userRole);
	/**
	 * 增加用户
	 * @param user
	 */
	int addUser(SmbmsUser user);
	/**
	 * 修改用户
	 * @param id
	 * @return
	 */
	int updateUser(SmbmsUser user);
}
