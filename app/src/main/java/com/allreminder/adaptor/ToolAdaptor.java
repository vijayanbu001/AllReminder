package com.allreminder.adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allreminder.activity.R;
import com.allreminder.customanimation.BatteryStatus;

/**
 * Created by vijay on 12-08-2017.
 */

public class ToolAdaptor extends BaseAdapter {
    private int[] itemImage;
    private Intent intent;
    private Context context;
    private String[] itemName;
    private int resource;

    public ToolAdaptor(Context context, Intent intent, int resource, String[] itemName,int[] itemImage) {
        this.context = context;
        this.intent = intent;
        this.resource = resource;
        this.itemName = itemName;
        this.itemImage = itemImage;
    }

    @Override
    public int getCount() {
        return itemName.length;
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
        ImageView itemImage = (ImageView) view.findViewById(R.id.tool_image);
        AnalogClock analogClock = (AnalogClock) view.findViewById(R.id.clock);
        itemName.setText(this.itemName[i]);
        itemImage.setImageDrawable(this.context.getResources().getDrawable(R.drawable.alarm_clock_img));
        if(itemName.getText().toString().equalsIgnoreCase("battery")) {
//            itemImage.setPadding(0,0,0,0);
            Bitmap bitmap = Bitmap.createBitmap(itemImage.getWidth() + 150, itemImage.getHeight() + 250, Bitmap.Config.ARGB_8888);
            BatteryStatus batteryStatus = new BatteryStatus(this.context, this.intent, itemImage);

        } else if(itemName.getText().toString().equalsIgnoreCase("alarm")){
            itemImage.setVisibility(View.INVISIBLE);
            analogClock.setVisibility(View.VISIBLE);
        }

//        batteryStatus.invalidate();
//        Canvas c = new Canvas(bitmap);
//        batteryStatus.draw(c);
//        Canvas c = new Canvas(bitmap);
//        itemImage.draw(c);

//        Paint p = new Paint();
//        p.setColor(Color.GREEN);
//        c.drawLine(0, 10, 10, 10, p);
//        itemImage.draw(c);
       // itemImage.setImageBitmap(batteryStatus.getBitmap());

//        itemImage.setImageBitmap(bitmap);
//        itemImage.setImageResource(R.drawable.ic_menu_camera);
        return view;
    }
}
