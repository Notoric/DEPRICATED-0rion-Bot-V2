package com.fourwheelerstudio.Commands.Listeners;

import java.util.logging.Logger;

import org.jetbrains.annotations.NotNull;

import com.fourwheelerstudio.Commands.Music.join;
import com.fourwheelerstudio.Commands.Music.leave;
import com.fourwheelerstudio.Commands.Music.play;
import com.fourwheelerstudio.Commands.Chat.website;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    @Override
    /**
     * Listens for the user to use a slash command as defined in CommandBuilder
     * Calls the relevant function(s) after logging the interaction
     * @param event the slash command event
     */
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        
        Logger logger = Logger.getLogger("orion");
        logger.info("@" + event.getGuild().getName() + " #" + event.getChannel().getName() + " - " + event.getUser().getName() + ": " + event.getCommandString());

        if (event.getName().equalsIgnoreCase("join")) {
            join.joinCommand(event);
        }
        if (event.getName().equalsIgnoreCase("leave") || event.getName().equalsIgnoreCase("stop") || event.getName().equalsIgnoreCase("destroy")) {
            leave.leaveCommand(event);
        }
        if (event.getName().equalsIgnoreCase("play") || event.getName().equalsIgnoreCase("queue")) {
            play.playCommand(event);
        }
        if (event.getName().equalsIgnoreCase("website")) {
            website.websiteCommand(event);
        }
    }
}
