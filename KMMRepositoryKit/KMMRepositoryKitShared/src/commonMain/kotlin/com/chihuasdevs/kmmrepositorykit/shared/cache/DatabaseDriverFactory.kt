package com.chihuasdevs.kmmrepositorykit.shared.cache


import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}