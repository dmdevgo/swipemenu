package com.dmdevgo.swipemenu;

import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * @author Dmitriy Gorbunov
 */
public abstract class BaseSwipeMenuHolder extends RecyclerView.ViewHolder implements SwipeMenuHolder {

    public static final int MENU_LEFT = 1;
    public static final int MENU_RIGHT = -1;
    public static final int MENU_LEFT_AND_RIGHT = 0;

    private ValueAnimator valueAnimator;
    private boolean mMenuOpened;
    private float direction = 1;

    public BaseSwipeMenuHolder(View itemView) {
        super(itemView);
    }

    protected void drawSwipeMenu(float dX) {
        getContentView().setTranslationX(dX);
        getMenuView().setTranslationX(Math.signum(-dX) * itemView.getWidth() + dX);
    }

    @Override
    public void onSwipeMenuDraw(float dX, boolean fromUser) {

        if (valueAnimator != null && valueAnimator.isRunning()) return;

        if (getMenuDirection() != MENU_LEFT_AND_RIGHT
                && Math.signum(dX) != getMenuDirection()
                && !mMenuOpened) {
            return;
        }

        if (fromUser) {
            if (mMenuOpened) {
                drawSwipeMenu(direction * getMenuWidth() + dX);
            } else {
                drawSwipeMenu(dX);
            }
        } else if (valueAnimator == null || !valueAnimator.isRunning()) {

            setIsRecyclable(false);

            final float threshold = dX / itemView.getWidth();
            if (Math.abs(threshold) >= getSwipeMenuThreshold()) {
                if (!mMenuOpened) {
                    mMenuOpened = true;
                    direction = Math.signum(dX);
                    startRecoverAnimation(dX, direction * getMenuWidth());
                } else {
                    mMenuOpened = false;
                    direction = 1;
                    startRecoverAnimation(getContentView().getTranslationX(), 0);
                }
            } else {
                if (!mMenuOpened) {
                    startRecoverAnimation(dX, 0);
                } else {
                    startRecoverAnimation(direction * getMenuWidth() + dX, direction * getMenuWidth());
                }
            }

        }
    }

    private void startRecoverAnimation(final float startDx, final float targetDx) {
        valueAnimator = ValueAnimator.ofFloat(startDx, targetDx);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float dx = (float) animation.getAnimatedValue();
                drawSwipeMenu(dx);
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();
    }

    private float getMenuWidth() {
        return getMenuVisiblePart() * itemView.getWidth();
    }

    public float getSwipeMenuThreshold() {
        return 0.2f;
    }

    public float getMenuVisiblePart() {
        return 0.5f;
    }

    abstract public int getMenuDirection();
}
