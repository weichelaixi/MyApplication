package com.weiche.module_girls;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.weiche.module_common.BaseActivity;
import com.weiche.module_common.view.StepArcView;

import com.weiche.module_common.utils.CustomAnimation;

/**
 * Created by ${chewei} on 2018/6/21.
 * params:2018/6/21
 */
@Route(path = "/girls/list")
public class MainGirlsActivity extends BaseActivity implements View.OnClickListener{

    Button btnAlpha;
    Button btnRotate;
    Button btnTranslate;
    Button btnScale;
    Button btnAnimationSet;
    Button btnCustomAnimation;
    Button btnAnimationSetup,btnAnimationStep;
    StepArcView stepArcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_main);

        btnAlpha = $(R.id.btnAlpha);
        btnRotate = $(R.id.btnRotate);
        btnTranslate = $(R.id.btnTranslate);
        btnScale = $(R.id.btnScale);
        btnAnimationSet = $(R.id.btnAnimationSet);
        btnCustomAnimation = $(R.id.btnCustomAnimation);
        btnAnimationSetup = $(R.id.btnAnimationSetup);
        btnAnimationStep = $(R.id.btnAnimationStep);
        stepArcView = $(R.id.sv);

        btnAlpha.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnTranslate.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnAnimationSet.setOnClickListener(this);
        btnCustomAnimation.setOnClickListener(this);
        btnAnimationSetup.setOnClickListener(this);
        btnAnimationStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnAlpha) { //渐变动画
            AlphaAnimation alpha = new AlphaAnimation(0, 1);
            alpha.setDuration(2000);
//            alpha.setFillAfter(true);
            btnAlpha.startAnimation(alpha);
        } else if (i == R.id.btnRotate) { //旋转动画
            RotateAnimation rotate = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(1000);
            btnRotate.startAnimation(rotate);
        } else if (i == R.id.btnTranslate) { //位移动化
            TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f);
            translate.setDuration(1000);
            btnTranslate.startAnimation(translate);
        } else if (i == R.id.btnScale) { //缩放动画
            ScaleAnimation scale = new ScaleAnimation(1, 0, 1, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scale.setDuration(1000);
            btnScale.startAnimation(scale);

        } else if (i == R.id.btnAnimationSet) {
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.setDuration(2000);
            AlphaAnimation another_alpha = new AlphaAnimation(0, 1);
            RotateAnimation another_rotate = new
                    RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animationSet.addAnimation(another_alpha);
            animationSet.addAnimation(another_rotate);
            btnAnimationSet.startAnimation(animationSet);

        } else if(i == R.id.btnCustomAnimation){
            CustomAnimation customAnimation = new CustomAnimation(30,40);
            customAnimation.setDuration(1000);
            btnCustomAnimation.startAnimation(customAnimation);
        } else if(i == R.id.btnAnimationSetup){
            ObjectAnimator animator = ObjectAnimator.ofFloat(btnAnimationSetup,"alpha",1,0,1); //渐变动画
            ObjectAnimator scaleanimator = ObjectAnimator.ofFloat(btnAnimationSetup,"scaleX",1,2,1); //缩放动画
            ObjectAnimator scaleanimator2 = ObjectAnimator.ofFloat(btnAnimationSetup,"scaleY",1,2,1); //缩放动画
            ObjectAnimator translationXanimator = ObjectAnimator.ofFloat(btnAnimationSetup,"translationX",1,200,1); //移动动画
            AnimatorSet set = new AnimatorSet();
            //同时沿X,Y轴放大，且改变透明度，然后移动
            set.play(scaleanimator).with(scaleanimator2).with(animator).before(translationXanimator);
            //都设置3s，也可以为每个单独设置
            set.setDuration(3000);
            set.start();
//            animator.setDuration(2000);
//            animator.setRepeatCount(5); //重复次数
//            animator.start();
        }else if(i == R.id.btnAnimationStep){
            stepArcView.setCurrentAngleLength(10000,5000);
        }
    }
}
