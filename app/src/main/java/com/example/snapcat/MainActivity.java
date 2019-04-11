package com.example.snapcat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    private Context context=this;
    public final int SELECT_IMAGE = 42;
    private Uri filePath;
    String remotePath;
    String fullRemotePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization of buttons
        Button sendButton =  findViewById(R.id.SendButton);
        Button galleryButton =  findViewById(R.id.GalleryButton);
        Button rotateButton =  findViewById(R.id.RotateButton);
        Button friendsButton =  findViewById(R.id.FriendsButton);

        //ImageView named kittyCat (the main image to be upload from the gallery)
        ImageView kittyCat = findViewById(R.id.imageView);
        kittyCat.setImageResource(R.drawable.bear);
        kittyCat.setVisibility(View.INVISIBLE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Uri filePath = data.getData();
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }
            ImageView kittyCat = findViewById(R.id.imageView);
            kittyCat.setImageBitmap(bitmap);
            kittyCat.setVisibility(View.VISIBLE);
        }

    }
    public void onClickGallery (View v){
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_IMAGE);
    }

    public void onClickSend(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure that this image contains a cat? Please be honest.");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "Yes I am sure.",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );

        builder.setNegativeButton(
                "No I am a dirty liar.",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onClickRotate(View view)
    {
        //ImageView named kittyCat
        ImageView kittyCat = findViewById(R.id.imageView);
        float degrees =90;
        if(bitmap!=null) {
            Matrix matrix = new Matrix();
            matrix.postRotate(degrees);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            kittyCat.setImageBitmap(bitmap);
        }
    }

    public void onClickFriends(View view)
    {
        Intent startIntent = new Intent(getApplicationContext(),FriendActivity.class);
        startActivity(startIntent);
    }
}