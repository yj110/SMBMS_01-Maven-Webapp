package com.yj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.dao.SmbmsRoleDao;
import com.yj.entity.SmbmsRole;
import com.yj.service.SmbmRoleService;
@Service("smbmRoleService")
public class SmbmsRoleServiceImpl implements SmbmRoleService {
	
	@Autowired
	private SmbmsRoleDao roleDao;
	
	public List<SmbmsRole> getAlls() {
		return roleDao.getAll();
	}

}
