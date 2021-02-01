
package com.example.senfit;

/*
PRJ666 Sen-Fit
init date: January 27th 2021
Author Mitchell Culligan
Version 1.0
SignUp Process Fragment

This fragment is displayed while a new member account is being processed in the background.
Class interacts with database client insert member into database.


 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;


public class SignUpProcessFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public interface SignUpRequest{
        public void resolve();
        public void reject(String errMsg);
    }

    private SignUpRequest request;


    public SignUpProcessFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof SignUpRequest){
            this.request= (SignUpRequest)context;
        }
    }

    //ensures all references of activity are dropped
    @Override
    public void onDetach(){
        super.onDetach();
        this.request=null;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        MemberSignUpViewModel memberViewModel = new ViewModelProvider.AndroidViewModelFactory(
                activity.getApplication())
                .create(MemberSignUpViewModel.class);//using view model to store data
        Member member = memberViewModel.getMember();

        DatabaseClient dbClient = DatabaseClient.getInstance(getActivity().getApplicationContext());
        DatabaseClient.dbExecutors.execute(()->{
            List<Member> memberList = dbClient//maybe replace with worker class
                    .getAppDatabase()
                    .getMemberDao()
                    .getMembersFromEmail(member.getEmail());
            if(memberList.isEmpty()) {
                //TODO: HASH PASSWORD
                try {
                    PasswordHasher ph = new PasswordHasher();
                    String hash = new String(ph.hashPassword(member.getPassword()), "UTF-8");
                    member.setPassword(hash);
                    member.setSalt(ph.getSalt());//store salt in database
                    dbClient.getAppDatabase()
                            .getMemberDao()
                            .insertMember(member);
                    activity.runOnUiThread(() -> {
                        request.resolve();
                        dismiss();
                    });
                } catch (GeneralSecurityException e) {
                    activity.runOnUiThread(() -> {
                        request.reject("Security Error ");//does not store password
                        dismiss();
                    });
                } catch (UnsupportedEncodingException e) {
                    activity.runOnUiThread(() -> {
                        request.reject("unsupported encoder type");
                        dismiss();
                    });
                }
            }
            else {
                activity.runOnUiThread(() -> {
                    request.reject("Email already found in database");
                    dismiss();//end fragment after ever request is resolved or rejected
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_process, container, false);
    }
}