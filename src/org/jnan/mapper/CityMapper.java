package org.jnan.mapper;

import java.util.List;

import org.jnan.po.City;
import org.jnan.po.Proportion;
import org.jnan.po.User;

public interface CityMapper {
	List<City> findCity(String name);

	Proportion findProportion();

	void toupdate(Proportion pro);

	List<City> ufindCity();

	void updateZong(City city);

	City getDanCity(String cname);

	void gengxin(City ci);

	User tologin(User user);

}
