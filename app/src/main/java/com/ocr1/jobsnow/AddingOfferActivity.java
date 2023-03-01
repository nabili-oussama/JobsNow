package com.ocr1.jobsnow;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_offer);

        // Get a reference to the Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Get references to the EditText views in the layout
        EditText titleEditText = findViewById(R.id.edit_text_title);
        EditText durationEditText = findViewById(R.id.edit_text_duration);
        EditText remunerationEditText = findViewById(R.id.edit_text_remuneration);
        EditText descriptionEditText = findViewById(R.id.edit_text_description);

        // Get a reference to the "job_offers" node in the database
        DatabaseReference jobOffersRef = mDatabase.child("job_offers");

        // Get a reference to a new, unique key for the job offer
        String key = jobOffersRef.push().getKey();

        // Create a new JobOffer object with the data from the EditText views
        JobOffer jobOffer = new JobOffer(
                titleEditText.getText().toString(),
                durationEditText.getText().toString(),
                remunerationEditText.getText().toString(),
                descriptionEditText.getText().toString()
        );

        // Add the job offer to the "job_offers" node in the database using the unique key
        jobOffersRef.child(key).setValue(jobOffer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Display a success message
                        Toast.makeText(AddingOfferActivity.this, "Job offer added successfully!", Toast.LENGTH_SHORT).show();
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
}



