package com.example.basmamohamed.agileproject;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import static android.provider.Settings.System.AIRPLANE_MODE_ON;

import java.io.File;

import it.sephiroth.android.library.picasso.Picasso;

import static android.app.PendingIntent.getActivity;
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
    Button UpdateImg;
    MyBD bd;




    static boolean isAirplaneModeOn(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return Settings.System.getInt(contentResolver, AIRPLANE_MODE_ON, 0) != 0;
    }
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(account);
        profile_img=(ImageView)findViewById(R.id.profileImg);
        txt=(TextView) findViewById(R.id.welcomeuser);
        UpdateImg = (Button)  findViewById(R.id.UpdateImgBtn);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String userName = bundle.getString("User");
        txt.setText("Welcome "+userName);

        bd=new MyBD(this);
        String urI=bd.getURI(bd,userName);
        Uri uri;
        uri = Uri.parse(urI);
        File f = new File(uri.getPath());

        Picasso picasso = new Picasso.Builder(this).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        }).build();


        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            picasso.with(this).setLoggingEnabled(true);
            picasso.with(this)
                    .load(uri)
                    .fit()
                    .skipMemoryCache()
                    .centerInside()
                    .into(profile_img);

        }




        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMG);
            }
        });


        UpdateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bd.editImage(bd,userName,newImage);
                Toast.makeText(Account.this, "Image was Updated",Toast.LENGTH_LONG).show();
            }
        });


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

public String newImage="";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMG&&resultCode==RESULT_OK&&data!=null)
        {
            Uri selectedImage=data.getData();
            profile_img.setImageURI(selectedImage);
            newImage=data.getData().toString();

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
