package com.mansastudios.mansahack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    EditText mobNo,password;
    ProgressBar pbar;
    String mobileNo,passwd;
    Button loginButton;
    String accessToken=null;
    int resp;
    public static SharedPreferences sharedPreferences;
    public static final String MAINURL="http://ec2-13-233-144-22.ap-south-1.compute.amazonaws.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences=this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        Log.i("isLoggedIn",""+sharedPreferences.getBoolean("isLoggedIn",false));
        mobNo=findViewById(R.id.loginMobileNo);
        password=findViewById(R.id.passwordInput);
        pbar=findViewById(R.id.loginProgress);
        loginButton=findViewById(R.id.loginButton);
        /*if(sharedPreferences.getBoolean("isLoggedIn",false)==true)
        {
            goToProfile();
        }*/
    }
    void setToken(String auth)
    {
        try {
            JSONObject object=new JSONObject(auth);
            String token=object.getString("token");
            sharedPreferences.edit().putString("auth",token).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    void goToProfile()
    {
        Intent profileIntent=new Intent(getApplicationContext(),SelectProfileActivity.class);
        startActivity(profileIntent);
        finish();
    }
    public class LoginTask extends AsyncTask<String,Integer,String>
    {
        public void enableButton()
        {
            loginButton.setClickable(true);
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
                Log.i("LOGIN JSON DATA",params[1]);
                resp=connection.getResponseCode();
                if(resp!=200) {
                    enableButton();
                    String response=getResponse(connection);
                    Log.i("LOGIN RESPONSE CODE",String.valueOf(resp));
                    return String.valueOf(resp);
                }
                else
                {
                    //sharedPreferences.edit().putBoolean("isLoggedIn",true).apply();
                    String response=getResponse(connection);
                    Log.i("LOGIN RESPONSE CODE",String.valueOf(resp));
                    setToken(response);
                    Log.i("LOGIN RESPONSE DATA",response);
                    outputStream.flush();
                    outputStream.close();
                    goToProfile();
                    return String.valueOf(resp);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            pbar.setVisibility(View.INVISIBLE);

            if (response != null) {
                if (response.equals("200")) {
                    Toast.makeText(getApplicationContext(), "Logged In!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Check Your Credentials And Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                enableButton();
                Toast.makeText(LoginActivity.this, "Please Check Your Internet Connection and Try Again", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String getResponse(HttpURLConnection connection)
    {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = in.readLine()) != null) {

                sb.append(line);
                break;
            }

            in.close();
            return sb.toString();
        }catch(Exception e)
        {
            return e.getMessage();
        }
    }
    public void login(View view) {
        Log.i("ButtonClick","login");
        mobileNo=mobNo.getText().toString();
        passwd=password.getText().toString();
        if(mobileNo.equals("")||passwd.equals(""))
        {
            Toast.makeText(this, "Enter valid data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loginButton.setClickable(false);
            JSONObject loginDetails=new JSONObject();
            try {
                 loginDetails.put("mobileNo",mobileNo);
                 loginDetails.put("password",passwd);
                pbar.setVisibility(View.VISIBLE);
                LoginTask task=new LoginTask();
                task.execute(LoginActivity.MAINURL+"/users/signin",loginDetails.toString());
                }
            catch(Exception e)
            {
                pbar.setVisibility(View.INVISIBLE);
                loginButton.setClickable(true);
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Register(View view) {
        Intent regInt=new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(regInt);
    }
}
