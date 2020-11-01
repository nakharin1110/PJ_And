package com.example.sqlite12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    DBHelper dbh;
    EditText eName,eSurname,eSection,eSex,eAge,eSalary,eID;
    Button btnAddData,btnEditData,btnDeleteData,btnViewAll,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });


        eName = findViewById(R.id.txtName);
        eSurname = findViewById(R.id.txtSurName);
        eSection = findViewById(R.id.txtSection);
        eSex = findViewById(R.id.txtSex);
        eAge = findViewById(R.id.txtAge);
        eSalary = findViewById(R.id.txtSalary);
        eID = findViewById(R.id.txtid);

        btnAddData = findViewById(R.id.btnAdd);
        btnEditData = findViewById(R.id.btnEdit);
        btnDeleteData = findViewById(R.id.btnDelete);
        btnViewAll = findViewById(R.id.btnViewAll);


        dbh = new DBHelper(this);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mAge = Integer.parseInt(eAge.getText().toString());
                int mSalary = Integer.parseInt(eSalary.getText().toString());
                boolean IsSucceed= dbh.AddData(eName.getText().toString(),eSurname.getText().toString(),eSection.getText().toString(),eSex.getText().toString(),mAge,mSalary);
                if (IsSucceed){
                    ClearText();
                    ShowInfo("RECORD System","เพิ่มข้อมูล สำเร็จ");
                }else {
                    ShowInfo("RECORD System","ไม่สามารถเพิ่มข้อมูลได้");
                }


            }
        });

        btnEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mAge = Integer.parseInt(eAge.getText().toString());
                int mSalary = Integer.parseInt(eSalary.getText().toString());
                boolean IsSucceed= dbh.UpdateData(eName.getText().toString(),eSurname.getText().toString(),eSection.getText().toString(),eSex.getText().toString(),mAge,mSalary,eID.getText().toString());
                if (IsSucceed){
                    ClearText();
                    ShowInfo("RECORD System","แก้ไขข้อมูล สำเร็จ");
                }else {
                    ShowInfo("RECORD System","ไม่สามารถเแก้ไขข้อมูลได้");
                }
            }
        });

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer delRowQty = dbh.DeleteData(eID.getText().toString());
                if (delRowQty>0){
                    ClearText();
                    ShowInfo("RECORD System","ลบข้อมูลไป จำนวน "+ delRowQty + "เร็คคอร์ด");
                }else {
                    ShowInfo("RECORD System","ไม่สามารถลบข้อมูลได้");
                }
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor resDat = dbh.getAllData();
                if (resDat.getCount()==0){
                    ShowInfo("RECORD System","ไม่มีข้อมูลในระบบ");
                }else {
                    StringBuffer datBuff = new StringBuffer();
                    while (resDat.moveToNext()){
                        datBuff.append("รหัส : "+resDat.getString(0)+"\n");
                        datBuff.append("ชื่อ : "+resDat.getString(1)+"\n");
                        datBuff.append("นามสกุล : "+resDat.getString(2)+"\n");
                        datBuff.append("แผนก : "+resDat.getString(3)+"\n");
                        datBuff.append("เพศ : "+resDat.getString(4)+"\n");
                        datBuff.append("อายุ : "+resDat.getString(5)+"\n");
                        datBuff.append("เงินเดือน : "+resDat.getString(6)+"\n");
                        datBuff.append("---------------------------------"+"\n");
                    }
                    ShowInfo("RECORD System",datBuff.toString());
                }

            }
        });

    }
    private void ShowInfo(String title,String msg){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setCancelable(true);
        ab.setTitle(title);
        ab.setMessage(msg);
        ab.show();

    }
    private void ClearText(){
        eName.setText("");
        eSurname.setText("");
        eSection.setText("");
        eAge.setText("");
        eSex.setText("");
        eSalary.setText("");
        eID.setText("");


    }
}