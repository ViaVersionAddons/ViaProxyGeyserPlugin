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
import org.geysermc.common.PlatformType;
import org.geysermc.geyser.GeyserBootstrap;
import org.geysermc.geyser.GeyserImpl;
import org.geysermc.geyser.GeyserLogger;
import org.geysermc.geyser.command.GeyserCommandManager;
import org.geysermc.geyser.configuration.GeyserConfiguration;
import org.geysermc.geyser.dump.BootstrapDumpInfo;
import org.geysermc.geyser.ping.GeyserLegacyPingPassthrough;
import org.geysermc.geyser.ping.IGeyserPingPassthrough;
import org.geysermc.geyser.text.GeyserLocale;
import org.geysermc.geyser.util.FileUtils;
import org.geysermc.geyser.util.LoopbackUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.function.Consumer;

public class VPGeyserBootstrap implements GeyserBootstrap {

    private VPGeyserLogger logger;
    private VPGeyserConfiguration config;

    private GeyserImpl geyser;
    private GeyserCommandManager commandManager;
    private IGeyserPingPassthrough pingPassthrough;

    @Override
    public void onEnable() {
        this.onEnable(null);
    }

    public void onEnable(final Consumer<VPGeyserConfiguration> configModifier) {
        this.logger = new VPGeyserLogger();
        LoopbackUtil.checkAndApplyLoopback(this.logger);

        try {
            final File configFile = FileUtils.fileOrCopiedFromResource(new File(GeyserPlugin.ROOT_FOLDER, "config.yml"), "config.yml", s -> s.replaceAll("generateduuid", UUID.randomUUID().toString()), this);
            this.config = FileUtils.loadConfig(configFile, VPGeyserConfiguration.class);
        } catch (IOException e) {
            this.logger.severe(GeyserLocale.getLocaleStringLog("geyser.config.failed"), e);
            System.exit(1);
        }

        if (configModifier != null) {
            configModifier.accept(this.config);
        }
        GeyserConfiguration.checkGeyserConfiguration(this.config, this.logger);

        this.geyser = GeyserImpl.load(PlatformType.STANDALONE, this);
        GeyserImpl.start();

        this.commandManager = new GeyserCommandManager(this.geyser);
        this.commandManager.init();

        this.pingPassthrough = GeyserLegacyPingPassthrough.init(this.geyser);
    }

    @Override
    public void onDisable() {
        this.geyser.shutdown();
        System.exit(0);
    }

    @Override
    public GeyserConfiguration getGeyserConfig() {
        return this.config;
    }

    @Override
    public GeyserLogger getGeyserLogger() {
        return this.logger;
    }

    @Override
    public GeyserCommandManager getGeyserCommandManager() {
        return this.commandManager;
    }

    @Override
    public IGeyserPingPassthrough getGeyserPingPassthrough() {
        return this.pingPassthrough;
    }

    @Override
    public Path getConfigFolder() {
        return GeyserPlugin.ROOT_FOLDER.toPath();
    }

    @Override
    public BootstrapDumpInfo getDumpInfo() {
        return new VPGeyserDumpInfo();
    }

}
