package mibh.mis.tawamart.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.view.state.BundleSavedState;

public class FirstListItem extends BaseCustomViewGroup {

    TextView tvListNo;
    Button btnMiddle;
    ImageButton ibLast;

    public FirstListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public FirstListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public FirstListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public FirstListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.item_order, this);
    }

    private void initInstances() {
        tvListNo = (TextView) findViewById(R.id.tvListNo);
        btnMiddle = (Button) findViewById(R.id.btnMiddleFirstItem);
        ibLast = (ImageButton) findViewById(R.id.ivLastFirstItem);
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

    public void setListNo(int number) {
        tvListNo.setText(String.valueOf(number));
    }

    public void setTextBtnMiddle(String txt) {
        btnMiddle.setText(txt);
    }

    public void setOnClickMiddle(OnClickListener onClickMiddle) {
        btnMiddle.setOnClickListener(onClickMiddle);
    }

    public void setOnClickLastButton(OnClickListener onClickLastButton) {
        ibLast.setOnClickListener(onClickLastButton);
    }

}
