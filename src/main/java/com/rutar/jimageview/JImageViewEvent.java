package com.rutar.jimageview;

import java.awt.*;

// ............................................................................

/**
 * Клас JImageViewEvent
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewEvent extends AWTEvent {

private final Object oldValue;
private final Object newValue;

/** Ідентифікатор події JIMAGEVIEW_EVENT */
public static int JIMAGEVIEW_EVENT = AWTEvent.RESERVED_ID_MAX + 333;

///////////////////////////////////////////////////////////////////////////////

public JImageViewEvent (Object source, Object oldValue, Object newValue) {

super(source, JIMAGEVIEW_EVENT);

this.oldValue = oldValue;
this.newValue = newValue;

}

///////////////////////////////////////////////////////////////////////////////

public Object getOldValue() { return oldValue; }
public Object getNewValue() { return newValue; }

///////////////////////////////////////////////////////////////////////////////

@Override
public String toString() {

    return getClass().getName() +
        "[" +
            "oldValue=" + oldValue + "; " +
            "newValue=" + newValue +
        "]";
}

// Кінець класу JImageViewEvent ///////////////////////////////////////////////

}
