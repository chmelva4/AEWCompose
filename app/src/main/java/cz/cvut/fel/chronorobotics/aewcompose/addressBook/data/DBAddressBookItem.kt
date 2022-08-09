package cz.cvut.fel.chronorobotics.aewcompose.addressBook.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "addressBook", indices = [Index(value = ["name"], unique = true), Index(value = ["ethereumAddress"], unique = true)])
data class DBAddressBookItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var ethereumAddress: String,
    var name: String
)