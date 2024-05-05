package com.chihuasdevs.kmmrepositorykit.webServices


import com.chihuasdevs.kmmCoreKit.interfaces.businessToRepo.SpaceXWebservicesDelegate
import com.chihuasdevs.kmmCoreKit.models.webServices.spacexWS.RocketLaunch
import com.chihuasdevs.kmmrepositorykit.shared.cache.Database
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class SpaceXWS(private val database: Database): SpaceXWebservicesDelegate {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    override suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            val list: List<RocketLaunch> = httpClient.get("https://api.spacexdata.com/v5/launches").body()
            database.clearDatabase()
            database.createLaunches(list)
            list
        }
    }

}