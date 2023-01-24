package me.r4wpanda.chatmanager.config;

import me.r4wpanda.chatmanager.ChatManagerPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;

public enum MessageEnum {


    PLUGIN_RELOADED("plugin-reloaded","&d[Boxes] &fPlugin has been &areloaded&f!"),
    NO_PERMISSION("no-permission","&cYou don't have permissions to do that!"),
    UNKNOWN_PLAYER("unknown-player","&cThere's no player with this name!"),
    UNKNOWN_NUMBER("unknown-number","&cPlease, type a valid number!"),
    ALREADY_OPENING("already-opening","&cYou are already opening an unscrambling reward box!"),
    OPENING_MAX("opening-max","&cYou are already opening 5 reward boxes at the same time!"),
    ONLY_PLAYERS("only-players","&cThis command is only for players!"),

    HELP("help", Arrays.asList(
            "&f ",
            " &d&lREWARD BOXES",
            " &d| &7/rboxes give <type> [amount] [player]",
            " &d| &7/rboxes reload",
            " &d| &7/rboxes list",
            "&f "
    )),

    BOX_GIVE_USAGE("box-give-usage","&d[Boxes] &fCommand usage: &7/rboxes give <type> [amount] [player]"),
    BOX_GIVEN_SELF("box-given-self","&d[Boxes] &fYou have given &e%box% &fto yourself!"),
    BOX_GIVEN_SENDER("box-given-sender","&d[Boxes] &fYou have given &e%box% &fto &6%player_name%&f!"),
    BOX_GIVEN_RECEIVER("box-given-receiver","&d[Boxes] &fYou have been given &e%box%&f!"),
    UNKNOWN_BOX("unknown-box","&cThere's no Reward Box with this name!"),

    BOX_LIST_NOTHING_FOUND("box-list-nothing-found","&7No Reward Box was found!"),
    BOX_LIST_HEADER("box-list-header","&dBox Types"),
    BOX_LIST_VALUE("box-list-value","&7- &f%identifier%"),
    BOX_LIST_FOOTER("box-list-footer", "&f ");

    private final String path;
    private final Object defVal;
    private final ChatManagerPlugin plugin = ChatManagerPlugin.plugin;

    MessageEnum(String path, Object defVal) {
        this.path = path;
        this.defVal = defVal;
    }

    public Message get() {
        if (getMsgFile().contains(path)) {
            return new Message(getMsgFile().get(path));
        } else {
            return new Message(defVal);
        }
    }

    private YamlConfiguration getMsgFile() {
        return plugin.getConfigHandler().getConfig("message");
    }

    public void send(CommandSender sender) {
        if (getMsgFile().contains(path)) {
            new Message(getMsgFile().get(path)).send(sender);
        } else {
            new Message(defVal).send(sender);
        }
    }
}
