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
 * Зміна штриха виділення регіону масштабування
 * @param evt Подія типу JImageViewEvent
 */
public void regionStrokeChange (JImageViewEvent evt);

/**
 * Зміна відображення додаткового штриха виділення регіону масштабування
 * @param evt Подія типу JImageViewEvent
 */
public void regionAdditionalStrokeChange (JImageViewEvent evt);

/**
 * Зміна I кольору рамки регіону масштабування
 * @param evt Подія типу JImageViewEvent
 */
public void regionLightColorChange (JImageViewEvent evt);

/**
 * Зміна II кольору рамки регіону масштабування
 * @param evt Подія типу JImageViewEvent
 */
public void regionDarkColorChange (JImageViewEvent evt);

/**
 * Зміна мінімального розміру регіону масштабування
 * @param evt Подія типу JImageViewEvent
 */
public void regionMinimumSizeChange (JImageViewEvent evt);

/**
 * Зміна розмірів області масштабування
 * @param evt Подія типу JImageViewEvent
 */
public void zoomAreaChange (JImageViewEvent evt);

/**
 * Зміна відступів області масштабування
 * @param evt Подія типу JImageViewEvent
 */
public void zoomOffsetChange (JImageViewEvent evt);

/**
 * Зміна рівня масштабування лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomLevelChange (JImageViewEvent evt);

/**
 * Зміна типу фігури області масштабування
 * @param evt Подія типу JImageViewEvent
 */
public void zoomShapeTypeChange (JImageViewEvent evt);

/**
 * Зміна видимості I рамки лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomFirstBorderVisibleChange (JImageViewEvent evt);

/**
 * Зміна видимості II рамки лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomSecondBorderVisibleChange (JImageViewEvent evt);

/**
 * Зміна ширини I рамки лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomFirstBorderWidthChange (JImageViewEvent evt);

/**
 * Зміна ширини II рамки лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomSecondBorderWidthChange (JImageViewEvent evt);

/**
 * Зміна відступу I рамки лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomFirstBorderGapChange (JImageViewEvent evt);

/**
 * Зміна відступу II рамки лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomSecondBorderGapChange (JImageViewEvent evt);

/**
 * Зміна кольору I рамки лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomFirstBorderColorChange (JImageViewEvent evt);

/**
 * Зміна кольору II рамки лупи
 * @param evt Подія типу JImageViewEvent
 */
public void zoomSecondBorderColorChange (JImageViewEvent evt);

/**
 * Зміна видимості курсора при збільшенні
 * @param evt Подія типу JImageViewEvent
 */
public void zoomShowCursorChange (JImageViewEvent evt);

/**
 * Зміна видимості лупи за межею компонента
 * @param evt Подія типу JImageViewEvent
 */
public void drugZoomOutChange (JImageViewEvent evt);

/**
 * Зміна інвертування лупи за межею компонента
 * @param evt Подія типу JImageViewEvent
 */
public void invertZoomOutChange (JImageViewEvent evt);

// Кінець класу JImageViewListener ////////////////////////////////////////////

}
