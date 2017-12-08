package com.yj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yj.entity.SmbmsProvider;

public interface SmbmsProviderService {
	List<SmbmsProvider> getAllProvider(String proCode,String proName,
			int pageIndex,int pageSize);
	
	int getCount(String proCode,String proName);
	
	SmbmsProvider getProviderById(Long id);
	
	List<SmbmsProvider> getAll();
	
	int addProvider(SmbmsProvider provider);
	
	int updateProvider(SmbmsProvider provider);
}
