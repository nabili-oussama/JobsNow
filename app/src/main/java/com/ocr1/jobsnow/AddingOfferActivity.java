package com.ocr1.jobsnow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddingOfferActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText titleEditText, durationEditText, remunerationEditText, descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_offer);

        // Get a reference to the Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Get references to the EditText views in the layout
        titleEditText = findViewById(R.id.edit_text_title);
        durationEditText = findViewById(R.id.edit_text_duration);
        remunerationEditText = findViewById(R.id.edit_text_remuneration);
        descriptionEditText = findViewById(R.id.edit_text_description);

        Button addButton = findViewById(R.id.button_add);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the "job_offers" node in the database
                DatabaseReference jobOffersRef = mDatabase.child("job_offers");

                // Get a reference to a new, unique key for the job offer
                String key = jobOffersRef.push().getKey();

                // Create a new JobOffer object with the data from the EditText views
                JobOffer jobOffer = new JobOffer(
                        titleEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        durationEditText.getText().toString(),
                        remunerationEditText.getText().toString()
                );

                // Add the job offer to the "job_offers" node in the database using the unique key
                jobOffersRef.child(key).setValue(jobOffer)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Display a success message
                                Toast.makeText(AddingOfferActivity.this, "Job offer added successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddingOfferActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Display an error message
                                Toast.makeText(AddingOfferActivity.this, "Error adding job offer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}