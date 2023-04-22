package net.raphimc.geyserplugin.impl;

import net.raphimc.geyserplugin.GeyserPlugin;
import net.raphimc.viaproxy.cli.ConsoleFormatter;
import org.geysermc.geyser.GeyserLogger;
import org.geysermc.geyser.command.GeyserCommandSource;

public class VPGeyserLogger implements GeyserLogger, GeyserCommandSource {

    @Override
    public void severe(String message) {
        GeyserPlugin.LOGGER.fatal(ConsoleFormatter.convert(message));
    }

    @Override
    public void severe(String message, Throwable error) {
        GeyserPlugin.LOGGER.fatal(ConsoleFormatter.convert(message), error);
    }

    @Override
    public void error(String message) {
        GeyserPlugin.LOGGER.error(ConsoleFormatter.convert(message));
    }

    @Override
    public void error(String message, Throwable error) {
        GeyserPlugin.LOGGER.error(ConsoleFormatter.convert(message), error);
    }

    @Override
    public void warning(String message) {
        GeyserPlugin.LOGGER.warn(ConsoleFormatter.convert(message));
    }

    @Override
    public void info(String message) {
        GeyserPlugin.LOGGER.info(ConsoleFormatter.convert(message));
    }

    @Override
    public void debug(String message) {
        GeyserPlugin.LOGGER.debug(ConsoleFormatter.convert(message));
    }

    @Override
    public void setDebug(boolean debug) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDebug() {
        return false;
    }

}
