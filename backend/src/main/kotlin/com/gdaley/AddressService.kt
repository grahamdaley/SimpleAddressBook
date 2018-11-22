package com.gdaley

import kotlinx.coroutines.Deferred
import pl.treksoft.kvision.remote.Request
import pl.treksoft.kvision.remote.async

actual class AddressService : IAddressService {
    override fun getAddressList(search: String?, req: Request?): Deferred<List<Address>> = req.async {
        val addressRepository = req!!.require(AddressRepository::class.java)
        addressRepository.findAddressesByName(search!!)
    }

    override fun saveAddress(address: Address, req: Request?): Deferred<Address> = req.async {
        val addressRepository = req!!.require(AddressRepository::class.java)
        addressRepository.save(address)
    }

    override fun deleteAddress(id: String, req: Request?): Deferred<Boolean> = req.async {
        val addressRepository = req!!.require(AddressRepository::class.java)
        addressRepository.delete(id)
        true
    }
}