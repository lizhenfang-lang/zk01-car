package com.dubbo.car.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.dubbo.car.entity.City;
import com.dubbo.car.entity.Driver;
import com.dubbo.car.entity.DriverBreak;
import com.dubbo.car.mapper.DriverManagerMapper;
import com.dubbo.car.service.DriverManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class DriverManagerServiceImpl implements DriverManagerService {

	@Autowired
	private DriverManagerMapper driverManagerMapper;
	
	@Override
	public List<Driver> getDriverList(String name) {
		// TODO Auto-generated method stub
		return driverManagerMapper.selectDriverList(name);
	}

	@Override
	public List<DriverBreak> selectBreakList(Integer id) {
		// TODO Auto-generated method stub
		return driverManagerMapper.selectBreakList(id);
	}

	@Override
	public List<City> selectByPid(int i) {
		// TODO Auto-generated method stub
		return driverManagerMapper.selectByPid(i);
	}

	@Override
	public boolean insert(Driver driver) {
		// TODO Auto-generated method stub
		return driverManagerMapper.insert(driver)>0;
	}

	@Override
	public Driver selectDriverById(Integer id) {
		// TODO Auto-generated method stub
		return driverManagerMapper.selectDriverById(id);
	}

	@Override
	public boolean update(Driver driver) {
		// TODO Auto-generated method stub
		return driverManagerMapper.update(driver)>0;
	}

	@Override
	public PageInfo<Driver> getPageInfo(String name, Integer pageNum) {
		PageHelper.startPage(pageNum,5);
		List<Driver> driverList = driverManagerMapper.selectDriverList(name);
		
		PageInfo<Driver> pageInfo = new PageInfo<>(driverList);
		
		return pageInfo;
	}

}
