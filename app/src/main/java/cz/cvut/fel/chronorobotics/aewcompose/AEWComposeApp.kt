package cz.cvut.fel.chronorobotics.aewcompose

import android.app.Application
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.addressBookModule
import cz.cvut.fel.chronorobotics.aewcompose.database.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AEWComposeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@AEWComposeApp)
            modules(
                databaseModule,
                addressBookModule,
            )
        }
    }

}