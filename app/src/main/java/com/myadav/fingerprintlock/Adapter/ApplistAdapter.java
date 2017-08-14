package com.myadav.fingerprintlock.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myadav.fingerprintlock.Data.AppData;
import com.myadav.fingerprintlock.R;
import com.myadav.fingerprintlock.Utils.SharedPrefrence;

import java.util.ArrayList;

/**
 * Created by lenovo on 09-08-2017.Mohit yadav
 */

public class ApplistAdapter extends RecyclerView.Adapter<ApplistAdapter.rowholder> {
   private ArrayList<AppData> mList;
   private Context context;
    private int pos[];
   private ArrayList<String> apps;
   private SharedPrefrence sharedPrefrence;
    public ApplistAdapter(ArrayList<AppData> mList, Context context) {
        this.mList = mList;
        this.context = context;
        sharedPrefrence=new SharedPrefrence();
        apps=new ArrayList<>();
    }

    @Override
    public ApplistAdapter.rowholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_applist,parent, false);
        return new ApplistAdapter.rowholder(v);
    }

    @Override
    public void onBindViewHolder(final ApplistAdapter.rowholder holder, final int position) {
        holder.appName.setText(mList.get(position).getName());
        holder.appimg.setImageDrawable(mList.get(position).getIcon());
        apps=sharedPrefrence.getLocked(context);
        holder.checkBox.setOnCheckedChangeListener(null);
        if(apps!=null) {
            for (int i = 0; i < apps.size(); i++) {
                if (apps.get(i).equals(mList.get(position).getPackageName())) {
                    mList.get(position).setSelectedposition(position);
                    holder.checkBox.setEnabled(true);
                }

            }
        }
        holder.checkBox.setChecked(mList.get(position).getSelectedposition() == position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mList.get(position).setSelectedposition(position);
                    holder.checkBox.setChecked(mList.get(position).getSelectedposition()==position);
                    sharedPrefrence.addLocked(context,mList.get(position).getPackageName());
                }
                else {
                    mList.get(position).setSelectedposition(-1);
                    holder.checkBox.setChecked(mList.get(position).getSelectedposition()==position);
                    sharedPrefrence.removeLocked(context,mList.get(position).getPackageName());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class rowholder extends RecyclerView.ViewHolder
    {
        TextView appName;
        CheckBox checkBox;
        ImageView appimg;
        public rowholder(View itemView)
        {
            super(itemView);
            appimg=(ImageView)itemView.findViewById(R.id.appicon);
            appName=(TextView)itemView.findViewById(R.id.appname);
            checkBox=(CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
