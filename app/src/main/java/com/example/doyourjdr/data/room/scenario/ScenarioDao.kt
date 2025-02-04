package com.example.doyourjdr.data.room.scenario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.doyourjdr.data.room.etape.EtapeDao
import com.example.doyourjdr.data.room.etape.EtapeEntity
import com.example.doyourjdr.data.room.personnages.PersonnageDao
import com.example.doyourjdr.data.room.personnages.PersonnageEntity

@Dao
interface ScenarioDao {
    @Insert
    suspend fun insert(scenarioEntity: ScenarioEntity)

    @Query("SELECT * FROM scenario")
    suspend fun getAllScenarios(): List<ScenarioEntityComplete>


    @Transaction
    @Query("SELECT * FROM scenario WHERE scenario.libelle = :id")
    suspend fun getScenario(id: String): ScenarioEntityComplete

    @Transaction
    suspend fun insertScenarioWithEtapes(scenario: ScenarioEntity,
                                         etapes: List<EtapeEntity>,
                                         etapeDao: EtapeDao,
                                         personnages: List<PersonnageEntity>,
                                         personnageDao: PersonnageDao) {
        insert(scenario)
        personnages.forEach { personnageDao.insertPersonnages(it) }
        etapes.forEach { etapeDao.insertEtape(it) }
    }


}
