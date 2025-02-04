package com.example.doyourjdr.data.room.etape

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EtapeDao {
    @Insert
    suspend fun insertEtape(etapeEntity: EtapeEntity)

    @Query("SELECT * FROM etape")
    suspend fun getAllScenarios(): List<EtapeEntity>

    @Query("SELECT * FROM etape WHERE etape.libelle = :id")
    suspend fun getScenario(id: String): EtapeEntity

}
