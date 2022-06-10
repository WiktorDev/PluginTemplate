package tld.example.plugin.commands.arguments;

import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.SingleArgumentHandler;
import dev.rollczi.litecommands.valid.ValidationCommandException;
import eu.okaeri.injector.annotation.Inject;
import tld.example.plugin.configuration.LocaleConfiguration;
import java.util.Arrays;
import java.util.List;

@ArgumentName("tekst")
public class StringArgument implements SingleArgumentHandler<String> {
    @Inject
    private LocaleConfiguration messages;

    @Override
    public String parse(LiteInvocation invocation, String argument) throws ValidationCommandException {
        return argument;
    }

    @Override
    public List<String> tabulation(LiteInvocation invocation, String command, String[] args) {
        return Arrays.asList("tekst");
    }
}