package no.MCH.utils;

import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
    public static final Logger log = Logger.getLogger(FontLoader.class);

    public static Font loadFont(String fontName) {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("fonts/" + fontName + ".ttf");
            if (inputStream == null) {
                throw new IOException("Cannot load from resource");
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            return font.deriveFont(Font.PLAIN, 12);
        } catch (IOException | FontFormatException e) {
            log.warn("Unable to use embedded font, using fallback", e);
            return new Font("Serif", Font.PLAIN, 12);
        }
    }
}
