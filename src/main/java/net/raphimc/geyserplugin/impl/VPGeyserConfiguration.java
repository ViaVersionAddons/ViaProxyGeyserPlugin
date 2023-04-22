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
