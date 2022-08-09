package cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui

import androidx.lifecycle.ViewModel
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.AddressBookDao

class AddressBookViewModel(
    private val dao: AddressBookDao
): ViewModel(), AddressBookDao by dao {
}