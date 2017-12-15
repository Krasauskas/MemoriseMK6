package com.vytautaskrasauskas.memorisemk6;

/**
 * Created by Krasauskas on 12-Dec-17.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyAdapterForCases extends RecyclerView.Adapter<MyAdapterForCases.MyViewHolder> {
    private String[] mDataset;
    private int[] years;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public TextView mTextView2;

        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            mTextView2 = (TextView) v.findViewById(R.id.tv_description);

        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapterForCases(String[] myDataset, int[] yearSet) {

        mDataset = myDataset;
        years = yearSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterForCases.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.mTextView.setText(mDataset[position]);
        holder.mTextView2.setText(String.valueOf(years[position]));
        context = holder.mTextView.getContext();

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = mDataset[position] + " " + years[position];
                Log.d("CardView", "CardView Clicked: " + currentValue);
                //Next 2 lines should cause a crash. Delete if so
                Intent intent = new Intent(context, CaseActivity.class);
                intent.putExtra("Case Name", mDataset[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mDataset.length;
    }


}

