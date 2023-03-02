package com.ocr1.jobsnow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class JobOfferAdapter extends RecyclerView.Adapter<JobOfferAdapter.ViewHolder> {

    Context context;

    ArrayList<JobOffer> list;
    private OnJobOfferClickListener onJobOfferClickListener;

    public JobOfferAdapter(Context context, ArrayList<JobOffer> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnJobOfferClickListener(OnJobOfferClickListener onJobOfferClickListener) {
        this.onJobOfferClickListener = onJobOfferClickListener;
    }

    public interface OnJobOfferClickListener {
        void onJobOfferClick(JobOffer jobOffer);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        JobOffer user = list.get(position);
        holder.Duration.setText(user.getDuration());
        holder.Title.setText(user.getTitle());
        holder.Remuneration.setText(user.getRemuneration());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJobOfferClickListener != null) {
                    onJobOfferClickListener.onJobOfferClick(user);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

       TextView Title, Duration, Remuneration;


        public ViewHolder(@NonNull View itemView){
            super(itemView);

            Title = itemView.findViewById(R.id.jobTitle);
            Duration = itemView.findViewById(R.id.jobDuration);
            Remuneration = itemView.findViewById(R.id.jobRemuneration);
        }
    }
}
