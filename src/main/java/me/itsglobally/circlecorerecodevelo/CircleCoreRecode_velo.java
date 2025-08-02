package me.itsglobally.circlecorerecodevelo;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.itsglobally.circlecorerecodevelo.commands.*;
import net.kyori.adventure.text.Component;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.player.PlayerLoginProcessEvent;
import net.luckperms.api.model.user.User;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Plugin(
        id = "circlecorerecode-velo",
        name = "CircleCoreRecode-velo",
        version = "1.0-SNAPSHOT",
        authors = {"ItsGlobally"}
)
public class CircleCoreRecode_velo {

    @Inject
    private Logger logger;
    public static ProxyServer ps;
    @Inject
    public CircleCoreRecode_velo(ProxyServer ps, @DataDirectory Path dd) {
        this.ps = ps;
    }
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent e) {
        CommandManager cm = ps.getCommandManager();
        CommandMeta msgm = cm.metaBuilder("msg")
                .aliases("m", "r", "reply", "tell")
                .plugin(this)
                .build();
        cm.register(msgm, new Msg(ps));
    }
    @Subscribe
    public void onJoin(PostLoginEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        LuckPerms lp = LuckPermsProvider.get();
        lp.getUserManager().loadUser(uuid).thenAcceptAsync(user -> {
            String prefix = user.getCachedData().getMetaData().getPrefix();
            if (prefix == null) prefix = "§7";
            data.setPrefix(uuid, prefix);
        });
    }

}
