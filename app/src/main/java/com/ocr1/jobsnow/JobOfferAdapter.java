package com.ocr1.jobsnow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobOfferAdapter extends RecyclerView.Adapter<JobOfferAdapter.ViewHolder> {
    private final List<JobOffer> mJobOffers;
    private final OnJobOfferClickListener mListener;

    public JobOfferAdapter(List<JobOffer> jobOffers, OnJobOfferClickListener listener) {
        mJobOffers = jobOffers;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_job_offers, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JobOffer jobOffer = mJobOffers.get(position);

        holder.title.setText(jobOffer.getTitle());
        holder.duration.setText(jobOffer.getDuration());
        holder.remuneration.setText(jobOffer.getRemuneration());

        holder.button.setOnClickListener(v -> mListener.onJobOfferClick(jobOffer));
    }

    @Override
    public int getItemCount() {
        return mJobOffers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;
        public TextView title;
        public TextView duration;
        public TextView remuneration;

        public ViewHolder(View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.button_job_offers);
            title = itemView.findViewById(R.id.job_offer_title);
            duration = itemView.findViewById(R.id.job_offer_duration);
            remuneration = itemView.findViewById(R.id.job_offer_remuneration);
        }
    }

    public interface OnJobOfferClickListener {
        void onJobOfferClick(JobOffer jobOffer);
    }
}