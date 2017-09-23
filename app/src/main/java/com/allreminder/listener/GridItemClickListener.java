package com.allreminder.listener;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.allreminder.activity.AlarmActivity;
import com.allreminder.activity.MainActivity;

/**
 * Created by vijay on 23-09-2017.
 */

public class GridItemClickListener implements AdapterView.OnItemClickListener {
    Context context;
    public GridItemClickListener(Context applicationContext) {
        this.context=applicationContext;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(context,id+"",Toast.LENGTH_SHORT).show();
//        view.findViewById(id)
        if(id==0){

        }else if(id == 1){
            Intent intent = new Intent(this.context, AlarmActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);
        }else if(id == 2){

        }else if(id == 3){

        }else if(id == 4){

        }
    }
}
