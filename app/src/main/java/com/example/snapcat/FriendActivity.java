package com.example.snapcat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FriendActivity extends AppCompatActivity {

    ListView friendListView;
    ArrayList<String> nicknames;
    ArrayList<String> ids;
    NameAdapter nameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend2);

        Resources res = getResources();
        friendListView = (ListView) findViewById(R.id.dynamic);
        nicknames = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.nicknames)));
        ids = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.idCodes)));

        Bundle extras = getIntent().getExtras();


        nameAdapter = new NameAdapter(this, nicknames, ids);
        friendListView.setAdapter(nameAdapter);


    }

    public void onClickAdd (View v){
        Intent intent=new Intent(FriendActivity.this,Pop.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == Activity.RESULT_OK) {

            if (data != null) {
                String a=new String(data.getStringExtra("nickname"));
                String b=new String(data.getStringExtra("id"));
                nameAdapter.add(a,b);
                nameAdapter.notifyDataSetChanged();
            }

        }

    }


}