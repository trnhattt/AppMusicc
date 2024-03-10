package com.example.appmusic.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.appmusic.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPassword extends Fragment {

    private TextView back;
    private FrameLayout frameLayout;
    private EditText email;
    private TextView responseMessage;
    private ProgressBar progressBar;
    private Button resetButton;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        back = view.findViewById(R.id.back);
        frameLayout = getActivity().findViewById(R.id.register_frame_layout);

        email = view.findViewById(R.id.email);
        responseMessage = view.findViewById(R.id.responseMessage);
        progressBar = view.findViewById(R.id.progressBar);
        resetButton = view.findViewById(R.id.resetButton);
        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        if(email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            progressBar.setVisibility(View.VISIBLE);
            mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        responseMessage.setText("Kiểm tra Email của bạn!");
                        responseMessage.setTextColor(getResources().getColor(R.color.startColor));
                    }
                    else {
                        responseMessage.setText("Xảy ra lỗi khi gửi đến Email");
                        responseMessage.setTextColor(getResources().getColor(R.color.purple_200));
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    responseMessage.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void checkInputs() {
        if (!email.getText().toString().isEmpty()){
            resetButton.setEnabled(true);
            resetButton.setTextColor(getResources().getColor(R.color.endColor));
        }
        else {
            resetButton.setEnabled(false);
            resetButton.setTextColor(getResources().getColor(R.color.endColor));
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,R.anim.out_from_right);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}