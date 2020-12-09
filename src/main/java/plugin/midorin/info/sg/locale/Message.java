package plugin.midorin.info.sg.locale;

import plugin.midorin.info.sg.sender.Sender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;

import java.util.Locale;

import static net.kyori.adventure.text.Component.*;
import static net.kyori.adventure.text.format.NamedTextColor.*;
import static net.kyori.adventure.text.format.TextDecoration.BOLD;

public interface Message {

    TextComponent OPEN_BRACKET = Component.text('(');
    TextComponent CLOSE_BRACKET = Component.text(')');
    TextComponent FULL_STOP = Component.text('.');

    Component PREFIX_COMPONENT = text()
            .color(GRAY)
            .append(text('['))
            .append(text()
                    .decoration(BOLD, true)
                    .append(text("ServerGate", GREEN))
            )
            .append(text(']'))
            .build();

    static TextComponent prefixed(ComponentLike component) {

        return text()
                .append(PREFIX_COMPONENT)
                .append(space())
                .append(component)
                .build();
    }



    Args0 REGISTER_CHEST = () -> prefixed(translatable()
            .key("ServerGate.register.chest")
            .color(YELLOW)
            .append(FULL_STOP)
    );

    Args0 ALREADY_PROTECTED = () -> prefixed(translatable()
            .key("ServerGate.already.protected")
            .color(RED)
            .append(FULL_STOP)
    );

    Args0 UNREGISTER_PROTECT = () -> prefixed(translatable()
            .key("ServerGate.unregister")
            .color(RED)
            .append(FULL_STOP)
    );

    Args0 CANNOT_DESTROYED = () -> prefixed(translatable()
            .key("ServerGate.cannot.destroyed")
            .color(RED)
            .append(FULL_STOP)
    );

    Args0 NOT_OWNER_CHEST = () -> prefixed(translatable()
            .key("ServerGate.not.owner.chest")
            .color(RED)
            .append(FULL_STOP)
    );

    Args1<String> VIEW_AVAILABLE_COMMANDS_PROMPT = label -> prefixed(translatable()
            // "&3Use &a/{} help &3to view available commands."
            .key("ServerGate.commandsystem.available-commands")
            .color(DARK_AQUA)
            .args(text('/' + label + " help", GREEN))
            .append(FULL_STOP)
    );

    Args0 NO_PERMISSION_FOR_SUBCOMMANDS = () -> prefixed(translatable()
            // "&3You do not have permission to use any sub commands."
            .key("ServerGate.commandsystem.no-permission-subcommands")
            .color(DARK_AQUA)
            .append(FULL_STOP)
    );

    Args2<String, String> MAIN_COMMAND_USAGE_HEADER = (name, usage) -> prefixed(text()
            // "&b{} Sub Commands: &7({} ...)"
            .color(AQUA)
            .append(text(name))
            .append(space())
            .append(translatable("ServerGate.commandsystem.usage.sub-commands-header"))
            .append(text(": "))
            .append(text()
                    .color(GRAY)
                    .append(OPEN_BRACKET)
                    .append(text(usage))
                    .append(text(" ..."))
                    .append(CLOSE_BRACKET)
            ));


    Args0 COMMAND_NO_PERMISSION = () -> prefixed(translatable()
            // "&cYou do not have permission to use this command!"
            .key("ServerGate.commandsystem.no-permission")
            .color(RED)
    );

    interface Args0 {
        Component build();

        default void send(Player player) {
            Sender.stream(player).sendMessage(build());
        }
    }

    interface Args1<A0> {
        Component build(A0 arg0);

        default void send(Player player, A0 arg0) {
            Sender.stream(player).sendMessage(build(arg0));
        }
    }

    interface Args2<A0, A1> {
        Component build(A0 arg0, A1 arg1);

        default void send(Player player, A0 arg0, A1 arg1) {
            Sender.stream(player).sendMessage(build(arg0, arg1));
        }
    }

    interface Args3<A0, A1, A2> {
        Component build(A0 arg0, A1 arg1, A2 arg2);

        default void send(Player player, A0 arg0, A1 arg1, A2 arg2) {
            Sender.stream(player).sendMessage(build(arg0, arg1, arg2));
        }
    }

    interface Args4<A0, A1, A2, A3> {
        Component build(A0 arg0, A1 arg1, A2 arg2, A3 arg3);

        default void send(Player player, A0 arg0, A1 arg1, A2 arg2, A3 arg3) {
            Sender.stream(player).sendMessage(build(arg0,arg1,arg2,arg3 ));
        }
    }

    interface Args5<A0, A1, A2, A3, A4> {
        Component build(A0 arg0, A1 arg1, A2 arg2, A3 arg3, A4 arg4);

        default void send(Player player, A0 arg0, A1 arg1, A2 arg2, A3 arg3, A4 arg4) {
            Sender.stream(player).sendMessage(build(arg0, arg1, arg2, arg3, arg4));
        }
    }

    interface Args6<A0, A1, A2, A3, A4, A5> {
        Component build(A0 arg0, A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5);

        default void send(Player player, A0 arg0, A1 arg1, A2 arg2, A3 arg3, A4 arg4, A5 arg5) {
            Sender.stream(player).sendMessage(build(arg0, arg1, arg2, arg3, arg4, arg5));
        }
    }
}

