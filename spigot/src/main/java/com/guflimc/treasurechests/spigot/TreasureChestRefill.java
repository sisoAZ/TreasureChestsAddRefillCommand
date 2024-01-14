package com.guflimc.treasurechests.spigot;

import com.guflimc.treasurechests.spigot.data.beans.BTreasureChest;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

public class TreasureChestRefill implements CommandExecutor {

    private final TreasureChestManager manager;

    public TreasureChestRefill(TreasureChestManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("treasurechestrefill")) {
            Bukkit.getLogger().info("Refilling treasure chests.");
            for (BTreasureChest treasureChest : this.manager.chests()) {
                Block block = treasureChest.location().getBlock();
                Inventory inv = this.manager.inventoryFor(block);
                if (inv == null) {
                    continue;
                }
                Chest chest = (Chest) block.getState();
                //override chest contents
                chest.getInventory().setContents(inv.getContents());
            }

            return true;
        }
            return false;
    }
}