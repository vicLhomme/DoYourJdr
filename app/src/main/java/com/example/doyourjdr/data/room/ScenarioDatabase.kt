package com.example.doyourjdr.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.doyourjdr.data.room.etape.EtapeDao
import com.example.doyourjdr.data.room.etape.EtapeEntity
import com.example.doyourjdr.data.room.personnages.Converters
import com.example.doyourjdr.data.room.personnages.PersonnageDao
import com.example.doyourjdr.data.room.personnages.PersonnageEntity
import com.example.doyourjdr.data.room.personnages.RelationEntity
import com.example.doyourjdr.data.room.scenario.ScenarioDao
import com.example.doyourjdr.data.room.scenario.ScenarioEntity

@TypeConverters(Converters::class)
@Database(entities = [ScenarioEntity::class, EtapeEntity::class, PersonnageEntity::class, RelationEntity::class], version = 1)
//@Database(entities = [ScenarioEntity::class, EtapeEntity::class, PersonnageEntity::class], version = 1)
abstract class ScenarioDatabase : RoomDatabase() {
    abstract fun scenarioDao(): ScenarioDao
    abstract fun etapeDao(): EtapeDao
    abstract fun personnageDao(): PersonnageDao

    companion object {
        @Volatile
        private var INSTANCE: ScenarioDatabase? = null

        fun getDatabase(context: Context): ScenarioDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScenarioDatabase::class.java,
                    "scenario"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}