package com.megacitycab.admin.dao;

import com.megacitycab.dao.AccountDAO;
import com.megacitycab.model.Admin;

public interface AdminDAO extends AccountDAO<Admin>{
	public boolean updateAccount(Admin admin);
}
