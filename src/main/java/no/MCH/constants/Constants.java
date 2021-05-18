package no.MCH.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {
	public static final String WORKING_DIR = System.getProperty("user.dir");
	public static final Path WORKING_PATH = Paths.get(WORKING_DIR);
	public static final Path CONFIG_PATH = WORKING_PATH.resolve("MCHConfig");
}
