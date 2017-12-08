package com.yj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yj.entity.SmbmsUser;

public interface SmbmsUserService {
	
	SmbmsUser loginUser(SmbmsUser user);
	
	List<SmbmsUser> getAllUsers(String userName,Long userRole,
			int pageIndex,int pageSize);
	
	SmbmsUser getUserById(Long id);
	
	int getAllUserCounts(String userName,Long userRole);
	
	int addUsers(SmbmsUser user);
	
	int updateUser(SmbmsUser user);
} 
