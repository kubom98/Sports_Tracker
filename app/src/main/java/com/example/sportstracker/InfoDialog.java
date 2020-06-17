package com.example.sportstracker;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.material.textview.MaterialTextView;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class InfoDialog extends Dialog {

    @BindView(R.id.dialogImageView)
    ImageView sportImageView;

    @BindView(R.id.timeFromText)
    MaterialTextView timeFromText;

    @BindView(R.id.dialogTimeFrom)
    MaterialTextView timeFromTextView;

    @BindView(R.id.timeToText)
    MaterialTextView timeToText;

    @BindView(R.id.dialogTimeTo)
    MaterialTextView timeToTextView;

    @BindView(R.id.distanceText)
    MaterialTextView distanceText;

    @BindView(R.id.dialogDistance)
    MaterialTextView distanceTextView;



    private Context context;

    public InfoDialog(@NonNull Context context) {
        super(context);

        this.context = context;
        setContentView(R.layout.dialog_layout);

        ButterKnife.bind(this);

        try {
            setData();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        show();

    }

    protected abstract void setData() throws ParseException;

}
