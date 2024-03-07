package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.imageio.*;

import static java.beans.BeanInfo.*;

// ............................................................................

/**
 * Клас JImageViewBeanInfo
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewBeanInfo extends SimpleBeanInfo {

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає список доступних властивостей
 * @return список доступних властивостей
 */
@Override
public PropertyDescriptor[] getPropertyDescriptors() {

PropertyDescriptor property;
ArrayList <PropertyDescriptor> properties = new ArrayList<>();

/**
 * new PropertyDescriptor(String a, Class b, String c, String d)
 * 
 * a - назва, яка відображається у IDE та описує властивість
 * b - клас, який містить дану властивість
 * c - назва getter-метода
 * d - назва setter-метода
 */

try {

// Background
property = new PropertyDescriptor("background", JImageView.class,
                                  "getBackground", "setBackground");
property.setPreferred(false);
properties.add(property);

// Foreground
property = new PropertyDescriptor("foreground", JImageView.class,
                                  "getForeground", "setForeground");
property.setPreferred(false);
properties.add(property);

// LineWidth
property = new PropertyDescriptor("lineWidth", JImageView.class,
                                  "getLineWidth", "setLineWidth");
property.setPreferred(true);
properties.add(property);

// MouthWidth
property = new PropertyDescriptor("mouthWidth", JImageView.class,
                                  "getMouthWidth", "setMouthWidth");
property.setPreferred(true);
properties.add(property);

// Smile
property = new PropertyDescriptor("smile", JImageView.class,
                                  "isSmile", "setSmile");
property.setPreferred(true);
properties.add(property);

}

catch (IntrospectionException e) { }

return properties.toArray(PropertyDescriptor[]::new);

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає список доступних прослуховувачів
 * @return список доступних прослуховувачів
 */
@Override
public EventSetDescriptor[] getEventSetDescriptors() {

String[] methods;
EventSetDescriptor eventSet;
ArrayList <EventSetDescriptor> descriptors = new ArrayList<>();

/**
 * new EventSetDescriptor(Class a, String b, Class c, String[] d,
                          String e, String f)
 * 
 * a - клас, який відправляє події
 * b - програмна назва дескриптора подій
 * c - клас, який містить методи для прослуховування. Бажано використовувати
       адаптери, а не інтерфейси
 * d - масив строк, який містить назви методів для відобреження в IDE
 * e - назва метода, який додає прослуховувач
 * f - назва setter, який видаляє прослуховувач
 */

try {

// ............................................................................
// JImageViewListener

methods = new String[] { "smileChange",
                         "lineWidthChange",
                         "mouseWidthChange",
                         "backgroundChange",
                         "foregroundChange" };

eventSet = new EventSetDescriptor(JImageView.class,
                                  "JImageViewListener",
                                  JImageViewAdapter.class, methods,
                                  "addJImageViewListener",
                                  "removeJImageViewListener");

descriptors.add(eventSet);

// ............................................................................
// PropertyChangeListener

methods = new String[] { "propertyChange" };

eventSet = new EventSetDescriptor(JImageView.class,
                                  "PropertyChangeListener",
                                  PropertyChangeListener.class, methods,
                                  "addPropertyChangeListener",
                                  "removePropertyChangeListener");

descriptors.add(eventSet);
  
}

catch (IntrospectionException e) { }

return descriptors.toArray(EventSetDescriptor[]::new);

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає об'єкт зображення типу Image
 * @param iconType Тип іконки - константа класу BeanInfo
 * @return об'єкт типу Image
 */
@Override
public Image getIcon (int iconType) {

return switch (iconType) {

    case ICON_MONO_16x16, ICON_COLOR_16x16 -> loadIcon(false);
    case ICON_MONO_32x32, ICON_COLOR_32x32 -> loadIcon(true);

    default -> null;

};
}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод завантажує зображення різного розміру
 * @param large Розмір іконки (true - великий, false - малий)
 * @return об'єкт типу Image
 */
private Image loadIcon (boolean large) {

    Image image;
    String res = "/com/rutar/jimageview/icons/"
               + "icon_" + (large ? "32" : "16") + ".png";
    
    try (InputStream stream = JImageViewBeanInfo.class
                             .getResourceAsStream(res)) {
            
        image = ImageIO.read(stream);
        return image;
    
    }
    
    catch (IOException ex) { return null; }

}

// Кінець класу JImageViewBeanInfo ////////////////////////////////////////////

}
