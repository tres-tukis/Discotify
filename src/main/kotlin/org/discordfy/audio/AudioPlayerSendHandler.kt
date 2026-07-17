package org.discordfy.audio

import net.dv8tion.jda.api.audio.AudioSendHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import java.nio.ByteBuffer

class AudioPlayerSendHandler(
    private val audioPlayer: AudioPlayer
) : AudioSendHandler {
    override fun canProvide(): Boolean {
        return audioPlayer.playingTrack != null
    }

    override fun provide20MsAudio(): ByteBuffer? {
        val data = audioPlayer.provide()

        return if (data != null) {
            ByteBuffer.wrap(data.data)
        } else {
            null
        }
    }

}