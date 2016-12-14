package com.example.basmamohamed.agileproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class signup extends Activity {
    Button btn;
    EditText txt1;
    EditText txt2;
    EditText txt3;
    EditText txt4;
    MyBD bd;
    TextView txt;
    ImageView profilePicture;
    String imageUrl;

    private static final int RESULT_LOAD_IMG=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_signup);
        btn=(Button) findViewById(R.id.sign_upbtn);
        profilePicture=(ImageView)findViewById(R.id.profilePic);
        bd=new MyBD(signup.this);

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });


        signupUser();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMG&&resultCode==RESULT_OK&&data!=null)
        {
            Uri selectedImage=data.getData();
            profilePicture.setImageURI(selectedImage);
            imageUrl=data.getData().toString();

        }
    }


    public void signupUser(){


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1=(EditText)findViewById(R.id.l_name);
                txt2=(EditText)findViewById(R.id.editText8);
                txt3=(EditText)findViewById(R.id.editText7);
                txt4=(EditText)findViewById(R.id.editText);

                if(txt1.getText().toString().equals("")){

                    txt1.setError("NAme is required ");
                }
                if(txt2.getText().toString().equals("")){

                    txt2.setError("Age is required ");
                }

                if(txt3.getText().toString().equals("")){

                    txt3.setError("Password is required ");
                }

                if(txt4.getText().toString().equals("")){

                    txt4.setError("Email is required ");
                }
                else{

                    if(bd.validateUSername(bd, txt1.getText().toString())){

                        txt1.setError("User name is already taken ,try another one");
                    }

                    else if(!bd.validemail(txt4.getText().toString())){

                        txt4.setError("Email is not valid");
                    }

                    else if(!bd.validage(Integer.parseInt(txt2.getText().toString()))){
                        txt2.setError("Sorry you can't create account,your age is out of the range");

                    }






                    else {
                        bd.insertUser(bd, txt1.getText().toString(), Integer.parseInt(txt2.getText().toString()), txt3.getText().toString(), txt4.getText().toString(),imageUrl);

                        if(bd.validateUSer(bd,txt1.getText().toString(),txt3.getText().toString())){

                            Toast.makeText(signup.this,
                                    txt1.getText().toString()+"is inserted", Toast.LENGTH_LONG).show();


                            Bundle b = new Bundle();
                            b.putString("User", txt1.getText().toString());

                            Intent intent = new Intent(signup.this, Account.class);
                            intent.putExtras(b);
                            startActivity(intent);

                        }
                    }

                }
            }
        });




    }

}
