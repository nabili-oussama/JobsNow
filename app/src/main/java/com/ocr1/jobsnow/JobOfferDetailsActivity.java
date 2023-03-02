package com.ocr1.jobsnow;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobOfferDetailsActivity extends AppCompatActivity {

    private JobOffer mJobOffer;
    private EditText mTitleEditText;
    private EditText mDurationEditText;
    private EditText mDescriptionEditText;
    private EditText mRemunerationEditText;
    private Button mSaveButton;
    private Button mDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_details);

        // Get the job offer object passed from MainActivity
        JobOffer mJobOffer = (JobOffer) getIntent().getSerializableExtra("jobOffer");

        // Initialize UI elements
        mTitleEditText = findViewById(R.id.title_edit_text);
        mDurationEditText = findViewById(R.id.duration_edit_text);
        mDescriptionEditText = findViewById(R.id.description_edit_text);
        mRemunerationEditText = findViewById(R.id.remuneration_edit_text);
        mSaveButton = findViewById(R.id.button_edit);
        mDeleteButton = findViewById(R.id.button_delete);

        // Display the job offer's details in the UI elements
        mTitleEditText.setText(mJobOffer.getTitle());
        mDurationEditText.setText(mJobOffer.getDuration());
        mDescriptionEditText.setText(mJobOffer.getDescription());
        mRemunerationEditText.setText(mJobOffer.getRemuneration());


        // Set up the save button click listener
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the job offer object with the modified details
                mJobOffer.setTitle(mTitleEditText.getText().toString());
                mJobOffer.setDuration(mDurationEditText.getText().toString());
                mJobOffer.setDescription(mDescriptionEditText.getText().toString());
                mJobOffer.setRemuneration(mRemunerationEditText.getText().toString());

                // Save the modified job offer object to Firebase
                DatabaseReference jobOfferRef = FirebaseDatabase.getInstance().getReference("job_offers").child(String.valueOf(mJobOffer));
                jobOfferRef.setValue(mJobOffer);

                // Finish the activity and return to MainActivity
                setResult(RESULT_OK);
                finish();
            }
        });
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the job offer object from Firebase
                DatabaseReference jobOfferRef = FirebaseDatabase.getInstance().getReference("job_offers").child(String.valueOf(mJobOffer));
                jobOfferRef.removeValue();

                // Finish the activity and return to MainActivity
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
