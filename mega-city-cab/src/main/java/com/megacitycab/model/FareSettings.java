package com.megacitycab.model;

import java.sql.Timestamp;

public class FareSettings {
    private int baseFare;
    private double taxRate;
    private double discountRate;
    private Timestamp updateTime;

    public FareSettings() {
    }

	public FareSettings(int baseFare, double taxRate, double discountRate) {
        this.baseFare = baseFare;
        this.taxRate = taxRate;
        this.discountRate = discountRate;
    }
	
	public FareSettings(int baseFare, double taxRate, double discountRate, Timestamp update_time) {
		this.baseFare = baseFare;
		this.taxRate = taxRate;
		this.discountRate = discountRate;
		this.updateTime = update_time;
	}


    public int getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(int baseFare) {
        this.baseFare = baseFare;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp update_time) {
		this.updateTime = update_time;
	}
}
