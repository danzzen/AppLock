package com.myadav.fingerprintlock.Data;

import com.myadav.fingerprintlock.R;

/**
 * Created by lenovo on 28-08-2017.Mohit yadav
 */

public enum ModelObject {

    RED(R.string.red, R.layout.pagerpasslayout),
    BLUE(R.string.blue, R.layout.pagerpasslayout),
    GREEN(R.string.finl, R.layout.pagerpasslayout);

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