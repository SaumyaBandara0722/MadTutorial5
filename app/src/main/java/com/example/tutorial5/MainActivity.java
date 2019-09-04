package com.example.tutorial5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtId, txtName, txtAdd, txtConNo;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId=findViewById(R.id.txtId);
        txtName=findViewById(R.id.txtName);
        txtAdd=findViewById(R.id.txtAdd);
        txtConNo=findViewById(R.id.txtNo);

        btnSave=findViewById(R.id.btnSave);
        btnShow=findViewById(R.id.btnShow);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);

        std = new student();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
                try{
                    if (TextUtils.isEmpty(txtId.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter An ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtAdd.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter address", Toast.LENGTH_SHORT).show();
                    else {
                        std.setId(txtId.getText().toString().trim());
                        std.setName(txtName.getText().toString().trim());
                        std.setAddress(txtAdd.getText().toString().trim());
                        std.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));

                        dbRef.push().setValue(std);
                        Toast.makeText(getApplicationContext(), "Date saved successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalid contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void clearControls(){
        txtId.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
    }
}
