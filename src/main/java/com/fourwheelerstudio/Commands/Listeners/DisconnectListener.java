package com.fourwheelerstudio.Commands.Listeners;

import java.util.logging.Logger;

import com.fourwheelerstudio.Commands.Libraries.Response;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DisconnectListener extends ListenerAdapter {
    Logger logger = Logger.getLogger("orion");
    /**
     * Listens for a GuildVoiceUpdateEvent and reacts to it
     * This only reacts to the bot changing channels, including
     * disconnecting.
     * @param event the event to respond to
     */
    
    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (!event.getMember().equals(event.getGuild().getSelfMember())) {
            return;
        }
        /*
         * This checks if the bot has been disconnected from a channel
         */
        if (event.getChannelJoined() == null) {

            logger.info("@" + event.getGuild().getName() + ": I have been disconnected");
            Response.Disconnected(event);
            return;
        }
        /*
         * This checks if the bot has been moved from one channel to another channel
         */
        if (event.getChannelLeft() != null) {
            logger.info("@" + event.getGuild().getName() + ": Bot has been moved from  🔊 " + event.getChannelLeft().getName() + " to  🔊 " + event.getChannelJoined().getName());
            Response.Moved(event);
            return;
        }
    }

}
