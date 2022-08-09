package cz.cvut.fel.chronorobotics.aewcompose.addressBook

import cz.cvut.fel.chronorobotics.aewcompose.addressBook.data.AddressBookDao
import cz.cvut.fel.chronorobotics.aewcompose.addressBook.ui.AddressBookViewModel
import cz.cvut.fel.chronorobotics.aewcompose.database.AEWComposeDB
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addressBookModule = module {

    fun provideAddressBookDao(database: AEWComposeDB): AddressBookDao {
        return database.addressBookDao()
    }

    fun provideViewModel(dao: AddressBookDao): AddressBookViewModel {
        return AddressBookViewModel(dao)
    }

    single { provideAddressBookDao(get()) }
    viewModel {provideViewModel(get())}
}