package com.allreminder.adaptor;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.allreminder.activity.MainActivity;
import com.allreminder.activity.R;
import com.allreminder.customanimation.BatteryStatus;

/**
 * Created by vijay on 12-08-2017.
 */

public class ToolAdaptor extends BaseAdapter {
    private Intent intent;
    private Context context;
    private String item;
    private int resource;

    public ToolAdaptor(Context context, Intent intent, int resource, String item) {
        this.context = context;
        this.intent = intent;
        this.resource = resource;
        this.item = item;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        System.out.println(layoutInflator);
        view = layoutInflator.inflate(resource, viewGroup, false);
        TextView itemName = (TextView) view.findViewById(R.id.tool_name);
        ImageView image = (ImageView) view.findViewById(R.id.tool_image);
        itemName.setText(this.item);
        Bitmap bitmap = Bitmap.createBitmap(image.getWidth()+ 150,image.getHeight()+ 250, Bitmap.Config.ARGB_8888);
        BatteryStatus batteryStatus= new BatteryStatus(this.context,this.intent,image);
//        batteryStatus.invalidate();
//        Canvas c = new Canvas(bitmap);
//        batteryStatus.draw(c);
//        Canvas c = new Canvas(bitmap);
//        image.draw(c);

//        Paint p = new Paint();
//        p.setColor(Color.GREEN);
//        c.drawLine(0, 10, 10, 10, p);
//        image.draw(c);
       // image.setImageBitmap(batteryStatus.getBitmap());

//        image.setImageBitmap(bitmap);
//        image.setImageResource(R.drawable.ic_menu_camera);
        return view;
    }


}
