package org.discordfy.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager

class GuildMusicManager(

    playerManager: AudioPlayerManager

) {

    val player: AudioPlayer = playerManager.createPlayer()

    val sendHandler = AudioPlayerSendHandler(player)

    val scheduler = TrackScheduler(player)


    init {
        player.addListener(scheduler)
    }

}