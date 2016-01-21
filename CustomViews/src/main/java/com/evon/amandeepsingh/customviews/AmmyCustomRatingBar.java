package com.evon.amandeepsingh.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by devesh on 5/11/15.
 */
public class AmmyCustomRatingBar extends android.widget.RatingBar {
    private Drawable starDrawable;

    public AmmyCustomRatingBar(Context context) {
        this(context, null);
    }

    public AmmyCustomRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
//        starDrawable = getResources().getDrawable(R.drawable.rating_progress);
        // R.drawable.star_act, context.getTheme());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Make sure to account for padding and margin, if you care.
        // I only cared about left padding.
        setMeasuredDimension(starDrawable.getIntrinsicWidth() * 5
                + getPaddingLeft(), starDrawable.getIntrinsicHeight());
    }
}
