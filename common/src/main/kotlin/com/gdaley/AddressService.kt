package com.gdaley

import kotlinx.coroutines.Deferred
import pl.treksoft.kvision.remote.JoobyServiceManager
import pl.treksoft.kvision.remote.Request

interface IAddressService {
    fun getAddressList(search: String?, req: Request? = null): Deferred<List<Address>>
    fun saveAddress(address: Address, req: Request? = null): Deferred<Address>
    fun deleteAddress(id: String, req: Request? = null): Deferred<Boolean>
}

expect class AddressService() : IAddressService

object AddressServiceManager : JoobyServiceManager<AddressService>(AddressService()) {
    init {
        bind(AddressService::getAddressList)
        bind(AddressService::saveAddress)
        bind(AddressService::deleteAddress)
    }
}
