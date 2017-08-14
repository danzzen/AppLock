package com.myadav.fingerprintlock;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myadav.fingerprintlock.Adapter.ApplistAdapter;
import com.myadav.fingerprintlock.Data.AppData;

import java.util.ArrayList;
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
        ArrayList<AppData> apps=getListOfInstalledApp(this);
        mRecyclerView=(RecyclerView)findViewById(R.id.mrecyclerview);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new ApplistAdapter(apps,this);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    public static ArrayList<AppData> getListOfInstalledApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList<AppData> installedApps = new ArrayList();
        List<PackageInfo> apps = packageManager.getInstalledPackages(0);
        if (apps != null && !apps.isEmpty()) {

            for (int i = 0; i < apps.size(); i++) {
                PackageInfo p = apps.get(i);
                ApplicationInfo appInfo = null;
                try {
                    appInfo = packageManager.getApplicationInfo(p.packageName, 0);
                    AppData app = new AppData();
                    app.setName(p.applicationInfo.loadLabel(packageManager).toString());
                    app.setPackageName(p.packageName);
                    app.setVersionName(p.versionName);
                    app.setVersionCode(p.versionCode);
                    app.setIcon(p.applicationInfo.loadIcon(packageManager));

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
}
