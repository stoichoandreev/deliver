package com.sniper.deliver

import com.sniper.deliver.di.testApplicationModule
import com.sniper.deliver.di.testNetworkModule
import org.koin.standalone.StandAloneContext.loadKoinModules


class TestDeliverApplication: DeliverApplication() {

    override fun initApplicationModules() {
        super.initApplicationModules()
        loadKoinModules(testApplicationModule, testNetworkModule)
    }

}
