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
import mibh.mis.tawamart.adapter.OrderListAdapter;
import mibh.mis.tawamart.view.state.BundleSavedState;

public class ReceiveListItem extends BaseCustomViewGroup {

    private Button btnTitle, btnComplete, btnIncomplete, btnDeny;
    private TextView tvListNo;

    public ReceiveListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ReceiveListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ReceiveListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ReceiveListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.item_receive, this);
    }

    private void initInstances() {
        btnTitle = (Button) findViewById(R.id.btnTitleListItemReceive);
        btnComplete = (Button) findViewById(R.id.btnCompleteListItemReceive);
        btnIncomplete = (Button) findViewById(R.id.btnInCompleteListItemReceive);
        btnDeny = (Button) findViewById(R.id.btnDenyListItemReceive);
        tvListNo = (TextView) findViewById(R.id.tvListItemNoReceive);
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

    public void setListNo(String txt) {
        tvListNo.setText(txt);
    }

    public void setTextBtnTitle(String txt) {
        btnTitle.setText(txt);
    }

    public void setStateBtn(String state) {

        int[] attrs = new int[]{android.R.attr.selectableItemBackground};
        TypedArray ta = getContext().obtainStyledAttributes(attrs);
        Drawable drawableFromTheme = ta.getDrawable(0);
        ta.recycle();
        btnComplete.setBackground(drawableFromTheme);
        btnIncomplete.setBackground(drawableFromTheme);
        btnDeny.setBackground(drawableFromTheme);

        if (state == null) {

        } else if (state.equals("COMPLETE")) {
            btnComplete.setBackgroundColor(Color.parseColor("#5cb85c"));
        } else if (state.equals("INCOMPLETE")) {
            btnIncomplete.setBackgroundColor(Color.parseColor("#f0ad4e"));
        } else if (state.equals("DENY")) {
            btnDeny.setBackgroundColor(Color.parseColor("#dd4b39"));
        }
    }

    public void setOnClickBtnComplete(OnClickListener onClickBtnComplete) {
        this.btnComplete.setOnClickListener(onClickBtnComplete);
    }

    public void setOnClickBtnIncomplete(OnClickListener onClickBtnIncomplete) {
        this.btnIncomplete.setOnClickListener(onClickBtnIncomplete);
    }

    public void setOnClickBtnDeny(OnClickListener onClickBtnDeny) {
        this.btnDeny.setOnClickListener(onClickBtnDeny);
    }

    public void setOnClickBtn(OnClickListener onClickBtn) {
        this.btnDeny.setOnClickListener(onClickBtn);
        this.btnIncomplete.setOnClickListener(onClickBtn);
        this.btnComplete.setOnClickListener(onClickBtn);
    }

    public Button getBtnDeny() {
        return btnDeny;
    }

    public Button getBtnComplete() {
        return btnComplete;
    }

    public Button getBtnIncomplete() {
        return btnIncomplete;
    }
}
