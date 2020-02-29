package com.dubbo.car.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dubbo.car.entity.City;
import com.dubbo.car.entity.Driver;
import com.dubbo.car.entity.DriverBreak;
import com.dubbo.car.service.DriverManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
public class DriverManagerController {

	@Reference(url="dubbo://127.0.0.1:20881")
	private DriverManagerService driverManagerService;
	
	
	@RequestMapping("list")
	public String driverManagerList(Model model,@RequestParam(defaultValue = "1")Integer pageNum,String name) {
		
		
		
		PageInfo<Driver> pageInfo = driverManagerService.getPageInfo(name,pageNum);
		
		
		model.addAttribute("pageInfo", pageInfo);
		
		model.addAttribute("name", name);
		
		return "list";
	}
	
	@RequestMapping("weig")
	public String breakList(Model model,@RequestParam("id")Integer id) {
	System.err.println(id+"-------------------------------");
		List<DriverBreak> breakList = driverManagerService.selectBreakList(id);
		
		model.addAttribute("breakList", breakList);
		
		return "breakList";
	}
	
	@RequestMapping("toAdd")
	public String toAdd(Model model) {
		
		List<City> plist = driverManagerService.selectByPid(0);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
		model.addAttribute("format", format);
		model.addAttribute("plist", plist);
		return "add";
	}
	@ResponseBody
	@RequestMapping("city")
	public Object city(Model model,Integer pid) {
		
		List<City> plist = driverManagerService.selectByPid(pid);
		
		return plist;
	}
	
	  @ResponseBody
	  @RequestMapping("add") public boolean add(Model model,String name,String
	  sex,String driver_num,Integer pid,Integer cid, Integer xid,String issue_date)
	  { 
		  System.err.println(name+"   "+sex+"    "+driver_num+" "+pid+" "+cid+" "
	  +xid+"   "+issue_date); 
		  
		  Driver driver = new Driver(null,name,sex,pid,cid,xid,null,null,null,issue_date,null,driver_num);
		  boolean flag = driverManagerService.insert(driver);
	  
	  return flag; 
	  }
	 
	  @RequestMapping("update")
	  public String update(Model model,Integer id) {
		  
		  //查询所有的省
		  List<City> plist = driverManagerService.selectByPid(0);
		  //根据id查询驾驶证
		  Driver driver = driverManagerService.selectDriverById(id);
		  //根据pid查询市
		  List<City> clist = driverManagerService.selectByPid(driver.getPid());
	      //根据pid查询区
		  List<City> xlist = driverManagerService.selectByPid(driver.getCid());
		  model.addAttribute("plist", plist);
		  model.addAttribute("driver", driver);
		  model.addAttribute("clist", clist);
		  model.addAttribute("xlist", xlist);
		  return "update";
	  }
	  
	  
	  @ResponseBody
	  @RequestMapping("updateDriver") 
	  public boolean updateDriver(Model model,Integer id,String name,String sex,String driver_num,Integer pid,Integer cid, Integer xid,String issue_date){ 
		  
		  Driver driver = new Driver(id,name,sex,pid,cid,xid,null,null,null,issue_date,null,driver_num);
		  boolean flag = driverManagerService.update(driver);
	  
	      return flag; 
	  }
}
