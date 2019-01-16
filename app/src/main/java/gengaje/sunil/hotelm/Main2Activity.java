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

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    EditText cid,cphn,cidate,codate,cquant;
    TextView total,uroom1;
    int i,r=1000,t;

    Button btnbook,btndel,btnupd,btnview,btnvall,btnPrice;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        cid= findViewById(R.id.ecid);
        cphn= findViewById(R.id.ePhone);
        cidate= findViewById(R.id.eidate);
        codate= findViewById(R.id.eodate);
        cquant= findViewById(R.id.etno);
        total=findViewById(R.id.tamt);
        uroom1=findViewById(R.id.uroom);





        btndel = findViewById(R.id.edelete);
        btnupd = findViewById(R.id.eupdate);
        btnview = findViewById(R.id.eview);
        btnvall = findViewById(R.id.eviewall);
        btnbook = findViewById(R.id.eallot);
        btnPrice= findViewById(R.id.epr);



        btnbook.setOnClickListener(this);
        btndel.setOnClickListener(this);
        btnupd.setOnClickListener(this);
        btnview.setOnClickListener(this);
        btnvall.setOnClickListener(this);
        btnPrice.setOnClickListener(this);



        db=openOrCreateDatabase("HotelDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS book(c_id VARCHAR,c_phn VARCHAR,c_idate VARCHAR,c_odate VARCHAR,c_tno VARCHAR);");




        }

    @Override
    public void onClick(View v) {

        if (v == btnbook) {
            if (cid.getText().toString().trim().length() == 0 ||
                    cphn.getText().toString().trim().length() == 0 ||
                    cidate.getText().toString().trim().length() == 0||
                    codate.getText().toString().trim().length() == 0||
                    cquant.getText().toString().trim().length() == 0) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                showMessage("Error", "Please enter all values");
                return;

            }

            db.execSQL("INSERT INTO book VALUES('" + cid.getText() + "','" + cphn.getText() + "','" + cidate.getText() + "','" + codate.getText() + "','" + cquant.getText() + "');");
            showMessage("sucess", "Booking done....");
            clearText();


        }

        if (v == btndel) {
            if (cid.getText().toString().length() == 0) {
                showMessage("Error...", "Please enter correct id");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM book WHERE c_id='" + cid.getText() + "'", null);//work internally on database
            //no direct modification(find data using raw query)
            if (c.moveToFirst())//go to  starting record(moveTofirst)
            {
                db.execSQL("DELETE FROM book WHERE c_id='" + cid.getText() + "'");
                showMessage("Success", "Booking Cancel");
            } else {
                showMessage("Error", "Invalid id");
            }
            clearText();
        }

        if (v == btnview)
        {
            if (cid.getText().toString().length() == 0)
            {
                showMessage("Error...", "Please enter id");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM book WHERE c_id='" + cid.getText() + "'", null);//work internally on database
            //no direct modification(find data using raw query)
            if (c.moveToFirst())//go to  starting record(moveTofirst)
            {

                cphn.setText(c.getString(1));
                cidate.setText(c.getString(2));
                codate.setText(c.getString(3));
                cquant.setText(c.getString(4));



            } else
            {
                showMessage("Error", "Invalid id");
            }

            //clearText();
        }



        if (v == btnupd) {
            if (cid.getText().toString().length()==0) {
                showMessage("Error...", "Please enter number");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM book WHERE c_id='" + cid.getText() + "'", null);//work internally on database
            //no direct modification(find data using raw query)
            if (c.moveToFirst())//go to  starting record(moveTofirst)
            {

                db.execSQL("UPDATE book SET c_phn='" +cphn.getText() + "',c_idate='" + cidate.getText() + "',c_odate='" + codate.getText() + "'WHERE c_id='" + cid.getText() + "'");
                showMessage("Success", "Record Modified");
            } else {
                showMessage("Error", "Invalid roll no");
            }
            clearText();
        }


        if(v==btnvall)
        {

            Cursor c=db.rawQuery("SELECT * FROM book",null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No record found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while (c.moveToNext())
            {
                buffer.append("c_id: "+c.getString(0)+"\n");
                buffer.append("c_phn: "+c.getString(1)+"\n");
                buffer.append("c_idate: "+c.getString(2)+"\n");
                buffer.append("c_odate: "+c.getString(3)+"\n");
                buffer.append("c_tno: "+c.getString(4)+"\n");


            }
            showMessage("Booking Details", buffer.toString());

        }

         if(v==btnPrice)
         {

             //r = Integer.parseInt(uroom1.getText().toString());
             t = Integer.parseInt(cquant.getText().toString());

             i= (r*t);
             total.setText(Float.toString(i));

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
        cid.setText("");
        cphn.setText("");
        cidate.setText("");
        codate.setText("");
        cquant.setText("");

        cid.requestFocus();

    }
}
