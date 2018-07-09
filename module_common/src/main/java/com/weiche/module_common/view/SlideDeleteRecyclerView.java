package com.weiche.module_common.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by ${chewei} on 2018/7/3.
 * params:2018/7/3
 * 支持侧滑删除的RecyclerView
 */

public class SlideDeleteRecyclerView extends RecyclerView {

    private static final int INVALID_POSITION = -1; // 触摸到的点不在子View范围内
    private static final int INVALID_CHILD_WIDTH = -1;  // 子ItemView不含两个子View
    private static final int SNAP_VELOCITY = 600;   // 最小滑动速度

    public int mTouchSlod; //滑动的最小距离（一般由系统提供）
    public Scroller mScroller;
    private Rect mTouchFrame;   // 子View所在的矩形范围
    VelocityTracker mVelocityTracker; //触摸滑动速度类
    public int mFirstX,mFirstY; //首次触摸范围
    public int mLastX; // 滑动过程中记录上次触碰点X
    public int mPosition; //获取触碰点所在的position
    ViewGroup mFlingView; // 触碰的子View
    public int mMenuViewWidth ; //子菜单的宽度
    private boolean mIsSlide;   // 是否滑动子View

    public SlideDeleteRecyclerView(Context context) {
        super(context);
        mTouchSlod = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }

    public SlideDeleteRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlod = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }

    public SlideDeleteRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlod = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }

    public int pointPosition(int x,int y){ //获取点击位置
        int firstPosition = ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
        Rect rect = mTouchFrame;
        if(rect == null){
            mTouchFrame = new Rect();
            rect = mTouchFrame;
        }
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if(child.getVisibility() == View.VISIBLE){
                child.getHitRect(rect);
                if(rect.contains(x,y)){
                    return firstPosition + i;
                }
            }
        }
        return INVALID_POSITION;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        obtainVelocity(e);
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){  //如果动画还没停止，则立即终止动画
                    mScroller.abortAnimation();
                }
                mFirstX = mLastX = x;
                mFirstY = y;
                mPosition = pointPosition(x,y);//获取触碰点所在的position
                if(mPosition != INVALID_POSITION){
                    View mView = mFlingView;
                    // 获取触碰点所在的view
                    mFlingView = (ViewGroup) getChildAt(mPosition - ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition());
                    // 这里判断一下如果之前触碰的view已经打开，而当前碰到的view不是那个view则立即关闭之前的view，此处并不需要担动画没完成冲突，因为之前已经abortAnimation
                    if(mView != null && mFlingView != mView && mView.getScaleX() != 0){
                        mView.scrollTo(0,0);
                    }
                    // 这里进行了强制的要求，RecyclerView的子ViewGroup必须要有2个子view,这样菜单按钮才会有值，
                    // 需要注意的是:如果不定制RecyclerView的子View，则要求子View必须要有固定的width。
                    // 比如使用LinearLayout作为根布局，而content部分width已经是match_parent，此时如果菜单view用的是wrap_content，menu的宽度就会为0。
                    if(mFlingView.getChildCount() == 2){
                        mMenuViewWidth  = mFlingView.getChildAt(1).getWidth();
                    }else{
                        mMenuViewWidth  = INVALID_POSITION;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.computeCurrentVelocity(1000); //computeCurrentVelocity(int)初始化速率的单位，并获得当前的事件的速率，然后使用getXVelocity() 或getXVelocity()获得横向和竖向的速率
                // 此处有俩判断，满足其一则认为是侧滑：
                // 1.如果x方向速度大于y方向速度，且大于最小速度限制；
                // 2.如果x方向的侧滑距离大于y方向滑动距离，且x方向达到最小滑动距离；
                // 注意：之所以要小于负值，是因为向左滑则速度为负值
                float xVelocity = mVelocityTracker.getXVelocity();
                float yVelocity = mVelocityTracker.getYVelocity();
                if((Math.abs(xVelocity) > SNAP_VELOCITY) && (Math.abs(xVelocity) > Math.abs(yVelocity))
                        || Math.abs(x - mFirstX) > mTouchSlod
                        && Math.abs(x - mFirstX) > Math.abs(y - mFirstY)){
                    mIsSlide = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                releaseVelocity();
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(mIsSlide && mPosition != INVALID_POSITION){
            float x = e.getX();
            obtainVelocity(e);
            switch (e.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    // 随手指滑动
                    if(mMenuViewWidth  != INVALID_CHILD_WIDTH){
                        float mX = mLastX - x;
                        if(mFlingView.getScrollX() + mX <= mMenuViewWidth  && mFlingView.getScrollX() + mX > 0){
                            mFlingView.scrollBy((int) mX,0);
                        }
                        mLastX = (int) x;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (mMenuViewWidth  != INVALID_CHILD_WIDTH) {
                        int scrollX = mFlingView.getScrollX();
                        mVelocityTracker.computeCurrentVelocity(1000);
                        // 此处有两个原因决定是否打开菜单：
                        // 1.菜单被拉出宽度大于菜单宽度一半；
                        // 2.横向滑动速度大于最小滑动速度；
                        // 注意：之所以要小于负值，是因为向左滑则速度为负值
                        if (mVelocityTracker.getXVelocity() < -SNAP_VELOCITY) {    // 向左侧滑达到侧滑最低速度，则打开
                            mScroller.startScroll(scrollX, 0, mMenuViewWidth  - scrollX, 0, Math.abs(mMenuViewWidth  - scrollX));
                        } else if (mVelocityTracker.getXVelocity() >= SNAP_VELOCITY) {  // 向右侧滑达到侧滑最低速度，则关闭
                            mScroller.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
                        } else if (scrollX >= mMenuViewWidth  / 2) { // 如果超过删除按钮一半，则打开
                            mScroller.startScroll(scrollX, 0, mMenuViewWidth  - scrollX, 0, Math.abs(mMenuViewWidth  - scrollX));
                        } else {    // 其他情况则关闭
                            mScroller.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
                        }
                        invalidate();
                    }
                    mMenuViewWidth = INVALID_CHILD_WIDTH;
                    mIsSlide = false;
                    mPosition = INVALID_POSITION;
                    releaseVelocity();  // 这里之所以会调用，是因为如果前面拦截了，就不会执行ACTION_UP,需要在这里释放追踪
                    break;
            }
            return true;
        }else{
            // 此处防止RecyclerView正常滑动时，还有菜单未关闭
            closeMenu();
            // Velocity，这里的释放是防止RecyclerView正常拦截了，但是在onTouchEvent中却没有被释放；
            // 有三种情况：1.onInterceptTouchEvent并未拦截，在onInterceptTouchEvent方法中，DOWN和UP一对获取和释放；
            // 2.onInterceptTouchEvent拦截，DOWN获取，但事件不是被侧滑处理，需要在这里进行释放；
            // 3.onInterceptTouchEvent拦截，DOWN获取，事件被侧滑处理，则在onTouchEvent的UP中释放。
            releaseVelocity();
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mFlingView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 将显示子菜单的子view关闭
     * 这里本身是要自己来实现的，但是由于不定制item，因此不好监听器点击事件，因此需要调用者手动的关闭
     */
    public void closeMenu() {
        if (mFlingView != null && mFlingView.getScrollX() != 0) {
            mFlingView.scrollTo(0, 0);
        }
    }

    private void releaseVelocity() {
        if(mVelocityTracker != null){
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public void obtainVelocity(MotionEvent e){  //触摸滑动速度类,跟踪一个touch事件
        if(mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();//使用obtain()方法得到这个类的实例
        }
        mVelocityTracker.addMovement(e);//用addMovement(MotionEvent)函数将你接受到的motion event加入到VelocityTracker类实例中
    }


}
