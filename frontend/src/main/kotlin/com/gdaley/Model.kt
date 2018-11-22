package com.gdaley

import com.lightningkite.kotlin.observable.list.ObservableList
import com.lightningkite.kotlin.observable.list.observableListOf
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import pl.treksoft.kvision.utils.syncWithList

object Model {

    private val addressService = AddressService()
    val addresses: ObservableList<Address> = observableListOf()

    var search: String = ""
        set(value) {
            field = value
            GlobalScope.async {
                getAddressList()
            }
        }

    suspend fun saveAddress(address: Address) {
        addressService.saveAddress(address).await()
        getAddressList()
    }

    suspend fun deleteAddress(id: String) {
        addressService.deleteAddress(id).await()
        getAddressList()
    }

    suspend fun getAddressList() {
        val newAddresses = addressService.getAddressList(search).await()
        addresses.syncWithList(newAddresses)
    }

}