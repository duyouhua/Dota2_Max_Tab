package xyz.isunxu.dota2_max_tab.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sunxu on 16/3/14.
 */
public class TriangleTabView extends LinearLayout {

    private Path mPath;

    private Paint mPaint;

    private int mTranslationX;

    private int mTriangleWidth;
    private int mTriangleHeight;

    private int initTranslationX ;

    private float scale = 1/6f;


    public TriangleTabView(Context context) {
        this(context,null);
    }


    public TriangleTabView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public TriangleTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData();
    }


    private void initData() {

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ffffffff"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));

    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth = (int) (w / 4 * scale);
        mTriangleHeight =  (int) (mTriangleWidth / 2 * (3 / 5f));

        initTranslationX = w / 4 / 2 - (mTriangleWidth/2);

        initTriangle();
    }


    private void initTriangle() {

        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth/2, - mTriangleHeight);
        mPath.close();

    }


    @Override protected void dispatchDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(initTranslationX + mTranslationX, getHeight());
        canvas.drawPath(mPath,mPaint);
        canvas.restore();

        super.dispatchDraw(canvas);

    }


    public void scroll(int position, float positionOffset) {
        mTriangleWidth = getWidth() / 4;

        mTranslationX = (int) (position * mTriangleWidth + positionOffset * mTriangleWidth);

        invalidate();
    }


}

