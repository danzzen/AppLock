package com.myadav.fingerprintlock.Data;

import com.myadav.fingerprintlock.R;

/**
 * Created by lenovo on 28-08-2017.Mohit yadav
 */

public enum ModelObject {

    RED(R.string.red, R.layout.activity_passwordset),
    BLUE(R.string.blue, R.layout.activity_passwordset),
    GREEN(R.string.finl, R.layout.activity_passwordset);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
         mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }
    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}