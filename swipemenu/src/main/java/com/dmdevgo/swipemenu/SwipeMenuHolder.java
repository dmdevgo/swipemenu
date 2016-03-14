package com.dmdevgo.swipemenu;

import android.view.View;

/**
 * @author Dmitriy Gorbunov
 */
public interface SwipeMenuHolder {
    View getMenuView();
    View getContentView();
    void onSwipeMenuDraw(float dX, boolean fromUser);
}
