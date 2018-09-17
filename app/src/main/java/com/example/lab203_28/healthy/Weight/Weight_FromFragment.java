package com.example.lab203_28.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab203_28.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Weight_FromFragment extends Fragment {
    Button buttonSave;
    Button buttonBack;
    EditText dateInput;
    EditText weightInput;
    ArrayList<Weight> weights = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_from, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // buttonSave = (Button) getView().findViewById();
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
                Float _weightFloat = Float.parseFloat(_weight.getText().toString());
                String _status = "UP";

                FirebaseFirestore mDB = FirebaseFirestore.getInstance();
                FirebaseAuth mauth = FirebaseAuth.getInstance();

                weights.add(new Weight(_dateStr, _weightFloat , _status));

                mDB.collection("myfitness").document("uid").collection("weight").add(new Weight(_dateStr,_weightFloat,_status)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("USER", "RECORD_WEIGHT");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("USER", "RECORD_WEIGHT_FAIL");
                        Toast.makeText(
                                getActivity(),
                                "เกิดข้อผืดพลาด :"+e,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });


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
