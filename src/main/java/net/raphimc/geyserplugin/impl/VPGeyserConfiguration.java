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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.raphimc.geyserplugin.GeyserPlugin;
import org.geysermc.geyser.configuration.GeyserJacksonConfiguration;

import java.io.File;
import java.nio.file.Path;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VPGeyserConfiguration extends GeyserJacksonConfiguration {

    @Override
    public Path getFloodgateKeyPath() {
        return new File(GeyserPlugin.ROOT_FOLDER, this.getFloodgateKeyFile()).toPath();
    }

}
