package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

// ............................................................................

/**
 * Клас JImageViewUtils
 * @author Rutar_Andriy
 * 26.03.2024
 */

public class JImageViewUtils {

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє швидко завантажити зображення, набагато швидше, ніж за
 * допомогою стандартного ImageIO.read()
 * @param file файл, який містить зображення
 * @return об'єкт типу BufferedImage
 */
public static BufferedImage getImageQuickly (File file) {
    
try {

    MediaTracker tracker = new MediaTracker(new JPanel());
    
    byte[] allBytes;
    try (FileInputStream stream = new FileInputStream(file)) {
        allBytes = stream.readAllBytes();
    }
    
    Image toolkitImage = Toolkit.getDefaultToolkit().createImage(allBytes);

    tracker.addImage(toolkitImage, 1);
    tracker.waitForID(1);
    tracker.removeImage(toolkitImage);
                    
    BufferedImage result = new BufferedImage(toolkitImage.getWidth(null),
                                             toolkitImage.getHeight(null),
                                             BufferedImage.TYPE_INT_ARGB);

    result.getGraphics().drawImage(toolkitImage, 0, 0, null);
             
    return result;

}
    
catch (IOException | InterruptedException e) { return null; }

}

// Кінець класу JImageViewAdapter /////////////////////////////////////////////

}
