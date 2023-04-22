package net.raphimc.geyserplugin;

import net.lenni0451.lambdaevents.EventHandler;
import net.raphimc.geyserplugin.impl.VPGeyserBootstrap;
import net.raphimc.viaproxy.cli.options.Options;
import net.raphimc.viaproxy.plugins.PluginManager;
import net.raphimc.viaproxy.plugins.ViaProxyPlugin;
import net.raphimc.viaproxy.plugins.events.ConsoleCommandEvent;
import net.raphimc.viaproxy.plugins.events.ProxyStartEvent;
import net.raphimc.viaproxy.plugins.events.ProxyStopEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geysermc.geyser.GeyserImpl;
import org.geysermc.geyser.api.network.AuthType;
import org.geysermc.geyser.text.GeyserLocale;

import java.io.File;

public class GeyserPlugin extends ViaProxyPlugin {

    public static final Logger LOGGER = LogManager.getLogger("Geyser");
    public static final File ROOT_FOLDER = new File(PluginManager.PLUGINS_DIR, "Geyser");

    private VPGeyserBootstrap bootstrap;

    @Override
    public void onEnable() {
        ROOT_FOLDER.mkdirs();

        this.bootstrap = new VPGeyserBootstrap();
        GeyserLocale.init(this.bootstrap);
        this.bootstrap.onEnable();
        GeyserImpl.getInstance().shutdown();

        PluginManager.EVENT_MANAGER.register(this);
    }

    @EventHandler
    public void onConsoleCommand(final ConsoleCommandEvent event) {
        if (event.getCommand().equals("geyser") || event.getCommand().equals("/geyser")) {
            event.setCancelled(true);

            this.bootstrap.getGeyserCommandManager().runCommand(this.bootstrap.getGeyserLogger(), "geyser " + String.join(" ", event.getArgs()));
        }
    }

    @EventHandler
    public void onProxyStart(final ProxyStartEvent event) {
        this.bootstrap.onEnable(config -> {
            config.setAutoconfiguredRemote(true);
            config.getRemote().setAddress("127.0.0.1");
            config.getRemote().setPort(Options.BIND_PORT);
            config.getRemote().setAuthType(AuthType.OFFLINE);
        });
    }

    @EventHandler
    public void onProxyStop(final ProxyStopEvent event) {
        GeyserImpl.getInstance().shutdown();
    }

}
