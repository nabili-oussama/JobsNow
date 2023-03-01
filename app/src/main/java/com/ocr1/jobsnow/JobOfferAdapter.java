package com.ocr1.jobsnow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobOfferAdapter extends RecyclerView.Adapter<JobOfferAdapter.ViewHolder> {
    private final List<JobOffer> mJobOffers;
    private final OnJobOfferClickListener mListener;
    private final DatabaseReference mDatabaseRef;
    private final ValueEventListener mValueEventListener;
    private RecyclerView mRecyclerView;

    public JobOfferAdapter(RecyclerView recyclerView, OnJobOfferClickListener listener) {
        mListener = listener;

        // Initialize Firebase Database reference
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("jobOffers");

        // Initialize value event listener to retrieve job offers from Firebase Database
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mJobOffers.clear();
                for (DataSnapshot jobOfferSnapshot : dataSnapshot.getChildren()) {
                    JobOffer jobOffer = jobOfferSnapshot.getValue(JobOffer.class);
                    mJobOffers.add(jobOffer);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Do nothing
            }
        };

        // Attach value event listener to Firebase Database reference
        mDatabaseRef.addValueEventListener(mValueEventListener);

        mJobOffers = new ArrayList<>();
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_job_offers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobOffer jobOffer = mJobOffers.get(position);
        holder.bind(jobOffer);
    }

    @Override
    public int getItemCount() {
        return mJobOffers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitleTextView;
        private final TextView mDescriptionTextView;
        private final TextView mSalaryTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.job_offer_title);
            mDescriptionTextView = itemView.findViewById(R.id.job_offer_description);
            mSalaryTextView = itemView.findViewById(R.id.job_offer_remuneration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        JobOffer jobOffer = mJobOffers.get(position);
                        mListener.onJobOfferClick(jobOffer);
                    }
                }
            });
        }

        public void bind(JobOffer jobOffer) {
            mTitleTextView.setText(jobOffer.getTitle());
            mDescriptionTextView.setText(jobOffer.getDescription());
            mSalaryTextView.setText(jobOffer.getRemuneration());
        }
    }

    public interface OnJobOfferClickListener {
        void onJobOfferClick(JobOffer jobOffer);
    }
}
