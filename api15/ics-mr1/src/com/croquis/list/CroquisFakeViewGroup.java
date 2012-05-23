package com.croquis.list;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;

public abstract class CroquisFakeViewGroup extends ViewGroup {

    public CroquisFakeViewGroup(Context context) {
        super(context);
    }

    public CroquisFakeViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CroquisFakeViewGroup(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }

    // from View.java

    private float mVerticalScrollFactor;
    protected float getVerticalScrollFactor() {
        if (mVerticalScrollFactor == 0) {
            TypedValue outValue = new TypedValue();
            if (!getContext().getTheme().resolveAttribute(
                    android.R.attr.listPreferredItemHeight, outValue, true)) {
                throw new IllegalStateException(
                        "Expected theme to define listPreferredItemHeight.");
            }
            mVerticalScrollFactor = outValue.getDimension(
                    getContext().getResources().getDisplayMetrics());
        }
        return mVerticalScrollFactor;
    }
    protected boolean performButtonActionOnTouchDown(MotionEvent event) {
        return false;
    }

    protected void invalidateParentIfNeeded() {
        if (isHardwareAccelerated() && getParent() instanceof View) {
            ((View) getParent()).invalidate();
        }
    }

    // from ViewGroup.java

    public void offsetChildrenTopAndBottom(int offset) {
        final int count = getChildCount();

        for (int i = 0; i < count; i++) {
            final View v = getChildAt(i);
            v.offsetTopAndBottom(offset);
        }
    }
    
    // from OverScroller.java
    
    static public boolean isScrollingInDirection(OverScroller scroller, float xvel, float yvel) {
        final int dx = scroller.getFinalX() - scroller.getStartX();
        final int dy = scroller.getFinalY() - scroller.getStartY();
        return !scroller.isFinished() && Math.signum(xvel) == Math.signum(dx) &&
                Math.signum(yvel) == Math.signum(dy);
    }
}
