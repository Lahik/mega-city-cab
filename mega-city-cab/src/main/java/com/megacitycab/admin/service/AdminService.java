package com.megacitycab.admin.service;

import java.util.List;

import com.megacitycab.model.Admin;
import com.megacitycab.service.AccountService;

public interface AdminService extends AccountService<Admin>{
	boolean updateProfile(Admin admin);
	List<Admin> getAllAdmins();
	void deleteAdmin(int id);
}
