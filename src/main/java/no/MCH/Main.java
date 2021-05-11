package no.MCH;

import com.formdev.flatlaf.FlatDarculaLaf;
import no.MCH.view.DefaultGui;
import no.MCH.view.TestAddComponent;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatDarculaLaf());
        TestAddComponent.test();
        DefaultGui.renderDefaultGui();
    }

}
