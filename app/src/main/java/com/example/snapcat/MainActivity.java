package com.example.snapcat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
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

        Button sendButton =  findViewById(R.id.SendButton);
        Button galleryButton =  findViewById(R.id.GalleryButton);
        Button rotateButton =  findViewById(R.id.RotateButton);
        Button friendsButton =  findViewById(R.id.FriendsButton);
        //ImageView named kittyCat
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


        }

    }
    public void onClickGallery (View v){
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_IMAGE);
    }

    public void onClickSend(View view)
    {
        TextView hiText = findViewById(R.id.helloWorldTextView);
        hiText.setText("Hello world from Send!");
        hiText.setVisibility(View.VISIBLE);
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