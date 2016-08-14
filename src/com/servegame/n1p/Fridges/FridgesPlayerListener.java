package com.servegame.n1p.Fridges;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class FridgesPlayerListener implements Listener {

	public static Fridges plugin;

	public FridgesPlayerListener(Fridges instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (plugin.enabled) {

				if (event.getClickedBlock() instanceof Block) {

					Block block = event.getClickedBlock();

					if (block.getType() == Material.IRON_BLOCK) {

						Player entityPlayer = event.getPlayer();

						Location loc = block.getLocation();
						loc.setX(loc.getX() + 1);
						Block x1 = loc.getWorld().getBlockAt(loc);

						Location loc2 = block.getLocation();
						loc2.setX(loc2.getX() - 1);
						Block x2 = loc2.getWorld().getBlockAt(loc2);

						Location loc3 = block.getLocation();
						loc3.setZ(loc3.getZ() + 1);
						Block z1 = loc3.getWorld().getBlockAt(loc3);

						Location loc4 = block.getLocation();
						loc4.setZ(loc4.getZ() - 1);
						Block z2 = loc4.getWorld().getBlockAt(loc4);

						if (x1.getType() == Material.CHEST) {

							if (x1.getState() instanceof Chest) {

								openChest(x1, entityPlayer);

							}

						} else if (x2.getType() == Material.CHEST) {

							if (x2.getState() instanceof Chest) {

								openChest(x2, entityPlayer);

							}

						} else if (z1.getType() == Material.CHEST) {

							if (z1.getState() instanceof Chest) {

								openChest(z1, entityPlayer);

							}

						} else if (z2.getType() == Material.CHEST) {

							if (z2.getState() instanceof Chest) {

								openChest(z2, entityPlayer);

							}

						}
					}
				}
			}
		}
	}

	public void openChest(Block block, Player entityPlayer) {

		Chest chest = (Chest) block.getState();
		Inventory inventory = chest.getInventory();
		entityPlayer.openInventory(inventory);

	}
}