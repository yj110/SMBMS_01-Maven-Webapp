package com.yj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yj.entity.SmbmsProvider;

public interface SmbmsProviderDao {
	/**
	 * 分页按条件查询
	 * @param proCode
	 * @param proName
	 * @param begin
	 * @param size
	 * @return
	 */
	List<SmbmsProvider> getAllProvider(@Param("proCode")String proCode,@Param("proName")String proName,
			@Param("begin")int begin,@Param("size")int size);
	/**
	 * 根据id获得供应商
	 * @param id
	 * @return
	 */
	SmbmsProvider getProviderById(Long id);
	/**
	 * 获得总记录数
	 * @param proCode
	 * @param proName
	 * @return
	 */
	int getCount(@Param("proCode")String proCode,@Param("proName")String proName);
	/**
	 * 新增供应商
	 * @param provider
	 * @return
	 */
	int addProvider(SmbmsProvider provider);
	/**
	 * 修改供应商
	 * @param provider
	 * @return
	 */
	int updateProvider(SmbmsProvider provider);
	/**
	 * 获得供应商
	 * @return
	 */
	List<SmbmsProvider> getAll();
}
