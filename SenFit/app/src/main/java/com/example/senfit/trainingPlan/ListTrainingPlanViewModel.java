/*
PRJ666 Sen-Fit
init date: April 10th 2021
Author Mitchell Culligan
Version 1.0
ListTrainingPlanViewModel
This viewmodel class holds data to list the training plans a member has been enrolled for
 */
package com.example.senfit.trainingPlan;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetworkServices.TrainingPlanService;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.dao.TrainingPlanDAO;
import com.example.senfit.dataContext.entities.TrainingPlan;
import com.example.senfit.dataContext.views.TrainingPlanView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTrainingPlanViewModel extends ViewModel {

        private TrainingPlanService planService;
        private TrainingPlanDAO planDAO;
        private LiveData<List<TrainingPlanView>> trainingPlanLiveData;

        public ListTrainingPlanViewModel(int memberId){
                this.planDAO= DatabaseClient.getInstance().getAppDatabase().getTrainingPlanDAO();
                this.planService = NetworkManager
                        .getNetworkManager()
                        .createNetworkService(TrainingPlanService.class);
                this.planService.getTrainingPlans(memberId).enqueue(new Callback<List<TrainingPlan>>() {
                        @Override
                        public void onResponse(Call<List<TrainingPlan>> call, Response<List<TrainingPlan>> response) {
                                if(response.isSuccessful()){
                                        Observable.fromIterable(response.body())
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(Schedulers.from(DatabaseClient.dbExecutors))
                                                .subscribe(new Observer<TrainingPlan>() {
                                                       private Disposable disposable;
                                                        @Override
                                                        public void onSubscribe(@NonNull Disposable d) {
                                                                disposable=d;
                                                        }

                                                        @Override
                                                        public void onNext(@NonNull TrainingPlan trainingPlan) {
                                                                planDAO.insertWithPortfolio(trainingPlan,
                                                                        trainingPlan.getPortfolio());
                                                        }

                                                        @Override
                                                        public void onError(@NonNull Throwable e) {
                                                                Log.e("load_training_plans",e.getMessage());
                                                        }

                                                        @Override
                                                        public void onComplete() {
                                                            if(!disposable.isDisposed())
                                                                disposable.dispose();
                                                        }
                                                });
                                }
                        }

                        @Override
                        public void onFailure(Call<List<TrainingPlan>> call, Throwable t) {
                            Log.e("load_training_plans",t.getMessage());
                        }
                });
            this.trainingPlanLiveData= this.planDAO.getTrainingPlansForMember(memberId);
        }



        public LiveData<List<TrainingPlanView>> getTrainingPlanLiveData(){
            return this.trainingPlanLiveData;
        }



}
