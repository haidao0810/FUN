package com.example.bf.kf.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bf.kf.R;

/**
 * 类描述：
 * 创建人：ccx
 */
public class HomePageTabLayout extends LinearLayout {
    private ImageView iconImageView;
    private ImageView mUnReadMessageFlagView;
    private TextView textView;

    public HomePageTabLayout(Context mContext) {
        this(mContext, null);
    }

    public HomePageTabLayout(Context mContext, AttributeSet atts) {
        this(mContext, atts, 0);
    }

    public HomePageTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {
        this.setOrientation(VERTICAL);
        this.setGravity(Gravity.CENTER);
        addView(createImageView(mContext));
        addView(createTextView(mContext));
        //addView(createUnReadMessageFlagView(mContext));
    }

    private View createUnReadMessageFlagView(Context pMContext) {
        mUnReadMessageFlagView = new ImageView(pMContext);
        return mUnReadMessageFlagView;
    }

    private ImageView createImageView(Context context) {
        iconImageView = new ImageView(context);
        LayoutParams layoutParams = new LayoutParams(context.getResources().getDimensionPixelOffset(R.dimen.icon_size), context.getResources().getDimensionPixelOffset(R.dimen.icon_size));
        iconImageView.setLayoutParams(layoutParams);
        return iconImageView;
    }

    private TextView createTextView(Context context) {
        textView = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        return textView;
    }

    public void setViewColorAndBackground(int resId,int colorId){
        iconImageView.setImageResource(resId);
        textView.setTextColor(colorId);
    }


    public void setTextViewText(String text) {
        textView.setText(text);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        iconImageView  = null;
        textView = null;
    }
}
