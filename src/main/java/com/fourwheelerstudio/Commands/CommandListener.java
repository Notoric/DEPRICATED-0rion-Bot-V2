package com.fourwheelerstudio.Commands;

import org.jetbrains.annotations.NotNull;

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

        //get time

        //log command

        //add command

    }
}
