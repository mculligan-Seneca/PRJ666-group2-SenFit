/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.senfit.NetworkManager.Interceptor.AuthInterceptor;
import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetworkServices.LoginService;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Member;

import org.json.JSONObject;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * This class fetch login info from database and compare with the user input
 */
public class LoginHelper {

    private static final String SHARED_PREFS = "senfit_sharedPrefs";
    private static final String KEY = "memberId";

    public LoginHelper() {}

    /*
    Compare user credentials
    context - app context
    email -  input email
    password - input password
    comparisonCallback - interface to propagate result
     */
    public static void compareEmailPass(Context context, String email, String password,
                                        LoginActivity.ComparisonCallback comparisonCallback) {
        LoginService loginService = NetworkManager.getNetworkManager().createNetworkService(LoginService.class);
        Call<Member> memberCall = loginService.login(new LoginBody(email,password));
        memberCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                    if(!response.isSuccessful()){
                        String errMsg=null;
                        try {
                            JSONObject jsonErr = new JSONObject(response.errorBody().string());
                            errMsg=jsonErr.getString("errMsg");
                        }catch(Exception e){
                            Log.e("login_req_err",e.getMessage());
                            errMsg="Error retrieving member";

                        }
                        comparisonCallback.isValid(-1, errMsg);
                        return;
                    }
                    Member member = response.body();
                    NetworkManager.getNetworkManager().addInterceptorToClient(new AuthInterceptor(member.getToken()));
                    //retrieve token for member
                    member.setPassword(password);

                    DatabaseClient.initDB(context)
                            .getAppDatabase()
                            .getMemberDao()
                            .insertMember(member)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.from(DatabaseClient.dbExecutors))//Maybe use Schedulers.computation();
                            .subscribe(new CompletableObserver() {
                                private Disposable disposable;
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                            disposable=d;
                                }

                                @Override
                                public void onComplete() {
                                    setMemberId(context,member.getMember_id());
                                    comparisonCallback.isValid(member.getMember_id(), "Login Success");
                                    if(!disposable.isDisposed())
                                        disposable.dispose();
                                }



                                @Override
                                public void onError(@NonNull Throwable e) {
                                   /* DatabaseClient.getInstance().getAppDatabase()
                                            .getMemberDao()
                                            .insertMember(member);
                                    comparisonCallback.isValid(member.getMember_id(), "Login Success");*/
                                    Log.e("load_to_db_err",e.getMessage());
                                    if(!disposable.isDisposed())
                                        disposable.dispose();
                                }
                            });


                 /*   DatabaseClient.dbExecutors.execute(()->{


                    });//doesnt save member id but saves member in database
                    */
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e("login_api_err",t.getLocalizedMessage());
                comparisonCallback.isValid(-1,t.getMessage());
            }
        });
        /*new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {
                List<Member> memberList = DatabaseClient.initDB(context)
                        .getAppDatabase().getMemberDao().getMembersFromEmail(email);

                if (memberList == null || memberList.isEmpty()) {
                    comparisonCallback.isValid(false, true);
                    return null;
                }

                int memberId = memberList.get(0).getMember_id();
                setMemberId(context, memberId);

                String emailFromDb = memberList.get(0).getEmail();
                String passWordFromDb = memberList.get(0).getPassword();

                boolean isValidUser = email.equalsIgnoreCase(emailFromDb);
                boolean isValidPass = password.equals(passWordFromDb);

                comparisonCallback.isValid(isValidUser, isValidPass);
                return null;
            }
        }.execute();*/
    }

    public static void setMemberId(Context context, int memberId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY, memberId);
        editor.apply();
    }

    public static int getMemberId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getInt(KEY, -1);
    }

    public static void setLoginStatus(Context context, boolean status) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login_status", status);
        editor.apply();
    }

    public static boolean getLoginStatus(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getBoolean("login_status", false);
    }

}
