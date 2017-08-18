package com.allreminder.customanimation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.view.View;
import android.widget.ImageView;

import static android.graphics.Paint.Align.CENTER;

/**
 * Created by vijay on 15-08-2017.
 */

public class BatteryStatus extends View implements Drawable.Callback {
    Context context;
    private ImageView image;
    private Intent batteryStatus;
    private int height;
    private int width;
    private Rect batteryHead;
    private Bitmap bitmap;
    private Rect batteryBody;
    private Paint paint;
    private int batteryLeft = 10;
    private int batteryRight = 140;
    private int batteryTop = 40;
    private int batteryBottom = 240;
    private int batteryStep = 2;
    private Rect batteryBlood;
    private Drawable mDrawable;
    private Canvas canvas;
    private RectF batteryHeadRectF=new RectF();
    private RectF batteryBloodRectF =new RectF();
    private RectF batteryBodyRectF = new RectF();
    public BatteryStatus(Context context, Intent intent, ImageView image) {
        super(context);
        this.context = context;
        batteryBody = new Rect();
        batteryHead = new Rect();
        batteryBlood = new Rect();
        paint = new Paint();
        this.image = image;
        this.width = 150;

        this.height = 250;
        batteryStatus = intent;
        this.bitmap = Bitmap.createBitmap(150, 250, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        this.draw(canvas);
        System.out.println("inside constructor");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.bitmap = Bitmap.createBitmap(150, 250, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    public void setDrawable(Drawable d) {

    }

    @Override
    public void onDraw(Canvas canvas) {
        this.canvas = canvas;
        int batteryCenter = (batteryRight + batteryLeft) / 2;
        System.out.println(batteryCenter);
        batteryHead.set(batteryCenter - 20, 20, batteryCenter + 20, 40);
        batteryBody.set(batteryLeft, batteryTop, batteryRight, batteryBottom);
        batteryHeadRectF.set(batteryHead);
        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(batteryHeadRectF, 5, 5, paint);

        int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
        int plugged = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1):-1;

//        float batteryPct = level / (float) scale;

        batteryBlood.set(batteryLeft + 2, batteryBottom - (level * batteryStep) + 1, batteryRight - 1, batteryBottom - 1);

        if (level <= 30)
            paint.setColor(Color.RED);
        else if (level <= 50)
            paint.setColor(Color.YELLOW);
        else
            paint.setColor(Color.GREEN);

        batteryBloodRectF.set(batteryBlood);
        canvas.drawRoundRect(batteryBloodRectF, 10, 10, paint);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);

        batteryBodyRectF.set(batteryBody);
        canvas.drawRoundRect(batteryBodyRectF, 10, 10, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(30);
        paint.setTextAlign(CENTER);
        if (plugged > 0)
            canvas.drawText(String.valueOf(level), batteryCenter, (batteryTop + batteryBottom) / 2, paint);
        image.setImageBitmap(getBitmap());
        postInvalidate();
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }
}
