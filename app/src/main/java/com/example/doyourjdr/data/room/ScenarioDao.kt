package com.example.doyourjdr.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScenarioDao {
    @Insert
    suspend fun insert(scenarioEntity: ScenarioEntity)

    @Query("SELECT * FROM scenario")
    suspend fun getAllScenario(): List<ScenarioEntity>
}
