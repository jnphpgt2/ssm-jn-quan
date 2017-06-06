package org.jnan.service;

import java.util.List;

import org.jnan.mapper.CityMapper;
import org.jnan.po.City;
import org.jnan.po.Proportion;
import org.jnan.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("cityService")
public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper cityMapper;
	public List<City> findCity(String name){
		List<City> citylist = cityMapper.findCity(name);
		/*for (City city : citylist) {
			if(city.getZong()==null){
			Proportion proportion = cityMapper.findProportion();
			city.setZong(city.getShehui()*proportion.getShehui()+city.getJingji()*proportion.getJingji()+city.getHuanjing()*proportion.getHuanjing()+city.getZiyuan()*proportion.getZiyuan()+city.getShenghuo()*proportion.getShenghuo()+city.getGong()*proportion.getGong());
			}
		}*/
		return citylist;
	}
	@Override
	public Proportion findProportion() {
		return cityMapper.findProportion();
	}
	@Override
	public boolean toupdate(Proportion pro) {
		cityMapper.toupdate(pro);
		List<City> citylist = cityMapper.ufindCity();
		for (City city : citylist) {
			Proportion proportion = cityMapper.findProportion();
			city.setZong(city.getShehui()*proportion.getShehui()+city.getJingji()*proportion.getJingji()+city.getHuanjing()*proportion.getHuanjing()+city.getZiyuan()*proportion.getZiyuan()+city.getShenghuo()*proportion.getShenghuo()+city.getGong()*proportion.getGong());
			cityMapper.updateZong(city);
		}
		return true;
	}
	@Override
	public City getDanCity(City city) {
		return cityMapper.getDanCity(city.getCname());
	}
	@Override
	public boolean gengxin(City ci) {
		cityMapper.gengxin(ci);
		List<City> citylist = cityMapper.ufindCity();
		for (City city : citylist) {
			Proportion proportion = cityMapper.findProportion();
			city.setZong(city.getShehui()*proportion.getShehui()+city.getJingji()*proportion.getJingji()+city.getHuanjing()*proportion.getHuanjing()+city.getZiyuan()*proportion.getZiyuan()+city.getShenghuo()*proportion.getShenghuo()+city.getGong()*proportion.getGong());
			cityMapper.updateZong(city);
		}
		return true;
	}
	@Override
	public User tologin(User user) {
		user=cityMapper.tologin(user);
		return user;
	}
	
	
}
