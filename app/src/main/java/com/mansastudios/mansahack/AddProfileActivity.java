package com.mansastudios.mansahack;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;

public class AddProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView datePickerTv;
    EditText nameEt,profEt;
    String name,prof,gender,auth;
    RadioGroup rg;
    RadioButton maleButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        datePickerTv=findViewById(R.id.DOBDatePicker);
        nameEt=findViewById(R.id.nameInput);
        profEt=findViewById(R.id.proffessionInput);
        rg=findViewById(R.id.registerGenderRadioGroup);
        maleButton=findViewById(R.id.maleRadioBtn);
        progressBar=findViewById(R.id.addProfileProgress);
        maleButton.setChecked(true);
        gender="m";
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.maleRadioBtn)
                    gender="m";
                else if(i==R.id.femaleRadioBtn)
                    gender="f";
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Calendar c=Calendar.getInstance();
        String year= String.valueOf(c.get(Calendar.YEAR));
        String month= String.valueOf(c.get(Calendar.MONTH)+1);
        if(month.length()==1)
        {
            month="0"+month;
        }
        String day= String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        if(day.length()==1)
        {
            day="0"+day;
        }
        datePickerTv.setText(year+"-"+month+"-"+day);
        datePickerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment df=new DatePickerFragment();
                df.show(getSupportFragmentManager(),"Choose Date");

            }
        });
    }

    public void AddProfile(View view) {
        Log.i("btnClick","addProfile");
        JSONObject profileObject=new JSONObject();
        auth=LoginActivity.sharedPreferences.getString("auth",null);
        if(auth!=null) {
            name = nameEt.getText().toString();
            prof = profEt.getText().toString();
            if (name.equals("") || prof.equals("")) {
                Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    profileObject.put("auth", auth);
                    profileObject.put("name", name);
                    profileObject.put("profession", prof);
                    profileObject.put("dob", datePickerTv.getText().toString());
                    profileObject.put("gender", gender);
                    AddProfileTask task=new AddProfileTask(getApplicationContext(), new DataCallBack() {
                        @Override
                        public void datacallBack(boolean response, String resp) {
                            if(response)
                            {
                                if(resp.equals("422"))
                                {
                                }
                                else
                                {
                                    onBackPressed();
                                }
                            }
                        }
                    },0);
                    task.execute(LoginActivity.MAINURL+"/profiles/addProfile",profileObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            Log.i("AddProfile","auth is null");
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);
        //String date=DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        String year= String.valueOf(c.get(Calendar.YEAR));
        String month= String.valueOf(c.get(Calendar.MONTH)+1);
        if(month.length()==1)
        {
            month="0"+month;
        }
        String day= String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        if(day.length()==1)
        {
            day="0"+day;
        }
        datePickerTv.setText(year+"-"+month+"-"+day);
    }
}
