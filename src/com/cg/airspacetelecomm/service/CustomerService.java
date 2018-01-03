package com.cg.airspacetelecomm.service;

import com.cg.airspacetelecomm.bean.UserBean;
import com.cg.airspacetelecomm.exception.AirSpaceException;

public interface CustomerService {
	public void addUser(UserBean user) throws AirSpaceException;
	public void Adjustbill(UserBean user,int Balance) throws AirSpaceException;
	public void editUser(UserBean user) throws AirSpaceException;

}
