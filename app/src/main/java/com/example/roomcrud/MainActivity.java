package com.example.roomcrud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.roomcrud.db.AppDb;
import com.example.roomcrud.db.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    UserRecycler userAdapter;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycleview);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userAdapter=new UserRecycler(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(userAdapter);


        loadUsers();
        userAdapter.setUserList(userList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addUser:{
                Intent intent=new Intent(MainActivity.this,AddUserActivity.class);
                startActivityForResult(intent,100);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadUsers(){
        AppDb db=AppDb.getInstance(this.getApplicationContext());
        userList=db.userDao().getAllUsers();
        userAdapter.setUserList(userList);
        Log.d("Users",userList.toString());

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100){
            loadUsers();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}