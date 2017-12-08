package com.yj.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yj.entity.SmbmsBill;
import com.yj.entity.SmbmsProvider;
import com.yj.entity.SmbmsUser;
import com.yj.service.SmbmRoleService;
import com.yj.service.SmbmsBillService;
import com.yj.service.SmbmsProviderService;
import com.yj.service.SmbmsUserService;
@Controller
@RequestMapping("jsp")
public class UserController {
	@Autowired
	private SmbmsUserService smbmsUserService;
	@Autowired
	private SmbmRoleService smbmRoleService;
	@Autowired
	private SmbmsProviderService providerService;
	@Autowired
	private SmbmsBillService billService;
	
	@RequestMapping("login.do")
	public String login(SmbmsUser user,HttpSession session){
		SmbmsUser logined = smbmsUserService.loginUser(user);
		if (logined!=null) {
			session.setAttribute("userSession", logined);
			return "jsp/frame";
		}
		return "login";
	}
	@RequestMapping("logout.do")
	public String logout(HttpSession session){
		session.removeAttribute("userSession");
		return "login";
	}
	private int pageSize = 5;
	
	@RequestMapping("user.do")
	public String getUsers(String queryUserName,Long queryUserRole,@RequestParam(defaultValue=("1"))Integer pageIndex,Map<String, Object> model ){
		int totalCount = smbmsUserService.getAllUserCounts(queryUserName, queryUserRole);
		int totalPageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		model.put("userList", smbmsUserService.getAllUsers(queryUserName, queryUserRole, pageIndex, pageSize));
		model.put("roleList", smbmRoleService.getAlls());
		model.put("totalCount", totalCount);
		model.put("currentPageNo", pageIndex);
		model.put("totalPageCount", totalPageCount);
		model.put("queryUserName", queryUserName);
		model.put("queryUserRole", queryUserRole);
		return "jsp/userlist";
	}
	
	@RequestMapping("provider.do")
	public String getProviders(String queryProCode,String queryProName,@RequestParam(defaultValue=("1"))Integer pageIndex,Map<String, Object> model ){
		int totalCount = providerService.getCount(queryProCode, queryProName);
		int totalPageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		model.put("providerList", providerService.getAllProvider(queryProCode, queryProName, pageIndex, pageSize));
		model.put("totalCount", totalCount);
		model.put("currentPageNo", pageIndex);
		model.put("totalPageCount", totalPageCount);
		model.put("queryProCode", queryProCode);
		model.put("queryProName", queryProName);
		return "jsp/providerlist";
	}
	
	@RequestMapping("addUser.do")
	public String addUser(SmbmsUser user){
		if (smbmsUserService.addUsers(user)>0) {
			return "redirect:user.do";
		}
		return "jsp/useradd";
	}
	@RequestMapping("toUser.do")
	public String toUser(@RequestParam("id")Long id,HttpSession session){
		session.setAttribute("user", smbmsUserService.getUserById(id));
		return "jsp/usermodify";
	}
	@RequestMapping("viewUser.do")
	public String viewUser(Long id,HttpSession session){
		session.setAttribute("user", smbmsUserService.getUserById(id));
		return "jsp/userview";
	}
	@RequestMapping("modifyUser.do")
	public String modifyUser(SmbmsUser user){
		if (smbmsUserService.updateUser(user)>0) {
			return "redirect:user.do";
		}
		return "jsp/usermodify";
	}
	/*@RequestMapping("addProcider.do")//creationDate
	public String addProcider(SmbmsProvider provider,BindingResult result,@RequestParam("photos") MultipartFile[] photos,HttpSession session){
		if(!result.hasErrors()){
			String path = session.getServletContext().getRealPath("photos");
			List<String> errors = new ArrayList<String>();
			List<String> filenames = new ArrayList<String>();
			//1.检测
			for(MultipartFile photo:photos){
				String err = getErrInfo(photo);
				//2.上传
				if(err == null){
					String fileName = upload(photo, path);
					filenames.add(fileName);
				}else{
					errors.add(err);
				}
			}
			
			if(filenames.size() > 0){
				provider.setWorkPicPath("photos"+File.separator+filenames.get(0));
				provider.setOrgaPicPath("photos"+File.separator+filenames.get(1));
				if(providerService.addProvider(provider)>0){
					return "redirect:provider.do";
				}
			}
			session.setAttribute("errors", errors);
			session.setAttribute("filenames", filenames);
		}
		return "jsp/adduser";
	}*/
	@RequestMapping("addProcider.do")//creationDate
	public String addProcider(SmbmsProvider provider,HttpSession session){
		provider.setCreatedBy(((SmbmsUser)session.getAttribute("userSession")).getId());
		provider.setCreationDate(new Timestamp(System.currentTimeMillis()));
		if (providerService.addProvider(provider)>0) {
			return "redirect:provider.do";
		}
		return "jsp/provideradd";
	}
	/**
	 * 
	 * @param photo
	 * @param path
	 * @return
	 */
	/*public String upload(MultipartFile photo,String path){
		String fileName = photo.getOriginalFilename();
		long time = System.currentTimeMillis();
		int randNumber = RandomUtils.nextInt(1000000);
		File dest = new File(path,time+"_"+randNumber+"_"+fileName);
		try {
			photo.transferTo(dest);
			return fileName;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	/**
	 * //1).上传的文件是否为空
		//2).上传文件的大小
		//3).上传文件的格式
	 * @param photo
	 * @return null is ok!!!
	 */
	/*public String getErrInfo(MultipartFile photo){
		String fileName = photo.getOriginalFilename();
		String suf = FilenameUtils.getExtension(fileName);
		String err = null;
		if(photo.isEmpty()){
			err = String.format("%s：上传文件不能为空",fileName);
		}else if(photo.getSize()>500000){
			err = String.format("%s：上传文件大小不能超过500KB",fileName);
		}else if(!formats.contains(suf.toLowerCase())){
			err = String.format("%s：上传文件格式不正确，支持的格式：%s",fileName,formats);
		}
		return err;
	}
	static List<String> formats = new ArrayList<String>();
	static{
		formats.add("png");
		formats.add("jpg");
		formats.add("jpeg");
		formats.add("pneg");
	}*/
	@RequestMapping("toProcider.do")
	public String toProcider(@RequestParam("id")Long id,HttpSession session){
		session.setAttribute("provider", providerService.getProviderById(id));
		return "jsp/providermodify";
	}
	@RequestMapping("viewProcider.do")
	public String viewProcider(Long id,HttpSession session){
		session.setAttribute("provider", providerService.getProviderById(id));
		return "jsp/providerview";
	}
	@RequestMapping("modifyProcider.do")
	public String modifyProcider(SmbmsProvider provider,HttpSession session){
		provider.setId(((SmbmsProvider) session.getAttribute("provider")).getId());
		if (providerService.updateProvider(provider) >0) {
			return "redirect:provider.do";
		}
		return "jsp/providermodify";
	}
	@RequestMapping("bill.do")
	public String getBills(String queryProductName,Long queryProviderId,Integer queryIsPayment,@RequestParam(defaultValue=("1"))Integer pageIndex,Map<String, Object> model ){
		int totalCount = billService.getAllBillCount(queryProductName, queryProviderId, queryIsPayment);
		int totalPageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		model.put("billList", billService.getAllBill(queryProductName, queryProviderId, queryIsPayment, pageIndex, pageSize));
		model.put("providerList",providerService.getAll());
		model.put("totalCount", totalCount);
		model.put("currentPageNo", pageIndex);
		model.put("totalPageCount", totalPageCount);
		model.put("queryProductName", queryProductName);
		model.put("queryProviderId", queryProviderId);
		model.put("queryIsPayment", queryIsPayment);
		return "jsp/billlist";
	}
	@ResponseBody
	@RequestMapping("getproviderlist.do")
	public String getproviderlist(){
		return JSONArray.toJSONString(providerService.getAll());
	}
	@RequestMapping("addBill.do")//creationDate
	public String addBill(SmbmsBill bill,HttpSession session){
		bill.setCreatedBy(((SmbmsUser)session.getAttribute("userSession")).getId());
		bill.setCreationDate(new Timestamp(System.currentTimeMillis()));
		if (billService.addBill(bill)>0) {
			return "redirect:bill.do";
		}
		return "jsp/billadd";
	}
	@RequestMapping("toBill.do")
	public String toBill(@RequestParam("id")Long id,HttpSession session){
		session.setAttribute("bill", billService.getBillById(id));
		return "jsp/billmodify";
	}
	@RequestMapping("viewBill.do")
	public String viewBill(Long id,HttpSession session){
		session.setAttribute("bill", billService.getBillById(id));
		return "jsp/billview";
	}
	@RequestMapping("modifyBill.do")
	public String modifyBill(SmbmsBill bill,HttpSession session){
		//bill.setId(((SmbmsBill) session.getAttribute("bill")).getId());
		if (billService.updateBill(bill) >0) {
			return "redirect:bill.do";
		}
		return "jsp/billmodify";
	}
	@RequestMapping("delBill.do")
	public String delBill(Long id,HttpSession session){
		if (billService.delBill(id) >0) {
			return "redirect:bill.do";
		}
		return "jsp/billlist";
	}
}
