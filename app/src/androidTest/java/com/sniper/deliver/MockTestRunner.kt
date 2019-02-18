package com.sniper.deliver

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

@Suppress("unused")
class MockTestRunner: AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestDeliverApplication::javaClass.name, context)
    }
}