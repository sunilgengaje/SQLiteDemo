package gengaje.sunil.hotelm;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button button1, button2, button3, button4, button5, button6, btnPay, btnCancel,btnClear,btnbook,btnview,btnviewall;
    EditText custno, cqty;
    TextView ctype, tamt, cprice;
    SQLiteDatabase db;
    int r, t, i;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        custno = findViewById(R.id.gid);
        cqty = findViewById(R.id.gno);
        ctype = findViewById(R.id.gtype);
        cprice = findViewById(R.id.gper);
        tamt = findViewById(R.id.amt);


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        btnPay = findViewById(R.id.btnPay1);
        btnCancel = findViewById(R.id.btnCancel);
        btnClear= findViewById(R.id.btnClr);
        btnbook=findViewById(R.id.btnbook);
        btnview=findViewById(R.id.btnview);
        btnviewall=findViewById(R.id.btnviewall);



        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        btnPay.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnbook.setOnClickListener(this);
        btnview.setOnClickListener(this);
        btnviewall.setOnClickListener(this);
        btnCancel.setOnClickListener(this);


        db = openOrCreateDatabase("HotelDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS room(c_id VARCHAR,c_qty VARCHAR,c_rtype VARCHAR,c_price VARCHAR,c_total VARCHAR);");

    }


    public void onClick(View v) {

        if (v == button1) {



            ctype.setText(ctype.getText() + "Standard");
            cprice.setText(cprice.getText() + "1000");

        }


        if (v == button2) {

            ctype.setText(ctype.getText() + "Standard");
            cprice.setText(cprice.getText() + "2000");

        }

        if (v == button3) {

            ctype.setText(ctype.getText() + "Standard");
            cprice.setText(cprice.getText() + "3000");

        }


        if (v == button4) {

            ctype.setText(ctype.getText() + "Standard");
            cprice.setText(cprice.getText() + "1500");

        }


        if (v == button5) {



            ctype.setText(ctype.getText() + "Standard");
            cprice.setText(cprice.getText() + "3000");

        }



        if (v == button6) {

            ctype.setText(ctype.getText() + "Standard");
            cprice.setText(cprice.getText() + "7000");

        }


        if(v==btnPay)
        {

            //r = Integer.parseInt(uroom1.getText().toString());
            t = Integer.parseInt(cqty.getText().toString());
            r = Integer.parseInt(cprice.getText().toString());

            i= (r*t);
            tamt.setText(Float.toString(i));

        }


        if(v==btnClear)
        {

            custno.setText("");
            cqty.setText("");
            ctype.setText("");
            cprice.setText("");
            tamt.setText("");

            custno.requestFocus();
        }

        if (v == btnbook) {
            if (custno.getText().toString().trim().length() == 0 ||
                    cqty.getText().toString().trim().length() == 0 ||
                    ctype.getText().toString().trim().length() == 0||
                    cprice.getText().toString().trim().length() == 0||
                    tamt.getText().toString().trim().length() == 0) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                showMessage("Error", "Please enter all values");
                return;

            }

            db.execSQL("INSERT INTO room VALUES('" + custno.getText() + "','" + cqty.getText() + "','" + ctype.getText() + "','" + cprice.getText() + "','" + tamt.getText() + "');");
            showMessage("sucess", "Booking done....");
            clearText();


        }

        if (v == btnview)
        {
            if (custno.getText().toString().length() == 0)
            {
                showMessage("Error...", "Please enter id");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM room WHERE c_id='" + custno.getText() + "'", null);//work internally on database
            //no direct modification(find data using raw query)
            if (c.moveToFirst())//go to  starting record(moveTofirst)
            {

                cqty.setText(c.getString(1));
                ctype.setText(c.getString(2));
                cprice.setText(c.getString(3));
                tamt.setText(c.getString(4));



            } else
            {
                showMessage("Error", "Invalid id");
            }

            //clearText();
        }

        if (v == btnCancel) {
            if (custno.getText().toString().length() == 0) {
                showMessage("Error...", "Please enter correct id");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM room WHERE c_id='" + custno.getText() + "'", null);//work internally on database
            //no direct modification(find data using raw query)
            if (c.moveToFirst())//go to  starting record(moveTofirst)
            {
                db.execSQL("DELETE FROM room WHERE c_id='" + custno.getText() + "'");
                showMessage("Success", "Booking Cancel");
            } else {
                showMessage("Error", "Invalid id");
            }
            clearText();
        }


        if(v==btnviewall)
        {

            Cursor c=db.rawQuery("SELECT * FROM room",null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No record found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while (c.moveToNext())
            {
                buffer.append("c_id: "+c.getString(0)+"\n");
                buffer.append("c_qty: "+c.getString(1)+"\n");
                buffer.append("c_rtype: "+c.getString(2)+"\n");
                buffer.append("c_price: "+c.getString(3)+"\n");
                buffer.append("c_total: "+c.getString(4)+"\n");


            }
            showMessage("Booking Details", buffer.toString());

        }


    }

    private void showMessage(String title, String message) {


        AlertDialog.Builder  builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        custno.setText("");
        cqty.setText("");
        ctype.setText("");
        cprice.setText("");
        tamt.setText("");

        custno.requestFocus();

    }
}