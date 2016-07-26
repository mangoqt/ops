/**
 * <p>User: Tim Tan
 * <p>Date: 2016年5月20日
 * <p>Version: 1.0
 */

package com.qt.inspection.model;

public class Memory {
	private long free;
	private long total;
	private long used;
	public long getFree() {
		return free;
	}
	public void setFree(long free) {
		this.free = free;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
}

