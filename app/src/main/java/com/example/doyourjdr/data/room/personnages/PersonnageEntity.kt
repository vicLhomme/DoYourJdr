package com.example.doyourjdr.data.room.personnages

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.doyourjdr.data.room.scenario.ScenarioEntity


@Entity(
    tableName = "personnage",
    foreignKeys = [ForeignKey(
        entity = ScenarioEntity::class,
        parentColumns = ["libelle"],
        childColumns = ["scenario_libelle"]
    )]
)
data class PersonnageEntity(
    @PrimaryKey
    val nom : String,
    @ColumnInfo(name = "scenario_libelle")
    val scenarioLibelle: String
)
