package de.drolpi.allesodernichts.commands;

import de.drolpi.allesodernichts.AllesOderNichts;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public final class AON_CMD implements CommandExecutor {

    private final AllesOderNichts plugin;
    private BukkitTask task = null;

    public AON_CMD(final AllesOderNichts plugin) {

        /* INIT */
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("allesodernichts").setExecutor(this);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        if(!(sender instanceof Player)) return false;
        final Player player = ((Player) sender);

        /* CHECK PERMISSIONS */

        if(!player.hasPermission("system.aon")) {
            player.sendMessage("§cDazu hast du keine Rechte§8!");
            return false;
        }

        /* CHECK IF A TASK ALREADY RUNNING */

        if(task != null) {
            player.sendMessage("§cEs wird bereits eine Zahl ausgerechnet§8!");
            return false;
        }

        /* START TASK */

        final AtomicInteger count = new AtomicInteger(10);
        task = new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("§cBerechne Zahlen...");

                count.set(count.get() - 1);

                if(count.get() == 0) {
                    ready(player);
                    task.cancel();
                    task = null;
                }
            }
        }.runTaskTimer(AllesOderNichts.getInstance(), 0, 20);

        return true;
    }

    private void ready(final Player player) {

        /* FINISH */
        player.sendMessage("§aErgebnis wurde gefunden!");
        player.sendMessage("");
        player.sendMessage("§7Echt-Geld: §e" + ThreadLocalRandom.current().nextInt(100) + "€ §7Ingame-Geld: §e" + ThreadLocalRandom.current().nextInt(1000000) + " Münzen");
    }
}
