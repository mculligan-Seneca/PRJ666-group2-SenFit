package com.example.senfit.fitnessResult;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddFitnessResultViewModelFactory implements ViewModelProvider.Factory {

        private static final String RESULT_FACTORY_TAG="result_factory_tag";
        private int portfolioId;
        public AddFitnessResultViewModelFactory(int portfolioId){
            this.portfolioId=portfolioId;
        }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(int.class).newInstance(this.portfolioId);
        }catch(ReflectiveOperationException rfe){
            Log.e(RESULT_FACTORY_TAG, rfe.getMessage());
            throw new RuntimeException("Unable to create view model of "+modelClass);
        }
    }
}
