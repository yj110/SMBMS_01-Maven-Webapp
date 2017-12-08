package com.yj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yj.entity.SmbmsBill;

public interface SmbmsBillDao {
	/**
	 * 查询所有订单信息
	 * @return
	 */
	List<SmbmsBill> getAllBill(@Param("productName")String productName,@Param("providerId")Long providerId,
			@Param("isPayment")Integer isPayment,@Param("begin")int begin,@Param("size")int size);
	/**
	 * 根据id查询订单信息
	 * @return
	 */
	SmbmsBill getBillById(Long id);
	/**
	 * 获得总行数
	 * @param userCode
	 * @param userRole
	 * @return
	 */
	int getAllBillCount(@Param("productName")String productName,@Param("providerId")Long providerId,
			@Param("isPayment")Integer isPayment);
	/**
	 * 增加订单
	 * @param user
	 */
	int addBill(SmbmsBill bill);
	/**
	 * 修改订单
	 * @param id
	 * @return
	 */
	int updateBill(SmbmsBill bill);
	/**
	 * 删除订单
	 * @param billCode
	 * @return
	 */
	int delBill(Long id);
}
