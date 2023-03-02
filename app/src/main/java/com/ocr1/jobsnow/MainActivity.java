package com.ocr1.jobsnow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    JobOfferAdapter jobOfferAdapter;
    DatabaseReference mDatabase;
    ArrayList<JobOffer> list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference("job_offers");
        FloatingActionButton addButton = findViewById(R.id.fab_add_job_offer);
        FloatingActionButton quitButton = findViewById(R.id.disconnect_job_offer);

        recyclerView = findViewById(R.id.job_offer_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        jobOfferAdapter = new JobOfferAdapter(this,list);
        recyclerView.setAdapter(jobOfferAdapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    JobOffer user = dataSnapshot.getValue(JobOffer.class);
                    list.add(user);
                }
                jobOfferAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        jobOfferAdapter.setOnJobOfferClickListener(new JobOfferAdapter.OnJobOfferClickListener() {
            @Override
            public void onJobOfferClick(JobOffer jobOffer) {
                // Open job offer details activity
                Intent intent = new Intent(MainActivity.this, JobOfferDetailsActivity.class);
                intent.putExtra("jobOffer", jobOffer);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddingOfferActivity.class);
                startActivity(intent);
                finish();
            };
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            };
        });
    }

}

