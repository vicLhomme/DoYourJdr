package com.example.doyourjdr.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "scenario")
data class ScenarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,


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

    @ColumnInfo
    val etapes : MutableList<EtapeEntity>,
     */
)
