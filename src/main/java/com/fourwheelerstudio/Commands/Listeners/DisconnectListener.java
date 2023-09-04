package com.fourwheelerstudio.Commands.Listeners;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.fourwheelerstudio.Commands.Libraries.Response;

import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DisconnectListener extends ListenerAdapter {
    Logger logger = Logger.getLogger("orion");
    /**
     * Listens for a GuildVoiceUpdateEvent and reacts to it
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
            OffsetDateTime now = OffsetDateTime.now().minus(Long.valueOf(1), ChronoUnit.SECONDS);
            /*
             * waits 1 second and grabs the server logs
             * loops through them and breaks if the log is more than 2 seconds old
             */
            event.getGuild().retrieveAuditLogs().queueAfter(1, TimeUnit.SECONDS, (logs) -> {
                for (AuditLogEntry log : logs) { // Potantial optimisation: only pull the last minute or however long of logs.
                    if (log.getType() == ActionType.MEMBER_VOICE_KICK) {
                        if (now.isAfter(log.getTimeCreated())) {
                            break;
                        }
                        System.out.println(log.getTimeCreated());
                        logger.info("@" + event.getGuild().getName() + ": Forceful Disconnection Detected!");
                        Response.Disconnected(event);
                    }
                }
            });
            return;
        }
        /*
         * This checks if the bot has been moved from a channel to another channel
         */
        if (event.getChannelLeft() != null) {
            logger.info("@" + event.getGuild().getName() + ": Bot has been moved from  🔊 " + event.getChannelLeft().getName() + " to  🔊 " + event.getChannelJoined().getName());
            Response.Moved(event);
            return;
        }
    }
}
