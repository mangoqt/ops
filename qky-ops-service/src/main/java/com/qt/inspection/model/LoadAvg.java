/**
 * <p>User: Tim Tan
 * <p>Date: 2016年5月20日
 * <p>Version: 1.0
 */

package com.qt.inspection.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadAvg {
	@JsonProperty(value = "Avg1min")  
	private double avg1min;
	@JsonProperty(value = "Avg5min")  
	private double avg5min;
	@JsonProperty(value = "Avg15min")  
	private double avg15min;
	public double getAvg1min() {
		return avg1min;
	}
	public void setAvg1min(double avg1min) {
		this.avg1min = avg1min;
	}
	public double getAvg5min() {
		return avg5min;
	}
	public void setAvg5min(double avg5min) {
		this.avg5min = avg5min;
	}
	public double getAvg15min() {
		return avg15min;
	}
	public void setAvg15min(double avg15min) {
		this.avg15min = avg15min;
	}
}

