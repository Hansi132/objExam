package no.MCH.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MainConfig {
	public static final String HEADER = "Main config file.";
	
	@Setting
	public String databaseDriver = "test";

}
