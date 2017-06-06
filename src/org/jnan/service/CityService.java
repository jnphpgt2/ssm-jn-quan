package org.jnan.service;

import java.util.List;

import org.jnan.po.City;
import org.jnan.po.Proportion;
import org.jnan.po.User;

public interface CityService {
	List<City> findCity(String name);

	Proportion findProportion();

	boolean toupdate(Proportion pro);

	City getDanCity(City city);

	boolean gengxin(City ci);

	User tologin(User user);

	
	
}
