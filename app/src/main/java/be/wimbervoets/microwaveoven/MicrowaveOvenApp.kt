package be.wimbervoets.microwaveoven

import android.app.Application
import timber.log.Timber

class MicrowaveOvenApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}