package com.example.basmamohamed.agileproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.File;

import it.sephiroth.android.library.picasso.Picasso;

import static com.example.basmamohamed.agileproject.R.layout.account;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class Account  extends ActionBarActivity {

    private Button btn3;
    private Button btn4;

    private static final int RESULT_LOAD_IMG=1;
    private  Button btn1;
    private Button btn2;
    ImageView profileimg;
    Button uploadimg;
    private TextView txt;
    private RatingBar ratingbar1;
    int totalscore;
    ImageView profile_img;

    MyBD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(account);
        profile_img=(ImageView)findViewById(R.id.profileImg);
        txt=(TextView) findViewById(R.id.welcomeuser);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String s = bundle.getString("User");

        txt.setText("Welcome "+s);

        bd=new MyBD(this);


        String urI=bd.getURI(bd,s);


        Uri uri;
        uri = Uri.parse(urI);

        File f = new File(uri.getPath());

        //Picasso.with(this).load(f).noPlaceholder().centerCrop().fit().into(profile_img);
        Picasso.with(this)
                .load(uri)
                .fit()
                .skipMemoryCache()
                .into(profile_img);


        btn1=(Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Account.this, Game_window.class);
                startActivity(intent);
            }
        });


        btn3=(Button) findViewById(R.id.leaderbtn);


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Account.this, leaderboard.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMG&&resultCode==RESULT_OK&&data!=null)
        {
            Uri selectedImage=data.getData();
            profileimg.setImageURI(selectedImage);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.log_out) {

            finish();
            // session.isUserLoggedIn()== false;
            Intent in = new Intent(getApplicationContext(), Login.class);
            startActivity(in);
            return true;
        }


        if (id == R.id.change_pass) {

            finish();
            // session.isUserLoggedIn()== false;
            Intent in = new Intent(getApplicationContext(), changepassword.class);
            startActivity(in);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }


 /*
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.profileImg:
                Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMG);
                break;


        }
    }

*/
}
