package com.example.lab203_28.healthy;


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



public class RegisterFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);}
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRegisterBtn();
    }
    void initRegisterBtn(){
        TextView _regBtn = (TextView) getView().findViewById(R.id.reg_register);
        _regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText _userId = (EditText) getView().findViewById(R.id.reg_userId);
                EditText _name = (EditText) getView().findViewById(R.id.reg_name);
                EditText _password = (EditText) getView().findViewById(R.id.reg_password);
                EditText _age = (EditText) getView().findViewById(R.id.reg_age);
                String _userIdStr = _userId.getText().toString();
                String _nameStr = _name.getText().toString();
                String _passwordStr = _password.getText().toString();
                String _ageStr = _age.getText().toString();
                if (_userIdStr.isEmpty() || _nameStr.isEmpty() || _passwordStr.isEmpty() || _ageStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                }else if (_userIdStr.equals("admin")){
                    Log.d("USER", "USER ALREADY EXIST");
                    Toast.makeText(
                            getActivity(),
                            "user นี้มีอยู่ในระบบแล้ว",
                            Toast.LENGTH_SHORT
                    ).show();

                }
                else{
                    Log.d("USER", "GOTO BMI");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).commit();
                }
            }
        });
    }
}