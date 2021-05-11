package no.MCH.view;

import no.MCH.model.ComponentModel;
import no.MCH.utils.FontLoader;

import javax.swing.*;
import java.awt.*;

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
}