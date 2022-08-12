package cz.cvut.fel.chronorobotics.aewcompose.addressBook.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "addressBook", indices = [Index(value = ["name"], unique = true), Index(value = ["ethereumAddress"], unique = true)])
data class DBAddressBookItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var ethereumAddress: String,
    var name: String
): Parcelable