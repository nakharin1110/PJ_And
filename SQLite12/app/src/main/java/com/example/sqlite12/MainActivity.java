package com.example.sqlite12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DBHelper dbh;
    EditText eName,eSurname,eSection,eSex,eAge,eSalary,eID;
    Button btnAddData,btnEditData,btnDeleteData,btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    ShowInfo("ข้อความจากแอพ","เพิ่มข้อมูล สำเร็จ");
                }else {
                    ShowInfo("ข้อความจากแอพ","ไม่สามารถเพิ่มข้อมูลได้");
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
                    ShowInfo("ข้อความจากแอพ","แก้ไขข้อมูล สำเร็จ");
                }else {
                    ShowInfo("ข้อความจากแอพ","ไม่สามารถเแก้ไขข้อมูลได้");
                }
            }
        });

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer delRowQty = dbh.DeleteData(eID.getText().toString());
                if (delRowQty>0){
                    ShowInfo("ข้อความจากแอพ","ลบข้อมูลไป จำนวน "+ delRowQty + "เร็คคอร์ด");
                }else {
                    ShowInfo("ข้อความจากแอพ","ไม่สามารถลบข้อมูลได้");
                }
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor resDat = dbh.getAllData();
                if (resDat.getCount()==0){
                    ShowInfo("ข้อความจากแอพ","ไม่มีข้อมูลในดาต้าเบส");
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
                    ShowInfo("ข้อมูลที่ต้องการ",datBuff.toString());
                }

            }
        });

        ShowInfo("ทดสอบ","นี่คือข้อความที่ต้องการแสดง");
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