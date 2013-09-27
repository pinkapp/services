package cc.ywxm.commonservice.dao.impl;


import cc.ywxm.commonservice.dao.IUserDao;

public class UserDaoImpl implements IUserDao {

	@Override
	public String getUserList() {
		return "Hello,Get the user list from database!";
	}

	@Override
	public int sum(int a, int b) {
		return a+b;
	}
}