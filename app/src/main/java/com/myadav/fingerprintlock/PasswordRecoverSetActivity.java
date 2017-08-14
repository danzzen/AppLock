package com.myadav.fingerprintlock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by lenovo on 12-08-2017.Mohit yadav
 */

public class PasswordRecoverSetActivity extends AppCompatActivity {
    Spinner spinner;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button confrm;
    EditText answer;
    int pos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setrecoverpassword);
        spinner = (Spinner) findViewById(R.id.spinner);
        confrm=(Button)findViewById(R.id.confirm2);
        answer=(EditText)findViewById(R.id.answer);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.questions_array, R.layout.simple_spinner_item_custom);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        sharedPreferences = getSharedPreferences(AppLockConstants.MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(PasswordRecoverSetActivity.this,adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                spinner.setSelection(i);
                pos=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        confrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(pos!=0&&!answer.getText().toString().trim().isEmpty()){
                   editor.putBoolean(AppLockConstants.IS_PASSWORD_SET,true);
                   editor.apply();
                   editor.commit();
                   editor.putString(AppLockConstants.ANSWER,answer.getText().toString().trim());
                   editor.apply();
                   editor.commit();
                   editor.putInt(AppLockConstants.QUESTION_NUMBER,pos);
                   editor.apply();
                   editor.commit();
                   Intent i =new Intent(PasswordRecoverSetActivity.this,AllApplicationList.class);
                   startActivity(i);
                   finish();
               }
               else
               {
                   Toast.makeText(getApplicationContext(), "Please select a question and write an answer", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}
