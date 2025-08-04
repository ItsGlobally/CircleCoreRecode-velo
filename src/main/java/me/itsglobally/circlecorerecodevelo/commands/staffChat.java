package me.itsglobally.circlecorerecodevelo.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import me.itsglobally.circlecorerecodevelo.data;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.UUID;

public class staffChat implements SimpleCommand {

    private static ProxyServer ps;

    public staffChat(ProxyServer ps) {
        staffChat.ps = ps;
    }
    @Override
    public void execute(Invocation invocation) {
        CommandSource cs = invocation.source();
        String[] args = invocation.arguments();
        if (!(cs instanceof Player p)) {
            return;
        }
        for (Player op : ps.getAllPlayers()) {
            if (op.hasPermission("circlecore.staffchat")) {
                UUID senderUUID = p.getUniqueId();
                String prefix = data.getPrefix(senderUUID);

                Component c1 = Component.text("Staff Chat ").color(NamedTextColor.AQUA);
                Component c2 = Component.text(prefix + p.getUsername());
                Component c3 = Component.text(" » " + String.join(" ", args)).color(NamedTextColor.WHITE);

                Component message = c1
                        .append(c2)
                        .append(c3);

                op.sendMessage(message);
            }
        }

    }
}
