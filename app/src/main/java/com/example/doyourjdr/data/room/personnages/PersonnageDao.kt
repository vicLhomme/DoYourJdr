package com.example.doyourjdr.data.room.personnages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface PersonnageDao {
    @Insert
    suspend fun insertPersonnages(personnageEntity: PersonnageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAmical(relation: RelationEntity)

    @Query("SELECT * FROM personnage")
    suspend fun getAllPersonnages(): List<PersonnageEntity>

    @Query("SELECT * FROM personnage WHERE personnage.nom = :id")
    suspend fun getPersonnage(id: String): PersonnageEntity

    @Query("SELECT EXISTS(SELECT 1 FROM personnage WHERE nom = :nom )")
    suspend fun exist(nom: String): Boolean


    @Query("SELECT EXISTS(SELECT 1 FROM personnage WHERE scenarios_libelle  LIKE '%' || :libelle || '%')")
    suspend fun hasThisScenario(libelle: String): Boolean

    @Transaction
    suspend fun addScenarioToPersonnage(nom: String, scenario: String) {
        // Récupère le personnage existant
        val personnage = getPersonnage(nom)

        // Ajoute le scénario à la liste
        personnage?.let {
            it.scenariosLibelle.add(scenario)
            // Met à jour l'entité personnage avec la nouvelle liste
            updatePersonnage(it)
        }
    }
    @Update
    suspend fun updatePersonnage(personnage: PersonnageEntity)
}

