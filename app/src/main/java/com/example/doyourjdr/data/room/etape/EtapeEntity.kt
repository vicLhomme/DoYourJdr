package com.example.doyourjdr.data.room.etape

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.doyourjdr.data.room.scenario.ScenarioEntity

@Entity(tableName = "etape",
    primaryKeys = ["libelle"],
    foreignKeys = [ForeignKey(
        entity = ScenarioEntity::class,
        parentColumns = ["libelle"],  // La clé primaire de ScenarioEntity
        childColumns = ["scenario_libelle"],  // La clé étrangère dans EtapeEntity
        onDelete = ForeignKey.CASCADE // Supprime les étapes si le scénario est supprimé
    )]
)
data class EtapeEntity (

    val libelle : String,
    val numero : Int,
    @ColumnInfo("scenario_libelle")
    val scenarioLibelle: String

    /*
    val personnages : MutableList<PersonnageEntity>,
    val cartes : MutableList<CartesEntity>,
    val notesAvancement : String,
    val bilan : MutableList<AmeliorationEntity>,
    val coordonneePosition : Pair<Long, Long>
     */
)
