package com.example.snapcat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

public class Pop extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.friendpopupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));
    }

    public void AddToList(View view)
    {
        final EditText nicknameEditText=(EditText)findViewById(R.id.nicknameEditText);
        final EditText idEditText=(EditText) findViewById(R.id.idEditText);
        String nickname = nicknameEditText.getText().toString();
        String id=idEditText.getText().toString();
        Intent intent =new Intent();
        intent.putExtra("nickname",nickname);
        intent.putExtra("id",id);
        setResult(RESULT_OK, intent);
        finish();
    }
}