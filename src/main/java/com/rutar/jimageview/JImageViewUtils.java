package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;

import static java.awt.image.AffineTransformOp.*;

// ............................................................................

/**
 * Клас JImageViewUtils
 * @author Rutar_Andriy
 * 26.03.2024
 */

public class JImageViewUtils {

/** Горизонтальне відзеркалення */
public static final int FLIP_HORIZONTAL = 0;
/** Вертикальне відзеркалення */
public static final int FLIP_VERTICAL   = 1;


/** Обертання зображення на 90° за годинниковою стрілкою */
public static final int ROTATE_90_DEG  = 90;
/** Обертання зображення на 180° */
public static final int ROTATE_180_DEG = 180;
/** Обертання зображення на 90° проти годинникової стрілки */
public static final int ROTATE_270_DEG = 270;

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє швидко завантажити зображення, набагато швидше, ніж за
 * допомогою стандартного ImageIO.read()
 * @param file файл, який містить зображення
 * @return об'єкт типу BufferedImage
 */
public static BufferedImage getImageQuickly (File file) {

    if (file == null) { return null; }
    
    try (FileInputStream fis = new FileInputStream(file)) {
        return getImageQuickly(fis); }
    catch (Exception e) { return null; }
    
}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє швидко завантажити зображення, набагато швидше, ніж за
 * допомогою стандартного ImageIO.read()
 * @param is об'єкт типу InputStream
 * @return об'єкт типу BufferedImage
 */
public static BufferedImage getImageQuickly (InputStream is) {

try {

    if (is.available() == 0) { return null; }
    
    MediaTracker tracker = new MediaTracker(new JPanel());
    
    byte[] allBytes;
    try (BufferedInputStream bis = new BufferedInputStream(is)) {
        allBytes = bis.readAllBytes();
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

///////////////////////////////////////////////////////////////////////////////

/**
 * Стандартний метод завантаження зображень, 
 * може бути корисний, якщо метод getImageQuickly() не працює
 * @param file файл, який містить зображення
 * @return об'єкт типу BufferedImage
 */
public static BufferedImage getImageSlowly (File file) {

if (file == null) { return null; }

try                   { return ImageIO.read(file); }
catch (IOException e) { return null;               }

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Стандартний метод завантаження зображень, 
 * може бути корисний, якщо метод getImageQuickly() не працює
 * @param is об'єкт типу InputStream
 * @return об'єкт типу BufferedImage
 */
public static BufferedImage getImageSlowly (InputStream is) {

try { if (is.available() == 0) { return null; }
      return ImageIO.read(is); }
catch (IOException e) { return null; }

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати зображення, обернуте на заданий кут
 * @param src зображення для обертання
 * @param angle ROTATE_90_DEG, ROTATE_180_DEG або ROTATE_270_DEG
 * @return обернуте зображення
 */
public static BufferedImage getRotatedImage (BufferedImage src, int angle) {
    
    double a, p, q;
    int w = src.getWidth();
    int h = src.getHeight();
    AffineTransform at = new AffineTransform();
    
    switch (angle) {
        
        case ROTATE_90_DEG  -> { a = -Math.PI/2; p = h;   q = h-w; }
        case ROTATE_180_DEG -> { a =  Math.PI;   p = w;   q = h;   }
        case ROTATE_270_DEG -> { a =  Math.PI/2; p = w-h; q = w;   }

        default -> { return src; } }
    
    at.rotate(a, w, h);
    at.translate(p, q);
              
    return new AffineTransformOp(at, TYPE_NEAREST_NEIGHBOR)
                        .filter(src, null);

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати відзеркалене зображення
 * @param src зображення для відзеркалення
 * @param type FLIP_HORIZONTAL або FLIP_VERTICAL
 * @return відзеркалене зображення
 */
public static BufferedImage getFlippedImage (BufferedImage src, int type) {

if (type == FLIP_VERTICAL) {                       // Вертикальне відзеркалення

    AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
    tx.translate(0, -src.getHeight());
    AffineTransformOp op = new AffineTransformOp(tx, TYPE_NEAREST_NEIGHBOR);
    
    return op.filter(src, null); }

else {                                           // Горизонтальне відзеркалення

    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
    tx.translate(-src.getWidth(), 0);
    AffineTransformOp op = new AffineTransformOp(tx, TYPE_NEAREST_NEIGHBOR);
    
    return op.filter(src, null); }

}

///////////////////////////////////////////////////////////////////////////////

// Кінець класу JImageViewUtils ///////////////////////////////////////////////

}
