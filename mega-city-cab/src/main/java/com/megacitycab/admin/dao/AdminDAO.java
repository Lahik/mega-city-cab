package com.megacitycab.admin.dao;

import java.util.List;

import com.megacitycab.dao.AccountDAO;
import com.megacitycab.model.Admin;

public interface AdminDAO extends AccountDAO<Admin>{
	boolean updateAccount(Admin admin);
	List<Admin> getAllAdmins();
	void deleteAdmin(int id);
}
