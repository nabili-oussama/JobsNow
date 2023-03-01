package com.ocr1.jobsnow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("job_offers");
        Button addButton = findViewById(R.id.fab_add_job_offer);
        Button quitButton = findViewById(R.id.disconnect_job_offer);

        RecyclerView recyclerView = findViewById(R.id.job_offer_list);
        JobOfferAdapter adapter = new JobOfferAdapter(recyclerView, new JobOfferAdapter.OnJobOfferClickListener() {
            @Override
            public void onJobOfferClick(JobOffer jobOffer) {
                // Handle job offer click event
                Intent intent = new Intent(MainActivity.this, JobOfferDetailsActivity.class);
                intent.putExtra("jobOffer", (Parcelable) jobOffer);
                startActivityForResult(intent, 1);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

