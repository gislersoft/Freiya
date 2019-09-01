package com.example.user.talleristamod.PackageGamePreguntas;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.user.talleristamod.QrRaceHolder;
import com.example.user.talleristamod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivitySelectImg extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView title;
    TabLayout tabLayoutImg;
    String filter1, filter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_img);

        recyclerView = findViewById(R.id.recyclerImg);
        tabLayoutImg = findViewById(R.id.tabLayoutImgActivities);
        filter1 =  "creator";
        filter2 =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        SetAdapterFire(filter1, filter2);

        tabLayoutImg.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        filter1 =  "creator";
                        filter2 =  FirebaseAuth.getInstance().getCurrentUser().getUid();
                        SetAdapterFire(filter1, filter2);
                        break;
                    case 1:
                        filter1 =  "copyA";
                        filter2 =  "false";
                        SetAdapterFire(filter1, filter2);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }


    public void SetAdapterFire (String filter1, String filter2){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("Activity/ActivityImaginaries");

        AdapterFirebaseImaginaries adaptadorFirebaseQrRace = new AdapterFirebaseImaginaries(ObjectActivityImaginaries.class,R.layout.adapter_recycler_view
                , QrRaceHolder.class,referencia,this, filter1, filter2);

        recyclerView.setAdapter(adaptadorFirebaseQrRace);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,false));
    }
}
