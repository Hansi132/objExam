package no.MCH.view;

import javax.swing.*;
import java.awt.*;

public class TestAddComponent extends DefaultGui{
	private static final long serialVersionUID = 1L;

	public static void test() {
        DefaultGui.componentModel.addComponent(new JFileChooser());
        JButton jButton = new JButton();

        jButton.addActionListener(e -> {
            System.out.println("Hello world");
        });

        DefaultGui.componentModel.addComponent(jButton);
    }
}
