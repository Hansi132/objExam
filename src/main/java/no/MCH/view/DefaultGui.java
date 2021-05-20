package no.MCH.view;

import no.MCH.model.ComponentModel;
import no.MCH.utils.FontLoader;

import javax.swing.*;
import java.awt.*;

import java.awt.GridLayout;

import javax.swing.JFrame;


	public class DefaultGui extends JFrame{
		private static final long serialVersionUID = 1L;	
		public static final ComponentModel componentModel = new ComponentModel();
		private ApplicationMenu appMenu = new ApplicationMenu();
		
	public DefaultGui(){
		
		renderDefaultGui(this);
		
		setTitle("Exam Spring 2021");
		setSize(1030, 1000);
		setLocationRelativeTo(null);
		setLayout(new GridLayout());
		setJMenuBar(appMenu);	
		setResizable(false);
		setVisible(true);
		
		
	}
	
	public static void renderDefaultGui(DefaultGui defaultGui) {
        for (Component component : componentModel.getComponentList()) {
            defaultGui.add(component);
        }
    }
}






/*
public class DefaultGui {
    public static final ComponentModel componentModel = new ComponentModel();

    public static void renderDefaultGui() {
        JFrame frame = new JFrame("");
        frame.setFont(FontLoader.loadFont("FreeSerif"));

        for (Component component : componentModel.getComponentList()) {
            frame.add(component);
        }

        frame.pack();
        frame.setVisible(true);
    } 
}*/ 