package com.evon.amandeepsingh.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by amandeep on 14/10/15.
 */
public class AmmyTextView extends TextView {

    private String mFont;

    public AmmyTextView(Context context) {
        super(context, null);
        init(context);
    }


    public AmmyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewAmmy,
                0, 0);
        try {
            mFont = a.getString(R.styleable.ViewAmmy_font);
        } finally {
            a.recycle();
        }
        init(context);
    }

    public AmmyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewAmmy,
                0, 0);

        try {
            mFont = a.getString(R.styleable.ViewAmmy_font);
        } finally {
            a.recycle();
        }
        init(context);
    }

    private void init(Context context) {
        if (mFont != null) {
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + mFont);
            setTypeface(tf);
        }
    }

}
