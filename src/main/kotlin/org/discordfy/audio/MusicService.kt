package org.discordfy.audio

import net.dv8tion.jda.api.entities.Guild

object MusicService {

    private val musicManagers: MutableMap<Long, GuildMusicManager> =
        mutableMapOf()

    fun getGuildMusicManager(
        guild: Guild
    ): GuildMusicManager {

        return musicManagers.getOrPut(guild.idLong) {

            GuildMusicManager(
                AudioPlayerManager.getPlayerManager()
            )

        }

    }

    fun loadAndPlay(
        guild: Guild,
        query: String
    ) {

        println("🎶 MusicService recibió: $query")

        val musicManager =
            getGuildMusicManager(guild)

        TrackLoader.loadTrack(
            musicManager,
            guild,
            query
        )
    }
}