package com.megacitycab.admin.service;

import com.megacitycab.model.Admin;
import com.megacitycab.service.AccountService;

public interface AdminService extends AccountService<Admin>{
	public boolean updateProfile(Admin admin);
}
