package de.drolpi.allesodernichts;

import de.drolpi.allesodernichts.commands.AON_CMD;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class AllesOderNichts extends JavaPlugin {

    private static AllesOderNichts instance;
    private String prefix;

    @Override
    public void onLoad() {

        /* INIT */
        instance = this;
        this.prefix = "";

        /* LOGGING */
        getServer().getConsoleSender().sendMessage("§eINFO: §fVersuche AllesOderNichts zu aktivieren!");
    }

    @Override
    public void onEnable() {

        /* REGISTER COMMANDS */
        new AON_CMD(this);

        /* LOGGING */
        getServer().getConsoleSender().sendMessage("§aSUCCES: §fAllesOderNichts wurde erfolgreich geladen!");
    }

    @Override
    public void onDisable() {

        /* LOGGING */
        getServer().getConsoleSender().sendMessage("§cINFO: §fAllesOderNichts wurde erfolgreich deaktviert!");
    }

    public static AllesOderNichts getInstance() {
        return instance;
    }
}
