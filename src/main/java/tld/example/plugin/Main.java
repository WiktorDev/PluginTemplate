package tld.example.plugin;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.valid.ValidationInfo;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import org.bukkit.plugin.java.JavaPlugin;
import tld.example.plugin.commands.ExampleCommand;
import tld.example.plugin.commands.arguments.StringArgument;
import tld.example.plugin.commands.arguments.StringsArgument;
import tld.example.plugin.configuration.LocaleConfiguration;
import tld.example.plugin.configuration.PluginConfiguration;
import tld.example.plugin.configuration.helper.SimpleConfigFactory;
import tld.example.plugin.utils.MessageUtil;

public final class Main extends JavaPlugin {
    private Injector injector;
    private PluginConfiguration pluginConfiguration;
    private LocaleConfiguration localeConfiguration;
    private LiteCommands liteCommands;

    @Override
    public void onEnable() {
        this.injector = OkaeriInjector.create().registerInjectable(this);
        this.loadConfiguration();
        this.injector.registerInjectable(pluginConfiguration);
        this.injector.registerInjectable(localeConfiguration);
        this.injector.registerInjectable(injector);
        this.loadCommands();
    }

    @Override
    public void onDisable() {
        if (this.liteCommands != null) {
            this.liteCommands.getPlatformManager().unregisterCommands();
        }
    }

    private void loadCommands() {
        this.liteCommands = LiteBukkitFactory.builder(this.getServer(), this.getDescription().getName())
                .argument(String.class, this.injector.createInstance(StringArgument.class))
                .argument(String[].class, this.injector.createInstance(StringsArgument.class))
                .commandInstance(this.injector.createInstance(ExampleCommand.class))
                .message(ValidationInfo.NO_PERMISSION, messageInfoContext -> MessageUtil.implementColors("&8&l>> &cNie masz uprawnien!"))
                .register();
    }

    private void loadConfiguration() {
        SimpleConfigFactory configurationFactory = new SimpleConfigFactory(this.getDataFolder());
        pluginConfiguration = configurationFactory.produce(PluginConfiguration.class, "configuration.yml");
        localeConfiguration = configurationFactory.produce(LocaleConfiguration.class, "messages.yml");
    }
}