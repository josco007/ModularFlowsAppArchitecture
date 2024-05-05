package com.chihuasdevs.kmmrepositorykit

import com.chihuasdevs.kmmrepositorykit.database.SpaceXDB
import com.chihuasdevs.kmmrepositorykit.shared.cache.Database
import com.chihuasdevs.kmmrepositorykit.shared.cache.DatabaseDriverFactory
import com.chihuasdevs.kmmrepositorykit.webServices.OtpWS
import com.chihuasdevs.kmmrepositorykit.webServices.SpaceXWS
import com.chihuasdevs.kmmrepositorykit.webServices.UserWS

class KMMRepositoryKit(databaseDriverFactory: DatabaseDriverFactory) {


    private var userWS: UserWS? = null
    private var otpWS: OtpWS? = null
    private var spaceXWS: SpaceXWS? = null

    private var database: Database = Database(databaseDriverFactory)
    private var spaceXDB: SpaceXDB? = null

    fun getUserWS(): UserWS? {
        if (userWS == null) {
            userWS = UserWS()
        }
        return userWS
    }

    fun getOtpWS(): OtpWS? {
        if (otpWS == null) {
            otpWS = OtpWS()
        }
        return otpWS
    }

    fun getSpaceXWS(): SpaceXWS? {
        if (spaceXWS == null) {
            spaceXWS = SpaceXWS(database)
        }
        return spaceXWS
    }

    fun getSpaceXDB(): SpaceXDB? {
        if (spaceXDB == null) {
            spaceXDB = SpaceXDB(database)
        }
        return spaceXDB
    }
}