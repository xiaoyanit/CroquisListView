package com.croquis.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

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

    // from ViewGroup.java

    public void offsetChildrenTopAndBottom(int offset) {
        final int count = getChildCount();

        for (int i = 0; i < count; i++) {
            final View v = getChildAt(i);
            v.offsetTopAndBottom(offset);
        }
    }
}
