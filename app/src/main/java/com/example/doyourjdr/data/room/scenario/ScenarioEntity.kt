package com.example.doyourjdr.data.room.scenario

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.doyourjdr.data.room.etape.EtapeEntity


@Entity(tableName = "scenario")
data class ScenarioEntity(

    @PrimaryKey
    val libelle : String,

    val avancement : Int,

    /*
    @ColumnInfo
    val descriptionGlobale : String,

    @ColumnInfo
    val personnages : MutableList<PersonnageEntity>,

    @ColumnInfo
    val carteGlobale : CartesEntity,

    @ColumnInfo
    val sousCartes : MutableList<CartesEntity>,
     */
)
data class ScenarioEntityComplete(
    @Embedded val scenario: ScenarioEntity,

    @Relation(
        parentColumn = "libelle",
        entityColumn = "scenario_libelle"
    )
    val etapes: List<EtapeEntity>
)

