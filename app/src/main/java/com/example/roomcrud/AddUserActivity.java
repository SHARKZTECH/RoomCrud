package com.example.roomcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomcrud.db.AppDb;
import com.example.roomcrud.db.User;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        EditText fname=findViewById(R.id.fName);
        EditText lname=findViewById(R.id.lName);
        Button btn=findViewById(R.id.btnSave);

        btn.setOnClickListener(view -> {
             saveNewUser(fname.getText().toString(),lname.getText().toString());
        });
    }
      private void saveNewUser(String fname,String lname){
          AppDb db=AppDb.getInstance(this.getApplicationContext());
          User user=new User();
          user.firstName=fname;
          user.lastName=lname;
          db.userDao().insertUser(user);
          finish();
      }

}