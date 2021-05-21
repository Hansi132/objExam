package no.MCH;

import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		try {
			new Run();
		} catch (UnsupportedLookAndFeelException e) {
			log.error(e.getMessage(), e);
		}
	}

}
