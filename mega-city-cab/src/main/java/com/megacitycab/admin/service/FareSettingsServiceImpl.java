package com.megacitycab.admin.service;

import java.util.List;

import com.megacitycab.admin.dao.FareSettingsDAOImpl;
import com.megacitycab.model.FareSettings;

public class FareSettingsServiceImpl implements FareSettingsService {
	
    private final FareSettingsDAOImpl fareSettingsDAO = new FareSettingsDAOImpl();

    @Override
    public FareSettings getFareSettings() {
        return fareSettingsDAO.getFareSettings();
    }

    @Override
    public void updateFareSettings(FareSettings fareSettings) {
        fareSettingsDAO.updateFareSettings(fareSettings);
    }

    @Override
    public List<FareSettings> getFareUpdateHistory() {
        return fareSettingsDAO.getFareUpdateHistory();
    }
}