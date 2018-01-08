package cn.cnlinfo.ccf.view;


import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.orhanobut.logger.Logger;

/**
 * Created by cj on 2016/9/20.
 */


public class MyNestedScrollView extends NestedScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;

    public MyNestedScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                Logger.d(downX+":"+downY);
                break;
                case MotionEvent.ACTION_MOVE:
                    int moveY = (int) e.getRawY();
                    if (Math.abs(moveY - downY) > mTouchSlop) {
                        Logger.d(moveY+":"+downY+":"+mTouchSlop);
                        return false;
                    }
        }
        return super.onInterceptTouchEvent(e);
    }
}

