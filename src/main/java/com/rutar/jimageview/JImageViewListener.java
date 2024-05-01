package com.rutar.jimageview;

import java.util.*;

// ............................................................................

/**
 * Клас JImageViewListener
 * @author Rutar_Andriy
 * 07.03.2024
 */

public interface JImageViewListener extends EventListener {

/**
 * Зміна відображуваного зображення
 * @param evt Подія типу JImageViewEvent
 */
public void imageChange (JImageViewEvent evt);

/**
 * Зміна зображення, яке відображається при помилці
 * @param evt Подія типу JImageViewEvent
 */
public void errorImageChange (JImageViewEvent evt);

/**
 * Зміна масштабу зображення
 * @param evt Подія типу JImageViewEvent
 */
public void imageScaleChange (JImageViewEvent evt);

/**
 * Зміна типу масштабування зображення
 * @param evt Подія типу JImageViewEvent
 */
public void imageScaleTypeChange (JImageViewEvent evt);

/**
 * Зміна розміру щойно відкритого зображення
 * @param evt Подія типу JImageViewEvent
 */
public void imageOpenSizeChange (JImageViewEvent evt);

/**
 * Зміна можливості використання ЛКМ
 * @param evt Подія типу JImageViewEvent
 */
public void LMBEnableChange (JImageViewEvent evt);

/**
 * Зміна можливості використання СКМ
 * @param evt Подія типу JImageViewEvent
 */
public void CMBEnableChange (JImageViewEvent evt);

/**
 * Зміна можливості використання ПКМ
 * @param evt Подія типу JImageViewEvent
 */
public void RMBEnableChange (JImageViewEvent evt);

/**
 * Зміна можливості використання комбінації ЛКМ + ПКМ
 * @param evt Подія типу JImageViewEvent
 */
public void ZMBEnableChange (JImageViewEvent evt);

/**
 * Зміна можливості використання колесика миші
 * @param evt Подія типу JImageViewEvent
 */
public void wheelEnableChange (JImageViewEvent evt);

/**
 * Зміна інвертування колесика миші
 * @param evt Подія типу JImageViewEvent
 */
public void wheelInvertChange (JImageViewEvent evt);

/**
 * Зміна можливості переміщення зображення за межею компонента
 * @param evt Подія типу JImageViewEvent
 */
public void drugImageOutChange (JImageViewEvent evt);

/**
 * Зміна видимості фонової сітки
 * @param evt Подія типу JImageViewEvent
 */
public void gridVisibleChange (JImageViewEvent evt);

/**
 * Зміна I кольору фонової сітки
 * @param evt Подія типу JImageViewEvent
 */
public void gridLightColorChange (JImageViewEvent evt);

/**
 * Зміна II кольору фонової сітки
 * @param evt Подія типу JImageViewEvent
 */
public void gridDarkColorChange (JImageViewEvent evt);

/**
 * Зміна розміру фонової сітки
 * @param evt Подія типу JImageViewEvent
 */
public void gridSizeChange (JImageViewEvent evt);

/**
 * Зміна штриха виділення регіону
 * @param evt Подія типу JImageViewEvent
 */
public void regionStrokeChange (JImageViewEvent evt);

/**
 * Зміна відображення додаткового штриха виділення регіону
 * @param evt Подія типу JImageViewEvent
 */
public void regionAdditionalStrokeChange (JImageViewEvent evt);

/**
 * Зміна I кольору рамки регіону
 * @param evt Подія типу JImageViewEvent
 */
public void regionLightColorChange (JImageViewEvent evt);

/**
 * Зміна II кольору рамки регіону
 * @param evt Подія типу JImageViewEvent
 */
public void regionDarkColorChange (JImageViewEvent evt);

// Кінець класу JImageViewListener ////////////////////////////////////////////

}
