package cz.cvut.fel.chronorobotics.aewcompose.database

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(context: Context): AEWComposeDB {
        return Room.databaseBuilder(context, AEWComposeDB::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { provideDatabase(get()) }

}