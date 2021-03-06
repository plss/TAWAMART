package mibh.mis.tawamart.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.view.state.BundleSavedState;

public class SecondListItem extends BaseCustomViewGroup {

    Button btnMiddle;
    ImageButton btnLast;

    public SecondListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public SecondListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public SecondListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public SecondListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.item_second, this);
    }

    private void initInstances() {
        btnMiddle = (Button) findViewById(R.id.btnMiddleSecondItem);
        btnLast = (ImageButton) findViewById(R.id.btnLastSecondItem);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setTextBtnMiddle(String txt) {
        btnMiddle.setText(txt);
    }

    public void setOnClickBtnMiddle(OnClickListener onClickBtnMiddle) {
        btnMiddle.setOnClickListener(onClickBtnMiddle);
    }

    public void setSetBgSelectedBtnMiddle(boolean state) {
        if (state)
            btnMiddle.setBackgroundColor(Color.parseColor("#d6d7d7"));
        else {
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
            TypedArray ta = getContext().obtainStyledAttributes(attrs);
            Drawable drawableFromTheme = ta.getDrawable(0);
            ta.recycle();
            btnMiddle.setBackground(drawableFromTheme);
        }
    }

    public void setOnClickBtnLast(OnClickListener onClickBtnLast) {
        btnLast.setOnClickListener(onClickBtnLast);
    }

}
