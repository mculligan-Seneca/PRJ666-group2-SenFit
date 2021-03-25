package com.example.senfit.dataContext.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.senfit.dataContext.entities.Member;
import com.example.senfit.dataContext.entities.UnregisteredClient;

@Dao
public interface UnregisteredClientDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public Long insertUnregisteredClient(UnregisteredClient client);

}
