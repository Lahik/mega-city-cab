package com.megacitycab.admin.service;
import java.util.List;

import com.megacitycab.admin.dao.AdminDAOImpl;
import com.megacitycab.model.Admin;

public class AdminServiceImpl implements AdminService {

	private AdminDAOImpl adminDAO;

    public AdminServiceImpl() {
    	this.adminDAO = new AdminDAOImpl();
    }
	
	@Override
	public boolean createUser(Admin admin) {
		return adminDAO.insertUser(admin);
	}

	@Override
	public boolean authenticateUser(String username, String password) {
		return adminDAO.validateCredentials(username, password);
	}

	@Override
	public Admin getUserDetails(String username) {
		return adminDAO.findUserByUsername(username);
	}

	@Override
	public boolean isUsernameTaken(String username, int id) {
		return adminDAO.isUsernameTaken(username, id);
	}

	@Override
	public boolean updateProfile(Admin admin) {
		return adminDAO.updateAccount(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminDAO.getAllAdmins();
	}

	@Override
	public void deleteAdmin(int id) {
		adminDAO.deleteAdmin(id);
	}

}
