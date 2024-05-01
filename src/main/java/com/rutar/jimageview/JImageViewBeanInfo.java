package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.imageio.*;

import static java.beans.BeanInfo.*;
import static com.rutar.jimageview.JImageView.*;

// ............................................................................

/**
 * Клас JImageViewBeanInfo
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewBeanInfo extends SimpleBeanInfo {

private final Class parentClass = JImageView.class.getSuperclass();
private final ArrayList<Object> valuesList = new ArrayList<>();

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає список доступних властивостей
 * @return список доступних властивостей
 */
@Override
public PropertyDescriptor[] getPropertyDescriptors() {

PropertyDescriptor property;
ArrayList <PropertyDescriptor> properties = new ArrayList<>();

try {

// ............................................................................
// Отримуємо властивості суперкласу та робимо їх непріоритетними

PropertyDescriptor[] descriptors = Introspector.getBeanInfo(parentClass)
                                               .getPropertyDescriptors();

for (var descriptor : descriptors) {
    descriptor.setPreferred(false);
    properties.add(descriptor);
}

// ............................................................................
// Додаємо нові властивості та робимо їх пріоритетними

/**
 * new PropertyDescriptor(String a, Class b, String c, String d)
 * 
 * a - назва, яка відображається у IDE та описує властивість
 * b - клас, який містить дану властивість
 * c - назва getter-метода
 * d - назва setter-метода
 */

// Image
property = new PropertyDescriptor("image", JImageView.class,
                                  "getImage", "setImage");
property.setPreferred(true);
properties.add(property);

// ErrorImage
property = new PropertyDescriptor("errorImage", JImageView.class,
                                  "getErrorImage", "setErrorImage");
property.setPreferred(true);
properties.add(property);

// ImageScale
property = new PropertyDescriptor("imageScale", JImageView.class,
                                  "getImageScale", "setImageScale");
property.setPreferred(true);
properties.add(property);

// ImageScaleType
property = new PropertyDescriptor("imageScaleType", JImageView.class,
                                  "getImageScaleType", "setImageScaleType");
property.setPreferred(true);
addToValuesList("SCALE_TYPE_FAST",   SCALE_TYPE_FAST,
                "JImageView.SCALE_TYPE_FAST");
addToValuesList("SCALE_TYPE_SMOOTH", SCALE_TYPE_SMOOTH,
                "JImageView.SCALE_TYPE_SMOOTH");
property.setValue("enumerationValues", getValuesList());
properties.add(property);

// ImageOpenSize
property = new PropertyDescriptor("imageOpenSize", JImageView.class,
                                  "getImageOpenSize", "setImageOpenSize");
property.setPreferred(true);
addToValuesList("OPEN_SIZE_ORIGINAL",     OPEN_SIZE_ORIGINAL,
                "JImageView.OPEN_SIZE_ORIGINAL");
addToValuesList("OPEN_SIZE_INTERNAL_FIT", OPEN_SIZE_INTERNAL_FIT,
                "JImageView.OPEN_SIZE_INTERNAL_FIT");
addToValuesList("OPEN_SIZE_EXTERNAL_FIT", OPEN_SIZE_EXTERNAL_FIT,
                "JImageView.OPEN_SIZE_EXTERNAL_FIT");
property.setValue("enumerationValues", getValuesList());
properties.add(property);

// LMBEnable
property = new PropertyDescriptor("lmbEnable", JImageView.class,
                                  "isLMBEnable", "setLMBEnable");
property.setPreferred(true);
properties.add(property);

// CMBEnable
property = new PropertyDescriptor("cmbEnable", JImageView.class,
                                  "isCMBEnable", "setCMBEnable");
property.setPreferred(true);
properties.add(property);

// RMBEnable
property = new PropertyDescriptor("rmbEnable", JImageView.class,
                                  "isRMBEnable", "setRMBEnable");
property.setPreferred(true);
properties.add(property);

// ZMBEnable
property = new PropertyDescriptor("zmbEnable", JImageView.class,
                                  "isZMBEnable", "setZMBEnable");
property.setPreferred(true);
properties.add(property);

// WheelEnable
property = new PropertyDescriptor("wheelEnable", JImageView.class,
                                  "isWheelEnable", "setWheelEnable");
property.setPreferred(true);
properties.add(property);

// WheelInvert
property = new PropertyDescriptor("wheelInvert", JImageView.class,
                                  "isWheelInvert", "setWheelInvert");
property.setPreferred(true);
properties.add(property);

// DrugImageOut
property = new PropertyDescriptor("drugImageOut", JImageView.class,
                                  "isDrugImageOut", "setDrugImageOut");
property.setPreferred(true);
properties.add(property);

// GridVisible
property = new PropertyDescriptor("gridVisible", JImageView.class,
                                  "isGridVisible", "setGridVisible");
property.setPreferred(true);
properties.add(property);

// GridLightColor
property = new PropertyDescriptor("gridLightColor", JImageView.class,
                                  "getGridLightColor", "setGridLightColor");
property.setPreferred(true);
properties.add(property);

// GridDarkColor
property = new PropertyDescriptor("gridDarkColor", JImageView.class,
                                  "getGridDarkColor", "setGridDarkColor");
property.setPreferred(true);
properties.add(property);

// GridSize
property = new PropertyDescriptor("gridSize", JImageView.class,
                                  "getGridSize", "setGridSize");
property.setPreferred(true);
properties.add(property);

// RegionStroke
property = new PropertyDescriptor("regionStroke", JImageView.class,
                                  "getRegionStroke", "setRegionStroke");
property.setPreferred(true);
properties.add(property);

// RegionAdditionalStroke
property = new PropertyDescriptor("regionAdditionalStroke", JImageView.class,
                                  "isRegionAdditionalStroke",
                                  "setRegionAdditionalStroke");
property.setPreferred(true);
properties.add(property);

// RegionLightColor
property = new PropertyDescriptor("regionLightColor", JImageView.class,
                                  "getRegionLightColor",
                                  "setRegionLightColor");
property.setPreferred(true);
properties.add(property);

// RegionDarkColor
property = new PropertyDescriptor("regionDarkColor", JImageView.class,
                                  "getRegionDarkColor",
                                  "setRegionDarkColor");
property.setPreferred(true);
properties.add(property);

// RegionMinimumSize
property = new PropertyDescriptor("regionMinimumSize", JImageView.class,
                                  "getRegionMinimumSize",
                                  "setRegionMinimumSize");
property.setPreferred(true);
properties.add(property);

// ZoomArea
property = new PropertyDescriptor("zoomArea", JImageView.class,
                                  "getZoomArea",
                                  "setZoomArea");
property.setPreferred(true);
properties.add(property);

// ZoomOffset
property = new PropertyDescriptor("zoomOffset", JImageView.class,
                                  "getZoomOffset",
                                  "setZoomOffset");
property.setPreferred(true);
properties.add(property);

// ZoomLevel
property = new PropertyDescriptor("zoomLevel", JImageView.class,
                                  "getZoomLevel",
                                  "setZoomLevel");
property.setPreferred(true);
addToValuesList("ZOOM_SCALE_X1_25", ZOOM_SCALE_X1_25,
                "JImageView.ZOOM_SCALE_X1_25");
addToValuesList("ZOOM_SCALE_X1_50", ZOOM_SCALE_X1_50,
                "JImageView.ZOOM_SCALE_X1_50");
addToValuesList("ZOOM_SCALE_X1_75", ZOOM_SCALE_X1_75,
                "JImageView.ZOOM_SCALE_X1_75");
addToValuesList("ZOOM_SCALE_X2_00", ZOOM_SCALE_X2_00,
                "JImageView.ZOOM_SCALE_X2_00");
addToValuesList("ZOOM_SCALE_X2_25", ZOOM_SCALE_X2_25,
                "JImageView.ZOOM_SCALE_X2_25");
addToValuesList("ZOOM_SCALE_X2_50", ZOOM_SCALE_X2_50,
                "JImageView.ZOOM_SCALE_X2_50");
addToValuesList("ZOOM_SCALE_X3_00", ZOOM_SCALE_X3_00,
                "JImageView.ZOOM_SCALE_X3_00");
addToValuesList("ZOOM_SCALE_X4_00", ZOOM_SCALE_X4_00,
                "JImageView.ZOOM_SCALE_X4_00");
addToValuesList("ZOOM_SCALE_X5_00", ZOOM_SCALE_X5_00,
                "JImageView.ZOOM_SCALE_X5_00");
property.setValue("enumerationValues", getValuesList());
properties.add(property);

// ZoomShapeType
property = new PropertyDescriptor("zoomShapeType", JImageView.class,
                                  "getZoomShapeType",
                                  "setZoomShapeType");
property.setPreferred(true);
addToValuesList("ZOOM_SHAPE_RECTANGLE", ZOOM_SHAPE_RECTANGLE,
                "JImageView.ZOOM_SHAPE_RECTANGLE");
addToValuesList("ZOOM_SHAPE_ELLIPSE",   ZOOM_SHAPE_ELLIPSE,
                "JImageView.ZOOM_SHAPE_ELLIPSE");
property.setValue("enumerationValues", getValuesList());
properties.add(property);

// ZoomFirstBorderVisible
property = new PropertyDescriptor("zoomFirstBorderVisible", JImageView.class,
                                  "isZoomFirstBorderVisible",
                                  "setZoomFirstBorderVisible");
property.setPreferred(true);
properties.add(property);

// ZoomSecondBorderVisible
property = new PropertyDescriptor("zoomSecondBorderVisible", JImageView.class,
                                  "isZoomSecondBorderVisible",
                                  "setZoomSecondBorderVisible");
property.setPreferred(true);
properties.add(property);

// ZoomFirstBorderWidth
property = new PropertyDescriptor("zoomFirstBorderWidth", JImageView.class,
                                  "getZoomFirstBorderWidth",
                                  "setZoomFirstBorderWidth");
property.setPreferred(true);
properties.add(property);

// ZoomSecondBorderWidth
property = new PropertyDescriptor("zoomSecondBorderWidth", JImageView.class,
                                  "getZoomSecondBorderWidth",
                                  "setZoomSecondBorderWidth");
property.setPreferred(true);
properties.add(property);

// ZoomFirstBorderGap
property = new PropertyDescriptor("zoomFirstBorderGap", JImageView.class,
                                  "getZoomFirstBorderGap",
                                  "setZoomFirstBorderGap");
property.setPreferred(true);
properties.add(property);

// ZoomSecondBorderGap
property = new PropertyDescriptor("zoomSecondBorderGap", JImageView.class,
                                  "getZoomSecondBorderGap",
                                  "setZoomSecondBorderGap");
property.setPreferred(true);
properties.add(property);

// ZoomFirstBorderColor
property = new PropertyDescriptor("zoomFirstBorderColor", JImageView.class,
                                  "getZoomFirstBorderColor",
                                  "setZoomFirstBorderColor");
property.setPreferred(true);
properties.add(property);

// ZoomSecondBorderColor
property = new PropertyDescriptor("zoomSecondBorderColor", JImageView.class,
                                  "getZoomSecondBorderColor",
                                  "setZoomSecondBorderColor");
property.setPreferred(true);
properties.add(property);

// ZoomShowCursor
property = new PropertyDescriptor("zoomShowCursor", JImageView.class,
                                  "isZoomShowCursor",
                                  "setZoomShowCursor");
property.setPreferred(true);
properties.add(property);

// DrugZoomOut
property = new PropertyDescriptor("drugZoomOut", JImageView.class,
                                  "isDrugZoomOut",
                                  "setDrugZoomOut");
property.setPreferred(true);
properties.add(property);

// InvertZoomOut
property = new PropertyDescriptor("invertZoomOut", JImageView.class,
                                  "isInvertZoomOut",
                                  "setInvertZoomOut");
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
ArrayList <EventSetDescriptor> eventSets = new ArrayList<>();

try {

// ............................................................................
// Отримуємо набори подій суперкласу та робимо їх непріоритетними

EventSetDescriptor[] descriptors = Introspector.getBeanInfo(parentClass)
                                               .getEventSetDescriptors();

for (var descriptor : descriptors) {
    descriptor.setPreferred(false);
    eventSets.add(descriptor);
}

// ............................................................................
// Додаємо нові набори подій та робимо їх пріоритетними

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

// ............................................................................
// JImageViewListener

methods = new String[] { "imageChange",
                         "errorImageChange",
                         "imageScaleChange",
                         "imageScaleTypeChange",
                         "imageOpenSizeChange",
                         "LMBEnableChange",
                         "CMBEnableChange",
                         "RMBEnableChange",
                         "wheelEnableChange",
                         "wheelInvertChange",
                         "drugImageOutChange",
                         "gridVisibleChange",
                         "gridLightColorChange",
                         "gridDarkColorChange",
                         "gridSizeChange",
                         "regionStrokeChange",
                         "regionAdditionalStrokeChange",
                         "regionLightColorChange",
                         "regionDarkColorChange" };

eventSet = new EventSetDescriptor(JImageView.class,
                                  "JImageViewListener",
                                  JImageViewAdapter.class, methods,
                                  "addJImageViewListener",
                                  "removeJImageViewListener");

eventSets.add(eventSet);
  
}

catch (IntrospectionException e) { }

return eventSets.toArray(EventSetDescriptor[]::new);

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

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод додає елемент до списку перелічених значень
 * @param name Назва елемента, яка відображатиметься в IDE
 * @param value Значення елемента, IDE використовує його для порівняння
 * @param code Java-код, який IDE буде вставляти у setter-метод
 */
private void addToValuesList (String name, Object value, String code) {

    valuesList.add(name);
    valuesList.add(value);
    valuesList.add(code);

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає масив перелічених значень і очищує список
 * @return масив перелічених значень
 */
private Object[] getValuesList() {
    
    Object[] result = valuesList.toArray();
    valuesList.clear();
    
    return result;
    
}

// Кінець класу JImageViewBeanInfo ////////////////////////////////////////////

}
