package org.discordfy.audio

import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

class TrackScheduler(
    private val player: com.sedmelluq.discord.lavaplayer.player.AudioPlayer
) : AudioEventAdapter() {

}