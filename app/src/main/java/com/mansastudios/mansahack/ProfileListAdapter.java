package com.mansastudios.mansahack;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ViewHolder>  {
    List<Profile> profileList;

    public ProfileListAdapter(List<Profile> profileList) {
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_list_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Profile profile=profileList.get(i);
        viewHolder.profileName.setText(profile.getName());
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ButtonClick","Profile:"+profile.getName());
                Intent remote=new Intent(viewHolder.context,MainActivity.class);
                remote.putExtra("profileName",profile.getName());
                remote.putExtra("profileId",profile.getId());
                viewHolder.context.startActivity(remote);

            }
        });


    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        Context context;
        ImageButton btn;
        TextView profileName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            btn=itemView.findViewById(R.id.profileAvatar);
            profileName=itemView.findViewById(R.id.profileNameTv);
        }
    }
}
