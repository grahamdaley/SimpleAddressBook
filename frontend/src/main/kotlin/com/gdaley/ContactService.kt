package com.gdaley

import pl.treksoft.kvision.remote.JoobyRemoteAgent
import pl.treksoft.kvision.remote.Request

object ContactAgent : JoobyRemoteAgent<ContactService>(ContactServiceManager)

actual class ContactService : IContactService {
    override fun saveContact(contact: Contact, req: Request?) =
        ContactAgent.call(IContactService::saveContact, contact)

    override fun getContactList(req: Request?) =
        ContactAgent.call(IContactService::getContactList)

    override fun deleteContact(id: String, req: Request?) =
            ContactAgent.call(IContactService::deleteContact, id)
}
