package com.example.cookingrecipe.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cookingrecipe.R;
import com.example.cookingrecipe.databinding.FragmentAccountBinding;
import com.example.cookingrecipe.databinding.FragmentFavoriteBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AccountFragment extends Fragment {

    FragmentAccountBinding binding;
    Button logout_btn;
    Button login_btn;
    TextView name;
    TextView email;
    ImageView profile_image;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    ConstraintLayout login_layout;
    ConstraintLayout profile_layout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        profile_layout = binding.profile;
        login_layout = binding.login;

        logout_btn = binding.logoutBtn;
        logout_btn.setOnClickListener(v -> signOut());
        login_btn = binding.loginBtn;
        login_btn.setOnClickListener(v -> startActivity(new Intent(this.getActivity(), Login.class)));

        name = binding.name;
        email = binding.email;
        profile_image = binding.profileImg;

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getActivity(), gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            profile_layout.setVisibility(View.VISIBLE);
            login_layout.setVisibility(View.GONE);
            String acct_name = acct.getDisplayName();
            String acct_email = acct.getEmail();
            String url_image = String.valueOf(acct.getPhotoUrl());

            name.setText(acct_name);
            email.setText(acct_email);
            if (!url_image.isBlank()) {
                Glide.with(getActivity())
                        .load(url_image)
                        .into(profile_image);
            }
        } else {
            profile_layout.setVisibility(View.GONE);
            login_layout.setVisibility(View.VISIBLE);
        }


        return view;
    }

    void signOut() {
        gsc.signOut().addOnCompleteListener(task -> {
            getActivity().finish();
            startActivity(new Intent(getActivity(), MainActivity.class));
        });
    }
}