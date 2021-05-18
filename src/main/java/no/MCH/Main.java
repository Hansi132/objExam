package no.MCH;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;

import no.MCH.config.Config;
import no.MCH.constants.Constants;
import no.MCH.view.DefaultGui;
import no.MCH.view.TestAddComponent;

public class Main {
	private static Config config;

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
    	final File CONFIG_FILE = Constants.CONFIG_PATH.toFile();
    	if (!CONFIG_FILE.exists()) {
    		CONFIG_FILE.mkdirs();
    	}
    	
    	config = new Config();
    	config.load();
    	
        UIManager.setLookAndFeel(new FlatDarculaLaf());
        TestAddComponent.test();
        DefaultGui.renderDefaultGui();
    }

}
