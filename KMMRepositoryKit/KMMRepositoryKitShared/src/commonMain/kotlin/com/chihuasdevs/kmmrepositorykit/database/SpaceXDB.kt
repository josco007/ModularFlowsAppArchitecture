package com.chihuasdevs.kmmrepositorykit.database

import com.chihuasdevs.kmmCoreKit.models.webServices.spacexWS.RocketLaunch
import com.chihuasdevs.kmmrepositorykit.shared.cache.Database

class SpaceXDB(val database: Database) {

//    @Throws(Exception::class)
//    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
//        val cachedLaunches = database.getAllLaunches()
//        return if (cachedLaunches.isNotEmpty() && !forceReload) {
//            cachedLaunches
//        } else {
//            api.getAllLaunches().also {
//                database.clearDatabase()
//                database.createLaunches(it)
//            }
//        }
//    }
}