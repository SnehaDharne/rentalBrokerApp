package com.example.houserentalsapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TenantDao {

    @Insert
    void registerTenant(TenantEntity tenantEntity);

    @Query("SELECT * from tenants where userId=(:userId) and password=(:password)")
    TenantEntity btnSignIn(String userId, String password);

}
