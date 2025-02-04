package com.example.doyourjdr.data.room.personnages

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
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
    val nom: String,

    @ColumnInfo(name = "scenario_libelle")
    val scenarioLibelle: String,

    @ColumnInfo(name = "niveau", defaultValue = "1")
    val niveau: Int = 1,

    @ColumnInfo(name = "histoire", defaultValue = "Inconnue")
    val histoire: String = "Inconnue",

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
}
