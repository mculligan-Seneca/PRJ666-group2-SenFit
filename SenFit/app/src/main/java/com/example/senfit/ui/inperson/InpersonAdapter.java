/*
* author: Portia siddiqua(107741175)
*
* */


package com.example.senfit.ui.inperson;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.R;

import java.util.List;

public class InpersonAdapter extends RecyclerView.Adapter<InpersonAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<InpersonClassData> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mClassImageView;
        private final TextView mClassNameTextView;
        private final TextView mClassDateTextView;
        private final TextView mClassTimeTextView;
        private final TextView mClassInstrctorTextView;
        private final Button mClassEnrollButton;
        private final View mInpersonRowView;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            mInpersonRowView =  v.findViewById(R.id.inperson_row);
            mClassImageView = (ImageView) v.findViewById(R.id.inperson_row_image);
            mClassNameTextView = (TextView) v.findViewById(R.id.inperson_row_class_name);
            mClassDateTextView = (TextView) v.findViewById(R.id.inperson_row_class_date);
            mClassTimeTextView = (TextView) v.findViewById(R.id.inperson_row_class_time);
            mClassInstrctorTextView = (TextView) v.findViewById(R.id.inperson_row_class_instructor_name);
            mClassEnrollButton = (Button) v.findViewById(R.id.inperson_row_class_enroll);
        }

        public ImageView getClassImageView() {
            return mClassImageView;
        }

        public TextView getClassNameTextView() {
            return mClassNameTextView;
        }

        public TextView getClassDateTextView() {
            return mClassDateTextView;
        }

        public TextView getClassTimeTextView() {
            return mClassTimeTextView;
        }

        public TextView getClassInstrctorTextView() {
            return mClassInstrctorTextView;
        }

        public Button getClassEnrollButton() {
            return mClassEnrollButton;
        }

        public View getInpersonRowView() {
            return mInpersonRowView;
        }
    }


    public InpersonAdapter(List<InpersonClassData> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
//setting data on textview for each row and showing
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getClassNameTextView().setText(mDataSet.get(position).getClasName());
        viewHolder.getClassDateTextView().setText(mDataSet.get(position).getDate());
        viewHolder.getClassTimeTextView().setText(mDataSet.get(position).getTime());
        viewHolder.getClassInstrctorTextView().setText(mDataSet.get(position).getInstructorName());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}