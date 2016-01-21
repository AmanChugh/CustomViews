package com.evon.amandeepsingh.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * View Pager Child custom class to disable Swipe
 * Created by amandeepsingh on 25/11/15.
 */
public class AmmyViewPager extends ViewPager {
    private boolean isPagingEnabled;

    public AmmyViewPager(Context context) {
        super(context);
    }

    public AmmyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}
