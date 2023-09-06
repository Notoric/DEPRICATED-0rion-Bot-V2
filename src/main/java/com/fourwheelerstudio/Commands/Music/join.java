package com.fourwheelerstudio.Commands.Music;

import java.awt.Color;
import java.util.logging.Logger;

import com.fourwheelerstudio.Commands.Libraries.Response;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class join {
    /**
     * This command attempts to verify the user is in a
     * voice channel, attempt to join the voice channel
     * by calling the joinVC method, and respond to the
     * users initial slash command.
     * @param event the slash command event
     * @return whether the event passed or not
     */
    public static Boolean joinCommand (SlashCommandInteractionEvent event) {

        Logger logger = Logger.getLogger("orion");

        event.deferReply().queue();
        Member member = event.getMember();

        if (event.getGuild().getAudioManager().isConnected()) {
            Response.errorResponse(event, "The bot is already in a channel.");
            logger.info("User " + event.getUser().getName() + " tried to use /join however bot is already in a channel.");
            return false;
        }

        if (!member.getVoiceState().inAudioChannel()) {
            Response.errorResponse(event, "User is not in a Voice Channel.");
            logger.info("User " + event.getUser().getName() + " was not in a voice channel");
            return false;
        }
        
        try {
            VoiceChannel vc = member.getVoiceState().getChannel().asVoiceChannel();
            joinVC(vc);

            //here need to set db_guild.ActiveChannel to event.getChannel().asTextChannel();
            //this will be used to keep future messages in the same channel

            if (vc.getUserLimit() > 0) {
                Response.genericResponseA(event, "Joining...", "🔊 " + vc.getName(), "\n" + vc.getMembers().size() + "/" + vc.getUserLimit() + " users.", new Color(255, 85, 0));
                logger.info("Successfully joined " + vc.getName() + "at the request of " + event.getUser().getName());
                return true;
            }

            Response.genericResponseA(event, "Joining...", "🔊 " + vc.getName(), vc.getMembers().size() + " users.", new Color(255, 85, 0));
            logger.info("Successfully joined " + vc.getName() + " at the request of " + event.getUser().getName());
            return true;

        } catch(Exception e) {
            logger.warning(e.getMessage());
            logger.warning(e.getStackTrace().toString());
        }

        Response.errorResponse(event, "An unknown error occurred, make sure the bot has permissions to join the channel.");
        logger.info("There was an error joining " + event.getUser().getName() + "'s Voice Channel.");
        return false;
    }
    /**
     * This command accepts a voice channel and opens
     * an audio connection to it.
     * @param vc the voice channel to join
     */
    public static void joinVC(VoiceChannel vc) {
        AudioManager am = vc.getGuild().getAudioManager();
        am.openAudioConnection(vc);         
    }
}
