package com.example.snapcat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class FriendActivity extends AppCompatActivity {

    ListView friendListView;
    ArrayList<String> nicknames;
    ArrayList<String> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend2);

        Resources res = getResources();
        friendListView = (ListView) findViewById(R.id.dynamic);
        //   nicknames =  Arrays.asList(res.getStringArray(R.array.nicknames));
        //   ids = res.getStringArray(R.array.idCodes);

        Bundle extras = getIntent().getExtras();


        NameAdapter nameAdapter = new NameAdapter(this, nicknames, ids);
        friendListView.setAdapter(nameAdapter);
    }

    public void onClickAdd (View v){
        startActivity(new Intent(FriendActivity.this,Pop.class));
    }


}