package gengaje.sunil.hotelm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

public class RoomActivity extends AppCompatActivity {

    private ImageSwitcher simpleImageSwitcher;
    Button btnNext,btnRBook,btnTBook,btnCheck;

    int currentIndex=-1;
    int imageIds[]={R.raw.sunil1,R.raw.sunil2,R.raw.sunil3,R.raw.sunil6,R.raw.sunil5,R.raw.sunil7};//add

    int count=imageIds.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);


        simpleImageSwitcher= findViewById(R.id.simpleImageSwitcher);//find image switcher
        btnNext=findViewById(R.id.buttonNext);//find button using id
        btnRBook=findViewById(R.id.buttonRBook);
        btnTBook=findViewById(R.id.buttonTBook);



        simpleImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                //To do auto generated method stub

                //Create imageview and set its properties

                ImageView imageView=new ImageView(getApplicationContext());

                //set scale type of image of  imagevievwr to fit center

                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                //set the height and width of imageview to fill parnet

                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.FILL_PARENT));

                return imageView;
            }

            //declare in and out animation and load them using AnimationUtils class
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentIndex++;
                if(currentIndex==count)
                    currentIndex=0;
                simpleImageSwitcher.setImageResource(imageIds[currentIndex]);

            }
        });

        btnRBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(RoomActivity.this,);
                startActivity(intent2);
            }
        });
        btnTBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(RoomActivity.this,Main2Activity.class);
                startActivity(intent3);
            }
        });





    }
}
