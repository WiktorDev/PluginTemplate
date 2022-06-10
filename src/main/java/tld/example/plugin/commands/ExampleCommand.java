package tld.example.plugin.commands;

import dev.rollczi.litecommands.annotations.*;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import tld.example.plugin.configuration.LocaleConfiguration;
import tld.example.plugin.utils.MessageUtil;

@Section(route = "example", aliases = {"ex"})
@Permission("ytools.command.alert")
public class ExampleCommand {
    @Inject
    private LocaleConfiguration messages;

    @Execute(required = 0)
    public void helper(CommandSender sender) {
        MessageUtil.sendMessage(sender, messages.getUsage());
    }

    @Execute(route = "chat")
    @MinArgs(1)
    public void chat(CommandSender sender, @Joiner String string){
        MessageUtil.sendOnline(messages.getPrefix() + "&r " +  string);
    }

    @Execute(route = "title")
    @MinArgs(1)
    public void title(CommandSender sender, @Joiner String string){
        Bukkit.getOnlinePlayers().forEach(online -> {
            MessageUtil.sendTitle(online, 15, 80, 15, messages.getPrefix(), string);
        });
    }

    @Execute(route = "actionbar")
    @MinArgs(1)
    @UsageMessage("&8&l» &cPoprawne użycie: /alert actionbar [wiadomość]")
    public void actionbar(CommandSender sender, @Joiner String string){
        Bukkit.getOnlinePlayers().forEach(online -> {
            MessageUtil.sendActionbar(online, messages.getPrefix() + "&r " + string);
        });
    }
}