package com.weiche.module_common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.view.View;

/**
 * Created by ${chewei} on 2018/7/26.
 * params:2018/7/26
 */

public class GearView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    //齿轮个数
    private int gearNum = 15;

    //齿轮高度
    private int gearHeight = 50;

    //半径
    private float radius = 200.0f;

    //圆环高度
    private float ringHeight = 80.0f;

    //圆心位置
    private float centerX = 500.0f;
    private float centerY = 500.0f;

    public GearView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        //以三角形作为齿轮（可任意定义齿轮形状）
        Path p = new Path();
        p.moveTo(gearHeight, gearHeight);
        p.lineTo(gearHeight, 0);
        p.lineTo(2 * gearHeight, gearHeight);
        p.close();

        float space = 2 * (float) Math.PI * radius / gearNum;
        PathEffect pathEffect = new PathDashPathEffect(p, space, 0, PathDashPathEffect.Style.MORPH);
        paint.setPathEffect(pathEffect);
        path.addCircle(centerX, centerY, radius, Path.Direction.CW);
        canvas.drawPath(path, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ringHeight);
        paint.setPathEffect(null);

        canvas.drawCircle(centerX, centerY, radius - gearHeight - ringHeight / 2, paint);
    }

}
