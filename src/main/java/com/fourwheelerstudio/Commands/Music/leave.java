package com.fourwheelerstudio.Commands.Music;

import java.awt.Color;
import java.util.logging.Logger;

import com.fourwheelerstudio.Commands.Libraries.Response;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;

public class leave {
    /**
     * This command attempts to verify the bot is in a channel
     * and closes the connection if one is found. The bot
     * provides the user with an accurate response detailing
     * whether or not it found a channel and if it left.
     * @param event the event to reply to
     * @return whether the event passed or not
     */
    public static Boolean leaveCommand(SlashCommandInteractionEvent event) {

        event.deferReply().queue();

        Logger logger = Logger.getLogger("orion");

        if (!event.getGuild().getAudioManager().isConnected()) {
            Response.errorResponse(event, "The bot is not in a voice channel.");
            logger.info("@" + event.getGuild().getName() + " The bot could not disconnect as it was not in a channel.");
            return false;
        }
        
        try {
            VoiceChannel vc = event.getGuild().getAudioManager().getConnectedChannel().asVoiceChannel();
            event.getGuild().getAudioManager().closeAudioConnection();
            Response.genericResponse(event, "Leaving...", "Disconnecting from  **🔊 " + vc.getName() + "**", new Color(255, 85, 0));
            logger.info("@" + event.getGuild().getName() + " Bot has disconnected from " + vc.getName());
            return true;
        } catch(Exception e) {
            logger.warning(e.getMessage());
        }

        Response.errorResponse(event, "Could not leave voice chat, try getting an admin to disconnect the bot.");
        return false;
    }
}
