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
import com.example.lab203_28.healthy.MenuFragment;
import com.example.lab203_28.healthy.R;
import com.example.lab203_28.healthy.Weight.Weight_FromFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class
WeightFragment extends Fragment{

    ArrayList<Weight> weights = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdd_WeightBtn();
        initBack_Btn();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();
        FirebaseFirestore mDB = FirebaseFirestore.getInstance();
        final ListView weightList = (ListView) getView().findViewById(R.id.weight_listview);
        final WeightAdaptor weightAdapter = new WeightAdaptor(
                getActivity(),
                R.layout.fragment_weight,
                weights
        );
        weights.clear();
        mDB.collection("myfitness").document(uId).collection("weight").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
                    Log.d("WEIGHT", doc.getData().toString());

                    weights.add(doc.toObject(Weight.class));
                }

                weightList.setAdapter(weightAdapter);
            }
        });
//        mDB.collection("myfitness").document(uId).collection("weight").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//
//                weights.clear();
//                for(DocumentSnapshot doc : documentSnapshots){
//                    if(doc.get("date") != null){
//                        weights.add(doc.toObject(Weight.class));
//                    }
//                }
//
//                ListView weightList = (ListView) getView().findViewById(R.id.weight_listview);
//                WeightAdaptor weightAdapter = new WeightAdaptor(getActivity(), R.layout.fragment_weight, weights);
//                weightList.setAdapter(weightAdapter);
//            }
//        });

        //        CollectionReference docRef = mDB.collection("myfitness").document("uid").collection("");
//        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//               Weight _weight = (Weight) queryDocumentSnapshots.toObjects(Weight.class);
//            }
//        });
//
//                mDB.collection("myfitness").document("uid").collection("weight").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(
//                    @javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
//                    @javax.annotation.Nullable FirebaseFirestoreException e) {
//                for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
//                    if (doc.get("uid") != null){
//                        weights.add(new Weight("1 june 18", 29, "up"));
//                    }
//                }
//            }
//        });
        //weights.add(new Weight("1 june 18", 29, "up"));
        //weights.add(new Weight("2 june 18", 21, "up"));
       // weights.add(new Weight("3 june 18", 26, "up"));




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
    void initBack_Btn() {
        TextView _back_Btn = (TextView) getView().findViewById(R.id.weight_back_btn);
        _back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("USER", "GOTO ADD_WEIGHT");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
            }
        });
    }
}
