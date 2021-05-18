package no.MCH;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.formdev.flatlaf.FlatDarculaLaf;

import no.MCH.config.Config;
import no.MCH.config.MainConfig;
import no.MCH.constants.Constants;
import no.MCH.view.DefaultGui;
import no.MCH.view.TestAddComponent;

public class Run {
	public static final Logger log = Logger.getLogger(Run.class);
	
	private static Run INSTANCE;
	private static Config config;

    Run() throws UnsupportedLookAndFeelException {
    	INSTANCE = this;
    	
    	final File CONFIG_FILE = Constants.CONFIG_PATH.toFile();
    	if (!CONFIG_FILE.exists()) {
    		CONFIG_FILE.mkdirs();
    	}
    	
    	config = new Config();
    	config.load();
    	
        UIManager.setLookAndFeel(new FlatDarculaLaf());
        TestAddComponent.test();
        DefaultGui gui = new DefaultGui();
    }

	public static Run getInstance() {
		if (INSTANCE == null) {
            throw new RuntimeException("Its too early to get a static instance!");
        }
        return INSTANCE;
	}
	
	public MainConfig getConfig() { 
		return Run.config.get();
	}

}
