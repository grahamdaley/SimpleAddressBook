package com.gdaley

import pl.treksoft.kvision.remote.JoobyRemoteAgent
import pl.treksoft.kvision.remote.Request

object AddressAgent : JoobyRemoteAgent<AddressService>(AddressServiceManager)

actual class AddressService : IAddressService {
    override fun saveAddress(address: Address, req: Request?) =
        AddressAgent.call(IAddressService::saveAddress, address)

    override fun getAddressList(search: String?, req: Request?) =
        AddressAgent.call(IAddressService::getAddressList, search)

    override fun deleteAddress(id: String, req: Request?) =
            AddressAgent.call(IAddressService::deleteAddress, id)
}
