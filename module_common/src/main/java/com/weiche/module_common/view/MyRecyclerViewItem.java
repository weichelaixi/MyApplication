package com.weiche.module_common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.weiche.module_common.utils.KeepLog;

/**
 * Created by ${chewei} on 2018/7/3.
 * params:2018/7/3
 */

public class MyRecyclerViewItem extends HorizontalScrollView {

    private boolean isLeft=true;//在左边

    public MyRecyclerViewItem(Context context) {
        super(context);
        init();
    }

    public MyRecyclerViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRecyclerViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int buttonWidth;
    private DisplayMetrics scale;

    private void init() {
        scale = getContext().getResources().getDisplayMetrics();
        buttonWidth = dp2px(60);// 布局的宽度
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        KeepLog.e(KeepLog.TAG,"执行"+"onLayout");

        //调整布局
        LinearLayout linearLayout= (LinearLayout) getChildAt(0);
        RelativeLayout linearLayoutLeft = (RelativeLayout) linearLayout.getChildAt(0);
        LinearLayout linearLayoutRight = (LinearLayout) linearLayout.getChildAt(1);
        linearLayout.layout(linearLayout.getLeft(), linearLayout.getTop(), linearLayout.getLeft()+getMeasuredWidth()+ buttonWidth, linearLayout.getBottom());
        linearLayoutLeft.layout(linearLayoutLeft.getLeft(), linearLayoutLeft.getTop(),linearLayoutLeft.getLeft()+getMeasuredWidth(), linearLayoutLeft.getBottom());
        linearLayoutRight.layout(linearLayoutLeft.getLeft()+getMeasuredWidth(), linearLayoutLeft.getTop(), linearLayoutLeft.getLeft()+getMeasuredWidth()+ buttonWidth, linearLayoutLeft.getBottom());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        KeepLog.e(KeepLog.TAG,"执行"+"onMeasure");
        //调整布局
        LinearLayout linearLayout= (LinearLayout) getChildAt(0);
        RelativeLayout linearLayoutLeft = (RelativeLayout) linearLayout.getChildAt(0);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayoutLeft.getLayoutParams();
        params.width = width;
        linearLayoutLeft.setLayoutParams(params);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //恢复状态
    public void reset(){
        isLeft=true;
        scrollTo(0,0);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction()== MotionEvent.ACTION_DOWN)
        {
            Log.i(getClass().getSimpleName(), "down");
            return true;
        }
        if (ev.getAction()==MotionEvent.ACTION_CANCEL || ev.getAction()== MotionEvent.ACTION_UP)
        {
            Log.i(getClass().getSimpleName(), "up");
            int range=70;
            if (isLeft)
            {
                if (getScrollX()>range)
                {
                    isLeft=false;
                    smoothScrollTo(buttonWidth, 0);
                }else {
                    smoothScrollTo(0,0);
                }
            }else {
                if (getScrollX()< (buttonWidth-range))
                {
                    isLeft=true;
                    smoothScrollTo(0,0);
                }else {
                    smoothScrollTo(buttonWidth, 0);
                }
            }
            return true;
        }
        Log.i(getClass().getSimpleName(), "end");
        return super.onTouchEvent(ev);
    }


    private int dp2px(float dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, scale);
    }

}
