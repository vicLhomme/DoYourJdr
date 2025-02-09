package com.example.doyourjdr.data.room.scenario

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.doyourjdr.data.room.personnages.PersonnageEntity
import com.example.doyourjdr.data.room.etape.EtapeEntity
import com.example.doyourjdr.data.room.personnages.Converters


@Entity(tableName = "scenario")
data class ScenarioEntity(

    @PrimaryKey
    val libelle : String,

    val avancement : Int,

    val descriptionGlobale : String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "personnages_nom")
    val perosonnageNom: MutableList<String> = mutableListOf(),

    /*
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
    val etapes: List<EtapeEntity>,

    /*
    @Relation(
        parentColumn = "libelle",
        entityColumn = "scenarios_libelle"
    )
    val personnages : List<PersonnageEntity>
     */
)

