package com.example.lab203_28.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab203_28.healthy.R;

import java.util.ArrayList;

public class Weight_FromFragment extends Fragment {

    ArrayList<Weight> weights = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_from, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initbackBtn();
        initrecBtn();
    }


    void initbackBtn(){
        TextView _backBtn = (TextView) getView().findViewById(R.id.weight_from_backBnt);
        _backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("USER", "GOTO WEIGHT");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).commit();
            }
        });
    }
    void initrecBtn(){
        TextView _recBtn = (TextView) getView().findViewById(R.id.weight_from_recBnt);
        _recBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                EditText _date = (EditText) getView().findViewById(R.id.weight_from_date);
                EditText _weight = (EditText) getView().findViewById(R.id.weight_from_weight);
                String _dateStr = _date.getText().toString();
                int _weightInt = Integer.parseInt(_weight.getText().toString());

                weights.add(new Weight(_dateStr, _weightInt , ""));



                Log.d("USER", "RECORD_WEIGHT");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).commit();
                Toast.makeText(
                        getActivity(),
                        "SAVE",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
