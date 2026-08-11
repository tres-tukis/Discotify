package org.discordfy.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame
import net.dv8tion.jda.api.audio.AudioSendHandler
import java.nio.ByteBuffer

class AudioPlayerSendHandler(
    private val audioPlayer: AudioPlayer
) : AudioSendHandler {

    private val buffer = ByteBuffer.allocate(2048)

    private val frame = MutableAudioFrame().apply {
        setBuffer(buffer)
    }

    override fun canProvide(): Boolean {

        return audioPlayer.provide(frame)

    }

    override fun provide20MsAudio(): ByteBuffer {

        println("📦 enviando frame: ${buffer.position()} bytes")

        buffer.flip()

        return buffer
    }

    override fun isOpus(): Boolean = true
}