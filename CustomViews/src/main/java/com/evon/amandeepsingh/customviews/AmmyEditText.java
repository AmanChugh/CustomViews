package com.evon.amandeepsingh.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by amandeep on 14/10/15.
 */
public class AmmyEditText extends EditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    private String mFont;
    private int drawableId;
    private Drawable clrIcon;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;
    private boolean hasClearDrawable = false;

    public AmmyEditText(Context context) {
        super(context, null);
        init(context);
    }


    public AmmyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewAmmy,
                0, 0);
        try {
            mFont = a.getString(R.styleable.ViewAmmy_font);
            drawableId = a.getResourceId(R.styleable.ViewAmmy_clear_drawable, 0);
        } finally {
            a.recycle();
        }
        init(context);
    }

    public AmmyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewAmmy,
                0, 0);

        try {
            mFont = a.getString(R.styleable.ViewAmmy_font);
//            drawableId=a.getInt(R.styleable.ViewShofur_clear_drawable, 0);
            drawableId = a.getResourceId(R.styleable.ViewAmmy_clear_drawable, 0);
        } finally {
            a.recycle();
        }
        init(context);
    }

    private void init(Context context) {
        if (mFont != null && !mFont.isEmpty()) {
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + mFont);
            setTypeface(tf);
        }
        if (drawableId != 0) {
            initClearEditText(context);
        }
    }

    private void initClearEditText(Context context) {
        hasClearDrawable = true;
        final Drawable drawable = ContextCompat.getDrawable(context, drawableId);
//        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable); //Wrap the drawable so that it can be tinted pre Lollipop
//        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        clrIcon = drawable;
        clrIcon.setBounds(0, 0, clrIcon.getIntrinsicWidth(), clrIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        mOnFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    private void setClearIconVisible(final boolean visible) {
        if (hasClearDrawable) {
            clrIcon.setVisible(visible, false);
            final Drawable[] compoundDrawables = getCompoundDrawables();
            setCompoundDrawables(
                    compoundDrawables[0],
                    compoundDrawables[1],
                    visible ? clrIcon : null,
                    compoundDrawables[3]);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        if(hasClearDrawable) {
        final int x = (int) event.getX();
        if (clrIcon.isVisible() && x > getWidth() - getPaddingRight() - clrIcon.getIntrinsicWidth()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                setText("");
            }
            return true;
        }
//        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(v, event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
//        if(hasClearDrawable) {
        if (isFocused()) {
            setClearIconVisible(text.length() > 0);
        }
    }
//    }

}
