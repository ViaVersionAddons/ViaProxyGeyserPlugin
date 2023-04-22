/*
 * This file is part of ViaProxyGeyserPlugin - https://github.com/RaphiMC/ViaProxyGeyserPlugin
 * Copyright (C) 2023 RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
