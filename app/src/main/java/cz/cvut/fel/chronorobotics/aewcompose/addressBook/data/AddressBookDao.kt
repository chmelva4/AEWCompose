package cz.cvut.fel.chronorobotics.aewcompose.addressBook.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressBookDao {

    @Insert()
    suspend fun insert(item: DBAddressBookItem)

    @Update()
    suspend fun edit(item: DBAddressBookItem)

    @Delete
    suspend fun delete(item: DBAddressBookItem)

    @Query("select * from addressBook")
    fun getAddressBookItems(): Flow<List<DBAddressBookItem>>

}