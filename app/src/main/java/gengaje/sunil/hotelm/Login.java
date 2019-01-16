package gengaje.sunil.hotelm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button button1,button2;
    EditText editText1,editText2;

    SQLiteDatabase sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        **R is a Class in android that are having the id’s of all the view’s. findViewById is a method that finds the view from the layout resource file that are attached with current Activity.
        setContentView(R.layout.activity_login);
        button1 = findViewById(R.id.LOGbtn);
        button2 = findViewById(R.id.ADDbt);
        editText1 = findViewById(R.id.USRbox);
        editText2 = findViewById(R.id.PASSbox);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
//	MODE_PRIVATE
//File creation mode: the default mode, where the created file can only be accessed by the calling application (or all applications sharing the same user ID).
        sq = openOrCreateDatabase("Hoteldb", Context.MODE_PRIVATE,null);
        sq.execSQL("CREATE TABLE IF NOT EXISTS user(UserName VARCHAR, Password VARCHAR);");




    }


    public void onClick(View v) {
        if(v==button2)
        {
            if(editText1.getText().toString().trim().length()==0|| editText2.getText().toString().length()==0)
            {
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
                showMessage("Error", "please enter all values..!");
                return;
            }
            sq.execSQL("INSERT INTO user VALUES('"+editText1.getText()+"','"+editText2.getText()+"');");
            showMessage("Success","Recorded Added");
            clearText();
        }
        if(v==button1)
        {
            if(editText1.getText().toString().trim().length()==0 ||editText2.getText().toString().trim().length()==0 )
            {
                showMessage("Error","Please Enter User name...!");
                return;
            }
            Cursor c = sq.rawQuery("SELECT * FROM user WHERE UserName='"+editText1.getText()+"' AND Password='"+editText2.getText()+ "'", null);
            if(c.moveToFirst())
            {
                Intent intent= new Intent(Login.this, RoomActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                showMessage("Error", "Invalid Data..!");
            }
        }

    }

    private void clearText() {
        editText1.setText("");
        editText2.setText("");
    }

    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
