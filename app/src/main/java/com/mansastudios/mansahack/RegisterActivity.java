package com.mansastudios.mansahack;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    EditText mob,pass;
    String mobile,password;
    Button register;
   // ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mob=findViewById(R.id.registerMobile);
        pass=findViewById(R.id.registerPassword);
        register=findViewById(R.id.submitRegister);
       // progressBar=findViewById(R.id.registerProgress);
    }

    public void Register(View view) {
        mobile=mob.getText().toString();
        password=pass.getText().toString();
        if(mobile.equals("")||password.equals(""))
        {
            Toast.makeText(this, "Invalid Data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            register.setClickable(false);
            try {
                //progressBar.setVisibility(View.VISIBLE);
                JSONObject userDetails = new JSONObject();
                userDetails.put("mobileNo",mobile);
                userDetails.put("password",password);
                RegisterTask task = new RegisterTask();
                Log.i("REGISTER JSON DATA", userDetails.toString());
                task.execute(LoginActivity.MAINURL+"/users/signup", userDetails.toString());
            } catch (Exception e) {
               // progressBar.setVisibility(View.INVISIBLE);
                Log.i("REGISTER ERROR", e.getMessage());
                register.setClickable(true);
            }
        }

    }
    public class RegisterTask extends AsyncTask<String,Void,String>
    {
        public void enableButton()
        {
            register.setClickable(true);
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url=new URL(params[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.addRequestProperty("Accept","application/json");
                connection.addRequestProperty("Content-Type","application/json");
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.connect();
                DataOutputStream outputStream=new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(params[1]);
                int resp=connection.getResponseCode();
                Log.i("REGISTER RESP",String.valueOf(resp));
                if(resp==200) {
                    return String.valueOf(resp);
                }
                else {
                    enableButton();
                    return String.valueOf(resp);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("Register",e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
           // progressBar.setVisibility(View.INVISIBLE);
           // Toast.makeText(RegisterActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            if(s!=null)
            {
                if(s.equals("200"))
                {
                    onBackPressed();
                    Toast.makeText(getApplicationContext(), "Registered...", Toast.LENGTH_SHORT).show();
                }
                else if(s.equals("422"))
                    Toast.makeText(getApplicationContext(), "Already Exists", Toast.LENGTH_SHORT).show();

            }
            else Toast.makeText(RegisterActivity.this, "Please Check Your Internet and Try Again!", Toast.LENGTH_SHORT).show();

        }
    }
}
