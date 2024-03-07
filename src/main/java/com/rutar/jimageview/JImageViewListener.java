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
 * Зміна усмішки
 * @param evt Подія типу JImageViewEvent
 */
public void smileChange (JImageViewEvent evt);
    
/**
 * Зміна ширини ліній
 * @param evt Подія типу JImageViewEvent
 */
public void lineWidthChange (JImageViewEvent evt);

/**
 * Зміна ширини усмішки (в градусах)
 * @param evt Подія типу JImageViewEvent
 */
public void mouthWidthChange (JImageViewEvent evt);

/**
 * Зміна кольору фону
 * @param evt Подія типу JImageViewEvent
 */
public void backgroundChange (JImageViewEvent evt);

/**
 * Зміна кольору ліній
 * @param evt Подія типу JImageViewEvent
 */
public void foregroundChange (JImageViewEvent evt);

// Кінець класу JImageViewListener ////////////////////////////////////////////

}
