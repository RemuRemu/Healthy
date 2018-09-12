package com.example.lab203_28.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lab203_28.healthy.BMIFragment;
import com.example.lab203_28.healthy.R;
import com.example.lab203_28.healthy.Weight.Weight_FromFragment;

import java.util.ArrayList;
import java.util.List;

public class WeightFragment extends Fragment{

    ArrayList<Weight> weights = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdd_WeightBtn();

        weights.add(new Weight("1 june 18", 29, "up"));
        weights.add(new Weight("2 june 18", 21, "up"));
        weights.add(new Weight("3 june 18", 26, "up"));

        ListView _weightList = (ListView) getView().findViewById(R.id.weight_listview);
        final WeightAdaptor _weightAdapter = new WeightAdaptor(
                getActivity(),
                R.layout.weight_,
                weights
        );

        _weightList.setAdapter(_weightAdapter);



    }


    void initAdd_WeightBtn(){
        TextView _add_weightBtn = (TextView) getView().findViewById(R.id.weight_add_weightbnt);
        _add_weightBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("USER", "GOTO ADD_WEIGHT");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Weight_FromFragment()).commit();
            }
        });
    }
}
