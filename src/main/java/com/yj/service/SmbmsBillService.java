package com.yj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yj.entity.SmbmsBill;

public interface SmbmsBillService {
	List<SmbmsBill> getAllBill(String productName,Long providerId,
			Integer isPayment,int pageIndex,int pageSize);
	
	SmbmsBill getBillById(Long id);
	
	int getAllBillCount(String productName,Long providerId,Integer isPayment);
	
	int addBill(SmbmsBill bill);
	
	int updateBill(SmbmsBill bill);
	
	int delBill(Long id);
}
