package com.example.doyourjdr.vue.fonctionscommunes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.doyourjdr.data.room.etape.EtapeDao
import com.example.doyourjdr.data.room.etape.EtapeEntity
import com.example.doyourjdr.data.room.scenario.ScenarioDao
import com.example.doyourjdr.data.room.scenario.ScenarioEntity

suspend fun setUpScenario(scenarioDao: ScenarioDao, etapeDao: EtapeDao) {
        if (scenarioDao.getScenario("Test") == null) {
            println("AUCUN SCENARIO DE TEST")

            val scenario = ScenarioEntity("Test", 1)
            val etapes = listOf(
                EtapeEntity("Etape 1", 1, "Test"),
                EtapeEntity("Etape 2", 2, "Test")
            )

            scenarioDao.insertScenarioWithEtapes(scenario, etapes, etapeDao )
            println("AJOUT SCENARIO REALISE")
            scenarioDao.getAllScenarios().forEach {
                println("Crée: $it")
            }
        } else {
            println("Scenario Test déja présent")
        }

}