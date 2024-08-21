package tech.capitalcoding.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.capitalcoding.basic_feature.data.local.dao.PokemonDao

private const val DATABASE_VERSION = 1

@Database(
    entities = [],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}