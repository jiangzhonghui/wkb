package com.apj.wkb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by student on 2014/11/29.
 */
public class ScrollGridView extends GridView {
    public ScrollGridView(Context paramContext){
        super(paramContext);
    }
    public ScrollGridView(Context paramContext, AttributeSet paramAttributeSet){
        super(paramContext, paramAttributeSet);
    }

    public ScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec( Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
