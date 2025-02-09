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
    suspend fun insertScenarioWithEtapes(
        scenario: ScenarioEntity,
        etapes: List<EtapeEntity>,
        etapeDao: EtapeDao,
        personnageDao: PersonnageDao
    ) {
        insert(scenario)
        scenario.perosonnageNom.forEach {
            println("SCENARIO DAO : ${!personnageDao.exist(it)}")
            if (!personnageDao.exist(it)) {
                personnageDao.insertPersonnages(PersonnageEntity(it, mutableListOf(scenario.libelle)))
            }
            else if(!personnageDao.hasThisScenario(scenario.libelle)){
                personnageDao.addScenarioToPersonnage(scenario.libelle, it)
            }
        }
        etapes.forEach {
            etapeDao.insertEtape(it)
        }


    }
}
