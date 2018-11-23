package com.gdaley

import org.jooby.Jooby
import org.jooby.mongodb.IdGen
import org.jooby.mongodb.Monphia
import org.mongodb.morphia.converters.ObjectIdConverter
import pl.treksoft.kvision.remote.KVServer

class App : KVServer({
    use(Monphia()
        .with(IdGen.GLOBAL)
        .doWith { morphia, _ ->
            morphia.mapper.converters.addConverter(ObjectIdConverter::class.java)
        })

    ContactServiceManager.applyRoutes(this)
})

fun main(args: Array<String>) {
    Jooby.run(::App, args)
}