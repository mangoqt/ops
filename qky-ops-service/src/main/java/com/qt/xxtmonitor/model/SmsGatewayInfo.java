package com.qt.xxtmonitor.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SmsGatewayInfo {
	/**  */
	private Integer id;
	/** 短信网关名称 */
	private String name;
	/** -1 未启用 网关类型 0 移动短信网关 1 移动短信状态报告 2 考勤接收记录 */
	private Integer kind;
	/** 网关下发主端口 */
	private Integer mainPort;
	/** 地区范围 */
	private String areaRange;
	/** 数据库驱动 */
	private String dbClass;
	/** 数据库连接 */
	private String dbJdbc;
	/** 数据库登陆用户 */
	private String dbUser;
	/** 数据库登陆密码 */
	private String dbPws;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public Integer getMainPort() {
		return mainPort;
	}

	public void setMainPort(Integer mainPort) {
		this.mainPort = mainPort;
	}

	public String getAreaRange() {
		return areaRange;
	}

	public void setAreaRange(String areaRange) {
		this.areaRange = areaRange;
	}

	public String getDbClass() {
		return dbClass;
	}

	public void setDbClass(String dbClass) {
		this.dbClass = dbClass;
	}

	public String getDbJdbc() {
		return dbJdbc;
	}

	public void setDbJdbc(String dbJdbc) {
		this.dbJdbc = dbJdbc;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPws() {
		return dbPws;
	}

	public void setDbPws(String dbPws) {
		this.dbPws = dbPws;
	}

	public void mapping(ResultSet rs) throws SQLException {
		setId(rs.getInt("ID"));
		setName(rs.getString("NAME"));
		setKind(rs.getInt("KIND"));
		setMainPort(rs.getInt("MAIN_PORT"));
		setAreaRange(rs.getString("AREA_RANGE"));
		setDbClass(rs.getString("DB_CLASS"));
		setDbJdbc(rs.getString("DB_JDBC"));
		setDbUser(rs.getString("DB_USER"));
		setDbPws(rs.getString("DB_PWS"));
	}
}
