package com.example.lab203_28.healthy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


public class RegisterFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);}
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //FirebaseAuth fbAuth = FirebaseAuth.getInstance();

        initRegisterBtn();
    }
    void initRegisterBtn(){
        TextView _regBtn = (TextView) getView().findViewById(R.id.reg_register);
        _regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText _userEmail = (EditText) getView().findViewById(R.id.reg_userEmail);
                EditText _password = (EditText) getView().findViewById(R.id.reg_password);
                EditText _repassword = (EditText) getView().findViewById(R.id.re_reg_password);

                String _userEmailStr = _userEmail.getText().toString();
                String _passwordStr = _password.getText().toString();
                String _repasswordStr = _repassword.getText().toString();
                if (_userEmailStr.isEmpty() || _repasswordStr.isEmpty() || _passwordStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                }else if (!_passwordStr.equals(_repasswordStr) ){
                    Log.d("USER", "INCORECT PASSWORD");
                    Toast.makeText(
                            getActivity(),
                            "พาสเวิรืดไม่ถูกต้อง",
                            Toast.LENGTH_SHORT
                    ).show();

                }else if (_password.length()<= 6){
                    Log.d("USER", "LENGTH NOT ENOUGH");
                    Toast.makeText(
                            getActivity(),
                            " ความยามขั้นต่ำ 6 ตัว",
                            Toast.LENGTH_SHORT
                    ).show();

                }

                else{
                    Log.d("USER", "GOTO BMI");
                    FirebaseAuth fbAuth = FirebaseAuth.getInstance();
                    AuthResult authResult;
                    fbAuth.createUserWithEmailAndPassword(_userEmailStr,_passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            authResult.getUser().sendEmailVerification();
                            Log.d("USER", "Register Complete");
                            Toast.makeText(
                                    getActivity(),
                                    "ลงทะเเบียนสำเร็จ" +
                                            "",
                                    Toast.LENGTH_SHORT
                            ).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(),"ERROR ="+e.getMessage(),Toast.LENGTH_LONG);
                        }
                    });

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).commit();

                }
            }
            private void sendVerifiedEmail(FirebaseUser _user){
                _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"ERROR ="+e.getMessage(),Toast.LENGTH_LONG);
                    }
                });
            }
        });
    }
}