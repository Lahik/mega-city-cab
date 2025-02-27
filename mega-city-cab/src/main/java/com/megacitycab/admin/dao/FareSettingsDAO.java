package com.megacitycab.admin.dao;

import java.util.List;

import com.megacitycab.model.FareSettings;

public interface FareSettingsDAO {
    FareSettings getFareSettings();
    void updateFareSettings(FareSettings fareSettings);
    List<FareSettings> getFareUpdateHistory();
}
