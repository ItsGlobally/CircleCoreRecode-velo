package me.itsglobally.circlecorerecodevelo.commands;


import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Optional;
import java.util.StringJoiner;

public class Msg implements SimpleCommand {

    private static ProxyServer ps;

    public Msg(ProxyServer ps) {
        Msg.ps = ps;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource cs = invocation.source();
        String[] args = invocation.arguments();
        if (!(cs instanceof Player p)) {
            return;
        }
        Optional<Player> optionalPlayer = ps.getPlayer(args[0]);
        if (optionalPlayer.isPresent()) {
            Player tg = optionalPlayer.get();
            StringJoiner joiner = new StringJoiner(" ");
            for (int i = 1; i < args.length; i++) {
                joiner.add(args[i]);
            }
            Component msg1 = Component
                    .text("[").color(NamedTextColor.LIGHT_PURPLE);
            Component msg2 = Component
                    .text(data.getPrefix(p.getUniqueId()) + p.getUsername());
            Component msg3 = Component
                    .text(" -> ").color(NamedTextColor.LIGHT_PURPLE);
            Component msg4 = Component
                    .text(data.getPrefix(tg.getUniqueId()) + tg.getUsername());
            Component msg5 = Component
                    .text("] ").color(NamedTextColor.LIGHT_PURPLE);
            Component msg6 = Component
                    .text(joiner.toString()).color(NamedTextColor.WHITE);
            p.sendMessage(msg1.append(msg2).append(msg3).append(msg4).append(msg5).append(msg6));
            tg.sendMessage(msg1.append(msg2).append(msg3).append(msg4).append(msg5).append(msg6));
        } else {
            p.sendMessage(Component.text("The player is not online!").color(NamedTextColor.RED));
        }

    }
}
