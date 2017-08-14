package com.myadav.fingerprintlock.Custom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.myadav.fingerprintlock.AllApplicationList;
import com.myadav.fingerprintlock.AppLockConstants;
import com.myadav.fingerprintlock.PasswordRecoverSetActivity;
import com.myadav.fingerprintlock.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenovo on 14-08-2017. Mohit yadav
 */

public class CustomDialog extends DialogFragment {
    public CustomDialog(){}
    private RadioGroup radioGroup;
    private EditText editText;
    private Button save;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.dialog_layout,container,false);
        getDialog().setTitle("Select a Security Question");
        radioGroup=(RadioGroup)mView.findViewById(R.id.radiogroup);
        radioGroup.check(R.id.radioButton);
        editText=(EditText)mView.findViewById(R.id.answer);
        sharedPreferences = this.getActivity().getSharedPreferences(AppLockConstants.MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Button mDoneBtn = (Button) mView.findViewById(R.id.savebtn);
        mDoneBtn.setOnClickListener(DoneAction);
        return mView;
    }

    View.OnClickListener DoneAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Toast.makeText(getActivity(),"Test",Toast.LENGTH_LONG).show();
            if(!editText.getText().toString().isEmpty()){
                if(radioGroup.getCheckedRadioButtonId()!=-1){
                    int id= radioGroup.getCheckedRadioButtonId();
                    View radioButton = radioGroup.findViewById(id);
                    int radioId = radioGroup.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton)radioGroup.getChildAt(radioId);
                    String selection = (String) btn.getText();
                    editor.putBoolean(AppLockConstants.IS_PASSWORD_SET,true);
                    editor.apply();
                    editor.commit();
                    editor.putString(AppLockConstants.ANSWER,editText.getText().toString().trim());
                    editor.apply();
                    editor.commit();
                    editor.putString(AppLockConstants.QUESTION,selection);
                    editor.apply();
                    editor.commit();
                    Intent i =new Intent(getActivity(),AllApplicationList.class);
                    startActivity(i);
                    getActivity().finish();
                }
            }
            else{
                Toast.makeText(getActivity(), "Provide a Answer", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
