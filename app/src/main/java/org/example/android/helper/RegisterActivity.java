package org.example.android.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    SQLiteDatabase db;

    EditText edittextUserNo,edittextHelperNo1,edittextHelperNo2,edittextHelperNo3,edittextHelperNo4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edittextUserNo = findViewById(R.id.editTextPhone);
        edittextHelperNo1 = findViewById(R.id.editTextHelp1);
        edittextHelperNo2 = findViewById(R.id.editTextHelp2);
        edittextHelperNo3 = findViewById(R.id.editTextHelp3);
        edittextHelperNo4 = findViewById(R.id.editTextHelp4);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserNo = edittextUserNo.getText().toString().trim();
                String HelperNo1 = edittextHelperNo1.getText().toString().trim();
                String HelperNo2 = edittextHelperNo2.getText().toString().trim();
                String HelperNo3 = edittextHelperNo3.getText().toString().trim();
                String HelperNo4 = edittextHelperNo4.getText().toString().trim();

                if(UserNo.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter a User Number", Toast.LENGTH_SHORT).show();
                }else if (HelperNo1.isEmpty() && HelperNo2.isEmpty() && HelperNo3.isEmpty() && HelperNo4.isEmpty() ){
                    Toast.makeText(RegisterActivity.this, "Please Enter atleast One Helper Number", Toast.LENGTH_SHORT).show();
                }else{
                    db = openOrCreateDatabase("MyDatabase",MODE_PRIVATE,null);
                    db.execSQL("Create table if not exists MyTable (UserNo , HelperNo1, HelperNo2 , HelperNo3, HelperNo4); ");
                    InsertNumber(UserNo,HelperNo1,HelperNo2,HelperNo3,HelperNo4);
                    Cursor c;
                    c=db.rawQuery("Select * From MYTable", null);
                    c.moveToFirst();
                    String element = c.getString(0);
                    Log.e("Mukesh",element);
                    Toast.makeText(RegisterActivity.this,"The number(s) you entered have been successfully saved",Toast.LENGTH_SHORT).show();
                }}
        });
    }
    public void InsertNumber(String UserNo,String HelperNo1,String HelperNo2,String HelperNo3,String HelperNo4){
        db.execSQL("Insert into MyTable values ("+ UserNo + "," + HelperNo1 + "," + HelperNo2 + "," + HelperNo3 + "," + HelperNo4 +");");
    }

//    public void ShowNumbers(){
//        Cursor c;
//        String element,data;
//        data="";
//
//        c=db.rawQuery("Select * From MYTable", null);
//        c.moveToFirst();
//
//        for(int i=0;c.moveToPosition(i);i++){
//            element = c.getString(0);
//            data+=c;
//        }
//        ((TextView)findViewById(R.id.records)).setText(data);
//    }
}
