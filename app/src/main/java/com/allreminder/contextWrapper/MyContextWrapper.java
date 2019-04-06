package com.allreminder.contextWrapper;

import android.content.Context;
import android.content.ContextWrapper;

public class MyContextWrapper extends ContextWrapper {

    public MyContextWrapper(Context base) {
        super(base);
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
}