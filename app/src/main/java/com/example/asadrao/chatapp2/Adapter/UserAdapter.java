package com.example.asadrao.chatapp2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asadrao.chatapp2.MessageActivity;
import com.example.asadrao.chatapp2.Model.User;
import com.example.asadrao.chatapp2.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{
    private Context mcontext;
    private List<User> muser;

    public UserAdapter(Context mcontext, List<User> muser) {
        this.mcontext = mcontext;
        this.muser = muser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.user_item, viewGroup, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final User user = muser.get(i);
        viewHolder.username.setText(user.getUsername());
        if (user.getImageURL().equals("default"))
        {
            viewHolder.profile_image.setImageResource(R.mipmap.download);
        }
        else
        {
            Glide.with(mcontext).load(user.getImageURL()).into(viewHolder.profile_image);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                System.out.println("mer name "+user.getId()+" "+user.getUsername());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return muser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView username;
        public ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}
