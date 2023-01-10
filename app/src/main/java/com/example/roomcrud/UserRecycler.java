package com.example.roomcrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomcrud.db.AppDb;
import com.example.roomcrud.db.User;

import java.util.ArrayList;
import java.util.List;

public class UserRecycler extends RecyclerView.Adapter<UserRecycler.MyViewHolder> {
    List<User> userList;
    Context context;

    public UserRecycler(Context context) {
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecycler.MyViewHolder holder, int  position) {
//        User user=userList.get(position);

        holder.fname.setText(userList.get(position).firstName);
        holder.lname.setText(userList.get(position).lastName);

        holder.itemView.setOnLongClickListener(view -> {
            AppDb db=AppDb.getInstance(context);

            PopupMenu menu=new PopupMenu(context,view);
            menu.getMenu().add("DELETE");
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if(menuItem.getTitle().equals("DELETE")){
                        db.userDao().delete(userList.get(holder.getAdapterPosition()));
                        userList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getLayoutPosition());
                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            menu.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fname,lname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fname=itemView.findViewById(R.id.fName);
            lname=itemView.findViewById(R.id.lName);
        }
    }
}
