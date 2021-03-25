
/*Author : Portia Siddiqua 107741175*/


package com.example.senfit.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.R;
import com.example.senfit.ui.inperson.InpersonAdapter;
import com.example.senfit.ui.inperson.InpersonClassData;

import java.util.List;

public class MyClassesAdapter extends RecyclerView.Adapter<MyClassesAdapter.ViewHolder>{

    private List<InpersonClassData> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mClassImageView;
        private final TextView mClassNameTextView;
        private final TextView mClassDateTextView;
        private final TextView mClassTimeTextView;
        private final TextView mClassInstrctorTextView;
        private final View mInpersonRowView;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                private String TAG = "OnlineClass";

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

        public View getInpersonRowView() {
            return mInpersonRowView;
        }
    }


    public MyClassesAdapter(List<InpersonClassData> dataSet) {
        mDataSet = dataSet;
    }


    public void updateDataSet(List<InpersonClassData> dataSet) {
        mDataSet.clear();
        mDataSet.addAll(dataSet);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyClassesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myclasses_row_item, parent, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyClassesAdapter.ViewHolder viewHolder, int position) {
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
