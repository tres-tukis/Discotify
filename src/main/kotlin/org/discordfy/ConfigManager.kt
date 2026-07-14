package org.discordfy

import java.util.Properties

class ConfigManager {

    private val properties = Properties()

    fun load() {

        val inputStream = javaClass.classLoader.getResourceAsStream("config.properties")
            ?: throw IllegalArgumentException("No se encontró config.properties!")

        properties.load(inputStream)

        }
        fun getToken(): String {
            return properties.getProperty("discord.token")
        }

}