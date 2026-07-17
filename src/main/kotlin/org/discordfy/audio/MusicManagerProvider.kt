package org.discordfy.audio

import net.dv8tion.jda.api.entities.Guild

object MusicManagerProvider {

    private val musicManagers = mutableMapOf<Long, GuildMusicManager>()

    fun getMusicManager(guild: Guild): GuildMusicManager {

        return musicManagers.getOrPut(guild.idLong){

            GuildMusicManager(AudioPlayerManager.getPlayerManager())
        }
    }

}