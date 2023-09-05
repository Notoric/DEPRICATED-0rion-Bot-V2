package com.fourwheelerstudio.Commands.Music;

import com.fourwheelerstudio.Commands.Libraries.Response;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class play {
    
    /**
     * This takes an event that has a song, checks if the song is a URL or a query,
     * if neccessary searches for the song on a given or default platform, and adds
     * the song to the currently playing queue.
     * @param event the event to respond to
     * @return whether the action passed or failed
     */
    public static boolean playCommand(SlashCommandInteractionEvent event) {

        event.deferReply().queue();
        
        String song = event.getOption("song").getAsString();
        Platform platform = Platform.NULL;
        short goodURL;

        try {
            String platformInput = event.getOption("platform").getAsString();
            platformInput = platformInput.replaceAll("\\s+", "").toLowerCase();

            switch(platformInput) {
                case "spotify":
                    platform = Platform.SPOTIFY;
                    break;
                case "soundcloud":
                    platform = Platform.SOUNDCLOUD;
                    break;
                case "youtube":
                    platform = Platform.YOUTUBE;
                    break;
                default:
                    platform = Platform.ERROR;
            }
        } catch(Exception ignore) {}

        if (platform.equals(Platform.NULL)) {
            //TODO: Get default platform from database
        }
        
        if (platform.equals(Platform.ERROR)) {
            Response.errorResponse(event, "The platform you entered was not valid. Valid platforms include: \nSpotify\nSoundcloud\nYoutube");
            return false;
        }
    
        goodURL = IsValidURL(song);

        if (goodURL == 2) {
            Response.errorResponse(event, "It looks like you provided a valid URL however it was not for a song or a platform we support. Please provide a link to a song on either Youtube, Soundcloud or Spotify, or [click here to get more help](https://notoric.net/help/music/url)."); //TODO: Replace url with valid alternative
            return false;
        }

        if (goodURL == 0) {
            song = GetURL(song, platform);
        }

        if (song == null) {
            Response.errorResponse(event, "There was an error while searching for this song, please try a different search term or platform, or provide a direct link");
            return false;
        }

        //TODO: Queue song in database
        return true;

    }

    private enum Platform {
        SPOTIFY,
        SOUNDCLOUD,
        YOUTUBE,
        NULL,
        ERROR
    }

    /**
     * Checks to see if a string is a URL for a valid location.
     * @param potentialURL the string to verify if it is a url or not
     * @return 0: the string is not a url, 1: the string is a valid url, 2: the string is a valid url for an unsupported location.
     */
    private static short IsValidURL(String potentialURL) {
        //TODO: Check if a string is a valid URL
        return 2;
    }

    /**
     * Searches for a song on a platform.
     * @param searchTerm the user provided search term
     * @param platform the platform to search on
     * @return the URL for the found song or NULL if no valid song could be found.
     */
    private static String GetURL(String searchTerm, Platform platform) {
        //TODO: Search a platform for the song provided
        return null;
    }

}
