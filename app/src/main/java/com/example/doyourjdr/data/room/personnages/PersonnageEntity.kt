package com.example.doyourjdr.data.room.personnages

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.doyourjdr.data.room.scenario.ScenarioEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(
    tableName = "personnage",
    /*
    foreignKeys = [ForeignKey(
        entity = ScenarioEntity::class,
        parentColumns = ["libelle"],
        childColumns = ["scenario_libelle"]
    )],
     */
    primaryKeys = ["nom"] // Déclaration de la clé primaire composée
)
data class PersonnageEntity(
    val nom: String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "scenarios_libelle")
    val scenariosLibelle: MutableList<String>,

    @ColumnInfo(name = "niveau", defaultValue = "1")
    val niveau: Int = 1,

    @ColumnInfo(name = "histoire", defaultValue = "Inconnue")
    val histoire: String = "Inconnue"
)


@Entity(
    tableName = "relations",
    primaryKeys = ["perso1", "perso2"],
    foreignKeys = [
        ForeignKey(
            entity = PersonnageEntity::class,
            parentColumns = ["nom"],
            childColumns = ["perso1"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PersonnageEntity::class,
            parentColumns = ["nom"],
            childColumns = ["perso2"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RelationEntity(
    val perso1: String,
    val perso2: String,

    @TypeConverters(Converters::class)
    val relation: RelationEnum = RelationEnum.NEUTRE
)

enum class RelationEnum {
    AMOUR, AMI, NEUTRE, MEFIANT, ENNEMI
}

class Converters {
    @TypeConverter
    fun fromRelationEnum(value: RelationEnum): String {
        return value.name
    }

    @TypeConverter
    fun toRelationEnum(value: String): RelationEnum {
        return RelationEnum.valueOf(value)
    }

    private val gson = Gson()

    // Convertir MutableList<String> en String (JSON)
    @TypeConverter
    fun fromScenarioList(scenarios: MutableList<String>): String {
        return gson.toJson(scenarios)
    }

    // Convertir String (JSON) en MutableList<String>
    @TypeConverter
    fun toScenarioList(data: String): MutableList<String> {
        val listType = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(data, listType)
    }
}
