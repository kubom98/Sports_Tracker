package com.example.sportstracker;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

class ActivityViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.activityFromListLayout)
    LinearLayout linearLayout;

    @BindView(R.id.sportImageView)
    ImageView imageView;

    @BindView(R.id.dateOfActivity)
    MaterialTextView materialTextView;

    public ActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setActivity(Activity activity, SportOnClickedListener sportOnClickedListener) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        this.materialTextView.setText(formatter.format(activity.getDate()));
        this.materialTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportOnClickedListener.onSportClick(activity);
            }
        });

        String type = activity.getType();
        switch (type) {
            case "basketball":
                this.imageView.setImageResource(R.drawable.basketball);
                break;
            case "box":
                this.imageView.setImageResource(R.drawable.box);
                break;
            case "cycling":
                this.imageView.setImageResource(R.drawable.cycling);
                break;
            case "football":
                this.imageView.setImageResource(R.drawable.football);
                break;
            case "gym":
                this.imageView.setImageResource(R.drawable.gym);
                break;
            case "gymnastics":
                this.imageView.setImageResource(R.drawable.gymnastics);
                break;
            case "rollerskating":
                this.imageView.setImageResource(R.drawable.rollerskating);
                break;
            case "running":
                this.imageView.setImageResource(R.drawable.running);
                break;
            case "skiing":
                this.imageView.setImageResource(R.drawable.skiing);
                break;
            case "swimming":
                this.imageView.setImageResource(R.drawable.swimming);
                break;
            default:
                this.imageView.setImageResource(R.drawable.tennis);
                break;
        }
    }

}
