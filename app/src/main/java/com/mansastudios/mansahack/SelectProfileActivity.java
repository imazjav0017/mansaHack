package com.mansastudios.mansahack;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectProfileActivity extends AppCompatActivity {
    RecyclerView profileRv;
    ProfileListAdapter adapter;
    List<Profile> profileList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profile);
        profileRv=findViewById(R.id.ProfilesListRv);
        profileList=new ArrayList<>();
        adapter=new ProfileListAdapter(profileList);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        profileRv.setLayoutManager(manager);
        profileRv.setHasFixedSize(true);
        profileRv.setAdapter(adapter);

    }
    void setProfileList()
    {
        profileList.clear();
        String JsonArray=LoginActivity.sharedPreferences.getString("profiles",null);
        if(JsonArray!=null) {
            JSONArray array= null;
            try {
                array = new JSONArray(JsonArray);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject profile = array.getJSONObject(i);
                    profileList.add(new Profile(profile.getString("name"), profile.getString("_id")));
                }
                adapter.notifyDataSetChanged();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        setProfileList();
        Log.i("Token",LoginActivity.sharedPreferences.getString("auth","null"));
        try {
            setData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setData() throws JSONException {
        String auth=LoginActivity.sharedPreferences.getString("auth","null");
        JSONObject object=new JSONObject();
        object.put("auth",auth);
        GetProfilesTask task=new GetProfilesTask(getApplicationContext(), new DataCallBack() {
            @Override
            public void datacallBack(boolean response, String resp) {
                if(response)
                {
                    if(!resp.equals("422")) {
                        try {
                            JSONArray main = new JSONArray(resp);
                            LoginActivity.sharedPreferences.edit().putString("profiles",main.toString()).apply();
                            setProfileList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        task.execute(LoginActivity.MAINURL+"/profiles/getAllProfile",object.toString());

    }

    public void addProfile(View view) {
        Intent addProfileIntent=new Intent(getApplicationContext(),AddProfileActivity.class);
        startActivity(addProfileIntent);
    }
}
