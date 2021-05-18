package no.MCH.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MainConfig {
	public static final String HEADER = "Main config file.";
	
	@Setting
	public String databaseUrl = "jdbc:mysql://92.220.179.219:3306/classicmodels";
	
	@Setting
	public String driverName = "com.mysql.jdbc.Driver";
	
	@Setting
	public String databaseUsername = "student";
	
	@Setting
	public String databasePassword = "student";

}
