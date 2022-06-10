package tld.example.plugin.configuration;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocaleConfiguration extends OkaeriConfig {
    private String usage = "&8&l» &cPoprawne użycie: &c/alert [chat/title/actionbar]";
    private String prefix = "&c&lOGLOSZENIE";
}
