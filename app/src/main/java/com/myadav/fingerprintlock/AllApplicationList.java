package com.myadav.fingerprintlock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.myadav.fingerprintlock.Adapter.ApplistAdapter;
import com.myadav.fingerprintlock.Data.AppData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by lenovo on 09-08-2017.Mohit yadav
 */

public class AllApplicationList  extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static String requiredAppsType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allapplicationlist);
        setStatusBarColored(this);
        ArrayList<AppData> apps=getListOfInstalledApp(this);
        mRecyclerView=(RecyclerView)findViewById(R.id.mrecyclerview);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new ApplistAdapter(apps,this);
//        setTaskBarColored(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    public static ArrayList<AppData> getListOfInstalledApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList<AppData> installedApps = new ArrayList();

        List<ApplicationInfo> apps = packageManager.getInstalledApplications(0);
        ArrayList<ApplicationInfo> aa=new ArrayList<>();
        for (ApplicationInfo app : apps) {
            if(packageManager.getLaunchIntentForPackage(app.packageName) != null) {
                // apps with launcher intent
                if((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1) {
                    aa.add(app);
                } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                    aa.add(app);
                } else {
                    // user installed apps
                    aa.add(app);
                }
            }

        }
        if (aa != null && !aa.isEmpty()) {

            for (int i = 0; i < aa.size(); i++) {
                ApplicationInfo p = aa.get(i);
                ApplicationInfo appInfo = null;
                try {
                    appInfo = packageManager.getApplicationInfo(p.packageName, 0);
                    AppData app = new AppData();
                    app.setName(p.loadLabel(packageManager).toString());
                    app.setPackageName(p.packageName);
                    app.setVersionName("h");
                    app.setVersionCode(0);
                    app.setIcon(p.loadIcon(packageManager));

                    //check if the application is not an application system
//                    Intent launchIntent = app.getLaunchIntent(context);
//                    if (launchIntent != null && (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                    installedApps.add(app);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

            //sort the list of applications alphabetically
//            if (installedApps.size() > 0) {
//                Collections.sort(installedApps, new Comparator() {
//
//                    @Override
//                    public int compare(final AppInfo app1, final AppInfo app2) {
//                        return app1.getName().toLowerCase(Locale.getDefault()).compareTo(app2.getName().toLowerCase(Locale.getDefault()));
//                    }
//                });
//            }
            return installedApps;
        }
        return null;
    }
    public static void setStatusBarColored(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window w = context.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStatusBarHeight(context);

            View view = new View(context);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.getLayoutParams().height = statusBarHeight;
            ((ViewGroup) w.getDecorView()).addView(view);
            view.setBackground(context.getResources().getDrawable(R.color.colorPrimaryTaskBar));

        }
    }

    public static int getStatusBarHeight(Activity context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
