package com.gdaley

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.treksoft.kvision.hmr.ApplicationBase
import pl.treksoft.kvision.panel.Root
import pl.treksoft.kvision.panel.SplitPanel.Companion.splitPanel
import pl.treksoft.kvision.utils.vh

object App : ApplicationBase {

    private lateinit var root: Root

    override fun start(state: Map<String, Any>) {

        root = Root("kvapp") {
            add(MainPanel)
        }

        GlobalScope.launch {
            Model.getAddressList()
        }
    }

    override fun dispose(): Map<String, Any> {
        root.dispose()
        return mapOf()
    }
}
