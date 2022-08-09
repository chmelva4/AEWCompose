package cz.cvut.fel.chronorobotics.aewcompose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.AddressBookDao
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.DBAddressBookItem


@Database(
    entities = [
        DBAddressBookItem::class
    ],
    version = 1
)
abstract class AEWComposeDB: RoomDatabase() {

    abstract fun addressBookDao(): AddressBookDao
}