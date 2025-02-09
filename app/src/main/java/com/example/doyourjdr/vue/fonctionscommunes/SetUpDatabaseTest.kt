package com.example.doyourjdr.vue.fonctionscommunes

import com.example.doyourjdr.data.room.etape.EtapeDao
import com.example.doyourjdr.data.room.etape.EtapeEntity
import com.example.doyourjdr.data.room.personnages.PersonnageDao
import com.example.doyourjdr.data.room.personnages.PersonnageEntity
import com.example.doyourjdr.data.room.scenario.ScenarioDao
import com.example.doyourjdr.data.room.scenario.ScenarioEntity

suspend fun setUpScenario(scenarioDao: ScenarioDao, etapeDao: EtapeDao, personnageDao: PersonnageDao) {
        if (scenarioDao.getScenario("Test") == null) {
            println("AUCUN SCENARIO DE TEST")

            val scenario = ScenarioEntity("Test", 1,
                "Ma description, j'ai pété les description sans abandonner les descriptions ni pété les descriptions")
            scenario.perosonnageNom.add("Mage")
            scenario.perosonnageNom.add("Heros")
            val etapes = listOf(
                EtapeEntity("Etape 1", 1, "Test"),
                EtapeEntity("Etape 2", 2, "Test")
            )

            scenarioDao.insertScenarioWithEtapes(scenario, etapes, etapeDao,  personnageDao )

            println("AJOUT SCENARIO REALISE")
            scenarioDao.getAllScenarios().forEach {
                println("Crée: $it")
            }
        } else {
            println("Scenario Test déja présent")
        }

    if (scenarioDao.getScenario("Test2") == null) {
        println("AUCUN SCENARIO DE TEST2")

        val scenario = ScenarioEntity("Test2", 1,
            "Ma description2, j'ai pété les description sans abandonner les descriptions ni pété les descriptions")
        scenario.perosonnageNom.add("Mage2")
        scenario.perosonnageNom.add("Heros")

        val etapes = listOf(
            EtapeEntity("Etape 12", 1, "Test2"),
            EtapeEntity("Etape 22", 2, "Test2")
        )


        scenarioDao.insertScenarioWithEtapes(scenario, etapes, etapeDao, personnageDao )

        println("AJOUT SCENARIO 2 REALISE")
        scenarioDao.getAllScenarios().forEach {
            println("Crée: $it")
        }
    } else {
        println("Scenario Test 52 déja présent")
    }
}