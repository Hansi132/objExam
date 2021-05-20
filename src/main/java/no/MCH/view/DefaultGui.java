package no.MCH.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;

import no.MCH.model.ComponentModel;


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
		setResizable(true);
		setVisible(true);
	}
	
	public static void renderDefaultGui(DefaultGui defaultGui) {
        for (Component component : componentModel.getComponentList()) {
            defaultGui.add(component);
        }
    }
}