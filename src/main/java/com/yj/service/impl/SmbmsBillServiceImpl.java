package com.yj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.dao.SmbmsBillDao;
import com.yj.entity.SmbmsBill;
import com.yj.service.SmbmsBillService;
@Service("smbmsBillService")
public class SmbmsBillServiceImpl implements SmbmsBillService{
	@Autowired
	private SmbmsBillDao dao;
	
	public List<SmbmsBill> getAllBill(String productName, Long providerId,
			Integer isPayment, int pageIndex,int pageSize) {
		return dao.getAllBill(productName, providerId, isPayment, (pageIndex-1)*pageSize, pageSize);
	}

	public SmbmsBill getBillById(Long id) {
		return dao.getBillById(id);
	}

	public int getAllBillCount(String productName, Long providerId,
			Integer isPayment) {
		return dao.getAllBillCount(productName, providerId, isPayment);
	}

	public int addBill(SmbmsBill bill) {
		return dao.addBill(bill);
	}

	public int updateBill(SmbmsBill bill) {
		return dao.updateBill(bill);
	}

	public int delBill(Long id) {
		int result = dao.delBill(id);
		return result;
	}

}
