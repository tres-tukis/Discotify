package org.discordfy.audio

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler as LavaAudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

class AudioLoadResultHandler : LavaAudioLoadResultHandler {

    override fun trackLoaded(track: AudioTrack) {

    }

    override fun playlistLoaded(playlist: AudioPlaylist) {

    }

    override fun noMatches() {

    }

    override fun loadFailed(exception: FriendlyException?) {
        TODO("Not yet implemented")
    }
}