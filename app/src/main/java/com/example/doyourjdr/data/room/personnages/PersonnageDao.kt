package com.example.doyourjdr.data.room.personnages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonnageDao {
    @Insert
    suspend fun insertPersonnages(personnageEntity: PersonnageEntity)

    @Query("SELECT * FROM personnage")
    suspend fun getAllPersonnages(): List<PersonnageEntity>

    @Query("SELECT * FROM personnage WHERE personnage.nom = :id")
    suspend fun getPersonnage(id: String): PersonnageEntity

}
