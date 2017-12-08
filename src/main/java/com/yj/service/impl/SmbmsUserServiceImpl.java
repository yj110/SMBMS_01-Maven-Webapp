package com.yj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yj.dao.SmbmsUserDao;
import com.yj.entity.SmbmsUser;
import com.yj.service.SmbmsUserService;
@Service("smbmsUserService")
public class SmbmsUserServiceImpl implements SmbmsUserService {
	@Autowired
	private SmbmsUserDao smbmsUserDao;
	
	public SmbmsUser loginUser(SmbmsUser user) {
		return smbmsUserDao.login(user);
	}

	public List<SmbmsUser> getAllUsers(String userName, Long userRole,
			int pageIndex,int pageSize) {
		return smbmsUserDao.getAllUser(userName, userRole, (pageIndex-1)*pageSize, pageSize);
	}

	public int getAllUserCounts(String userCode, Long userRole) {
		return smbmsUserDao.getAllUserCount(userCode, userRole);
	}

	public int addUsers(SmbmsUser user) {
		return smbmsUserDao.addUser(user);
	}

	public int updateUser(SmbmsUser user) {
		return smbmsUserDao.updateUser(user);
	};

	public SmbmsUser getUserById(Long id) {
		return smbmsUserDao.getUserById(id);
	}
}
