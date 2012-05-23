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
    
    // from Scroller.java

    private static float sViscousFluidScale;
    private static float sViscousFluidNormalize;

    static {
        // This controls the viscous fluid effect (how much of it)
        sViscousFluidScale = 8.0f;
        // must be set to 1.0 (used in viscousFluid())
        sViscousFluidNormalize = 1.0f;
        sViscousFluidNormalize = 1.0f / viscousFluid(1.0f);
    }

    static float viscousFluid(float x)
    {
        x *= sViscousFluidScale;
        if (x < 1.0f) {
            x -= (1.0f - (float)Math.exp(-x));
        } else {
            float start = 0.36787944117f;   // 1/e == exp(-1)
            x = 1.0f - (float)Math.exp(1.0f - x);
            x = start + x * (1.0f - start);
        }
        x *= sViscousFluidNormalize;
        return x;
    }
}
