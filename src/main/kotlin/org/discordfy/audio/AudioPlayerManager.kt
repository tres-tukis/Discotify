package org.discordfy.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager as LavaAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers




object AudioPlayerManager {

    private val playerManager: LavaAudioPlayerManager =
        DefaultAudioPlayerManager()

    fun initialize() {

        AudioSourceManagers.registerRemoteSources(
            playerManager
        )

        println("Fuentes de Audio Registradas correctamente")

    }

    fun getPlayerManager(): LavaAudioPlayerManager {
        return playerManager
    }
}