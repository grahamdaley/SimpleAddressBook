package com.gdaley

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.treksoft.kvision.hmr.ApplicationBase
import pl.treksoft.kvision.panel.Root

object App : ApplicationBase {

    private lateinit var root: Root

    override fun start(state: Map<String, Any>) {

        root = Root("kvapp") {
            add(MainPanel)
        }

        GlobalScope.launch {
            Model.getContactList()
        }
    }

    override fun dispose(): Map<String, Any> {
        root.dispose()
        return mapOf()
    }
}
