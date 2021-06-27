package com.example.dbcrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,dob;
    Button insert,update,delete,view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        dob=findViewById(R.id.dob);
        insert=findViewById(R.id.btninsert);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        view=findViewById(R.id.btnView);
        DB=new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nametext=name.getText().toString();
                String Contacttext=contact.getText().toString();
                String dobtext=dob.getText().toString();

                Boolean checkinsertdata=DB.insertuserdata(Nametext,Contacttext,dobtext);
                if (checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "new data inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nametext=name.getText().toString();
                String Contacttext=contact.getText().toString();
                String dobtext=dob.getText().toString();

                Boolean checkupdatedata=DB.updateuserdata(Nametext,Contacttext,dobtext);
                if (checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "data updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "data not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nametext=name.getText().toString();
                Boolean checkdeletedata=DB.deleteuserdata(Nametext);
                if (checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "data deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "data not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "no entry exist", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("name :"+res.getString(0)+"\n" );
                    buffer.append("contact :"+res.getString(1)+"\n" );
                    buffer.append("date of birth :"+res.getString(2)+"\n" );
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("user Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}