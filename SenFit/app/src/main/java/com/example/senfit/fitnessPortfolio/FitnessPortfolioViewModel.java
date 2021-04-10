/*
PRJ666 Sen-Fit
init date: Feb 17th 2021
Author Mitchell Culligan
Version 1.0
Fitness Portfolio ViewModel
This view model class holds the data for the fitness portfolio use case
 */
package com.example.senfit.fitnessPortfolio;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetworkServices.FitnessPortfolioService;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.dataContext.entities.FitnessResult;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FitnessPortfolioViewModel extends ViewModel {
    private LiveData<List<FitnessPortfolio>> portfolioData;//gets list of portfolios for member
   // private LiveData<List<FitnessResult>> resultData;// gets fitness results for data
    private DatabaseClient dbClient;
    private FitnessPortfolioService portfolioService;
    public FitnessPortfolioViewModel(int memberId){
        this.dbClient= DatabaseClient.getInstance();
        this.portfolioData = this.dbClient
                .getAppDatabase()
                .getFitnessPortfolioDAO()
                .getFitnessPortfolioFromMember(memberId);
        this.portfolioService= NetworkManager.getNetworkManager().createNetworkService(FitnessPortfolioService.class);
        this.portfolioService.getPortfolios(memberId).enqueue(new Callback<List<FitnessPortfolio>>() {
            @Override
            public void onResponse(Call<List<FitnessPortfolio>> call, Response<List<FitnessPortfolio>> response) {
                if(response.isSuccessful()){
                    Observable.fromIterable(response.body())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.from(DatabaseClient.dbExecutors))
                            .subscribe(new Observer<FitnessPortfolio>() {
                                private Disposable disposable;
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    disposable=d;
                                }

                                @Override
                                public void onNext(@NonNull FitnessPortfolio portfolio) {
                                        try{
                                            dbClient.getAppDatabase()
                                                    .getFitnessPortfolioDAO()
                                                    .insertPortfolio(portfolio);
                                        }catch(Exception e){
                                            Log.e("load_portfolio_err",e.getMessage());
                                        }
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.e("load_portfolio_err",e.getMessage());
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
            public void onFailure(Call<List<FitnessPortfolio>> call, Throwable t) {
                Log.e("load_portfolio",t.getMessage());
            }
        });

      /*  this.resultData = Transformations.switchMap(this.portfolioData,portfolio->{
            return dbClient.getAppDatabase()
                    .getFitnessResultDao()
                    .getResultsFromPortfolio(portfolio.getFitnessPortfolioId());
        })
*/


    }

    public LiveData<List<FitnessPortfolio>> getPortfolioData(){
        return this.portfolioData;
    }

}
