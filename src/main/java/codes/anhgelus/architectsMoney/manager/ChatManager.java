package codes.anhgelus.architectsMoney.manager;

import codes.anhgelus.architectsMoney.ArchitectsMoney;

public class ChatManager {

    /**
     * Send a message in the console
     *
     * @param message Message
     */
    public static void sendConsoleMessage(String message) {
        ArchitectsMoney.LOGGER.info("[" + ArchitectsMoney.PLUGIN_NAME + "] " + message);
    }

    /**
     * Send a warning in the console
     *
     * @param message Message
     */
    public static void sendWarnMessage(String message) {
        ArchitectsMoney.LOGGER.warning("[" + ArchitectsMoney.PLUGIN_NAME + "] " + message);
    }

}
