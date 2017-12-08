package com.yj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.dao.SmbmsProviderDao;
import com.yj.entity.SmbmsProvider;
import com.yj.service.SmbmsProviderService;
@Service("smbmsProviderService")
public class SmbmsProviderServiceImpl implements SmbmsProviderService {
	@Autowired
	private SmbmsProviderDao dao;
	
	public List<SmbmsProvider> getAllProvider(String proCode, String proName,
			int pageIndex, int pageSize) {
		return dao.getAllProvider(proCode, proName, (pageIndex-1)*pageSize, pageSize);
	}

	public int getCount(String proCode, String proName) {
		return dao.getCount(proCode, proName);
	}

	public SmbmsProvider getProviderById(Long id) {
		return dao.getProviderById(id);
	}

	public int addProvider(SmbmsProvider provider) {
		return dao.addProvider(provider);
	}

	public int updateProvider(SmbmsProvider provider) {
		return dao.updateProvider(provider);
	}
	public List<SmbmsProvider> getAll() {
		return dao.getAll();
	}
}
