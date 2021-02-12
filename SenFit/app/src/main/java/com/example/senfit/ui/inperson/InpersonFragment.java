  /*
   * author: Portia siddiqua(107741175)
   *
   * */


  package com.example.senfit.ui.inperson;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.R;

  /**
 * A placeholder fragment containing a simple view.
 */
public class InpersonFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    protected RecyclerView mRecyclerView;
    protected InpersonAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;


    public interface SelectClassListener {
        public void selectClassItem(int inPersonClassId);
    }
    //communicate with ui

    private SelectClassListener  listener;

    public static InpersonFragment newInstance(int index) {
        InpersonFragment fragment = new InpersonFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof SelectClassListener){//attaches callbackinterface
            this.listener=(SelectClassListener)context;
        }

    }

    @Override
    public void onDetach(){
        super.onDetach();
        this.listener=null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inperson, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        InpersonViewModel model = new ViewModelProvider(this).get(InpersonViewModel.class);

        // Observing live data and updating adapter data
        model.getGymClassLiveData().observe(this, inpersonClassData -> {
            mAdapter = new InpersonAdapter(inpersonClassData,listener);//passes data and listener to adapter

            mRecyclerView.setAdapter(mAdapter);

            mRecyclerView.setAdapter(mAdapter);

            mRecyclerView.setLayoutManager(mLayoutManager);
        });

        return root;
    }


}