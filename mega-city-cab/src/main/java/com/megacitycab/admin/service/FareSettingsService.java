package com.megacitycab.admin.service;

import java.util.List;

import com.megacitycab.model.FareSettings;

public interface FareSettingsService {
    FareSettings getFareSettings();
    void updateFareSettings(FareSettings fareSettings);
    List<FareSettings> getFareUpdateHistory();
}