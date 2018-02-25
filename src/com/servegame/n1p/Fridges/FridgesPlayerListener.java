package com.servegame.n1p.Fridges;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class FridgesPlayerListener implements Listener {

    public Fridges plugin;

    public FridgesPlayerListener(Fridges instance) {
        plugin = instance;
    }

    public final int woodDurability = 59;
    public final int diamondDurability = 1561;
    public final int stoneDurability = 131;
    public final int goldDurability = 32;
    public final int ironDurability = 250;

    public final Material[] woodMaterial = {Material.WOOD_AXE, Material.WOOD_PICKAXE, Material.WOOD_SPADE, Material.WOOD_HOE, Material.WOOD_SWORD, Material.FISHING_ROD, Material.CARROT_STICK, Material.BOW};
    public final Material[] diamondMaterial = {Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SPADE, Material.DIAMOND_HOE, Material.DIAMOND_SWORD};
    public final Material[] stoneMaterial = {Material.STONE_AXE, Material.STONE_PICKAXE, Material.STONE_SPADE, Material.STONE_HOE, Material.STONE_SWORD};
    public final Material[] goldMaterial = {Material.GOLD_AXE, Material.GOLD_PICKAXE, Material.GOLD_SPADE, Material.GOLD_HOE, Material.GOLD_SWORD};
    public final Material[] ironMaterial = {Material.IRON_AXE, Material.IRON_PICKAXE, Material.IRON_SPADE, Material.IRON_HOE, Material.IRON_SWORD, Material.SHEARS, Material.FLINT_AND_STEEL};

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (plugin.enabled) {
                if (event.getClickedBlock() != null) {
                    Block block = event.getClickedBlock();
                    if (block.getType() == Material.IRON_BLOCK) {
                        Player entityPlayer = event.getPlayer();

                        Location loc = block.getLocation();
                        loc.setY(loc.getY() - 1);
                        Block y1 = loc.getWorld().getBlockAt(loc);

                        Location loc2 = block.getLocation();
                        loc2.setY(loc2.getY() - 2);
                        Block y2 = loc2.getWorld().getBlockAt(loc2);

                        if (y1.getType() == Material.CHEST) {
                            if (y1.getState() instanceof Chest) {
                                if (entityPlayer.hasPermission("fridges.fridge.use")
                                        && this.plugin.getConfig().getBoolean("fridges.enabled")) {
                                    openChest(y1, entityPlayer);
                                    event.setCancelled(true);
                                }
                            }
                        } else if (y2.getType() == Material.CHEST) {
                            if (y2.getState() instanceof Chest) {
                                if (entityPlayer.hasPermission("fridges.fridge.use")
                                        && this.plugin.getConfig().getBoolean("fridges.enabled")) {
                                    openChest(y2, entityPlayer);
                                    event.setCancelled(true);
                                }
                            }
                        } else if (y1.getType() == Material.STONE_PLATE
                                && entityPlayer.hasPermission("fridges.anvil.repair")) {

                            Material item = event.getItem().getType();

                            double price_per_durability = 0;
                            String material = null;

                            if (Arrays.asList(woodMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.wood") / woodDurability;
                                material = "wood";
                            } else if (Arrays.asList(diamondMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.diamond") / diamondDurability;
                                material = "diamond";
                            } else if (Arrays.asList(stoneMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.stone") / stoneDurability;
                                material = "stone";
                            } else if (Arrays.asList(goldMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.gold") / goldDurability;
                                material = "gold";
                            } else if (Arrays.asList(ironMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.iron") / ironDurability;
                                material = "iron";
                            }

                            if (price_per_durability != 0) {
                                short durability = event.getItem().getDurability();

                                if (durability == 0) {
                                    entityPlayer.sendMessage("Tool at full durability.");
                                } else {
                                    double price = price_per_durability * durability;
                                    if (Fridges.economy.getBalance(entityPlayer) < price) {
                                        double money = Fridges.economy.getBalance(entityPlayer);

                                        if (money != 0) {
                                            durability -= (short) (money / price_per_durability);
                                            price = price_per_durability * (money / price_per_durability);
                                            event.getItem().setDurability(durability);
                                            Fridges.economy.withdrawPlayer(entityPlayer, price);
                                            entityPlayer.sendMessage(
                                                    "Repaired your " + material + " tool for " + price + " Crates!");
                                        } else {
                                            entityPlayer.sendMessage(
                                                    "You have no money to repair your " + material + " tool");
                                        }
                                    } else {
                                        event.getItem().setDurability((short) 0);
                                        Fridges.economy.withdrawPlayer(entityPlayer, price);
                                        entityPlayer.sendMessage(
                                                "Repaired your " + material + " tool for " + price + " Crates!");
                                    }
                                }
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }

        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (plugin.enabled) {
                if (event.getClickedBlock() instanceof Block) {
                    Block block = event.getClickedBlock();

                    if (block.getType() == Material.IRON_BLOCK) {

                        Player entityPlayer = event.getPlayer();

                        Location loc = block.getLocation();
                        loc.setY(loc.getY() - 1);
                        Block y1 = loc.getWorld().getBlockAt(loc);

                        Material item = event.getItem().getType();

                        if (y1.getType() == Material.STONE_PLATE && entityPlayer.hasPermission("fridges.anvil.info")) {
                            double price_per_durability = 0;
                            String material = null;

                            if (Arrays.asList(woodMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.wood") / woodDurability;
                                material = "wood";
                            } else if (Arrays.asList(diamondMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.diamond") / diamondDurability;
                                material = "diamond";
                            } else if (Arrays.asList(stoneMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.stone") / stoneDurability;
                                material = "stone";
                            } else if (Arrays.asList(goldMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.gold") / goldDurability;
                                material = "gold";
                            } else if (Arrays.asList(ironMaterial).contains(item)) {
                                price_per_durability = this.plugin.getConfig().getDouble("anvils.price.iron") / ironDurability;
                                material = "iron";
                            }

                            if (price_per_durability != 0) {
                                short durability = event.getItem().getDurability();

                                if (durability == 0) {
                                    entityPlayer.sendMessage("Tool at full durability.");
                                } else {
                                    double price = price_per_durability * durability;
                                    entityPlayer.sendMessage(
                                            "It will cost " + price + " Crates to repair your " + material + " tool!");
                                }
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event) {
        if (plugin.enabled) {
            if (event.getBlock() instanceof Block) {
                Block block = event.getBlock();

                if (block.getType() == Material.IRON_BLOCK) {

                    Player entityPlayer = event.getPlayer();

                    Location loc = block.getLocation();
                    loc.setY(loc.getY() - 1);
                    Block y1 = loc.getWorld().getBlockAt(loc);

                    if (y1.getType() == Material.STONE_PLATE && !entityPlayer.hasPermission("fridges.anvil.break")) {
                        event.setCancelled(true);
                    } else if (y1.getType() == Material.CHEST) {

                        Location loc2 = block.getLocation();
                        loc2.setY(loc2.getY() + 1);
                        Block y2 = loc2.getWorld().getBlockAt(loc2);

                        if (y2.getType() == Material.IRON_BLOCK && !entityPlayer.hasPermission("fridges.fridge.break")) {
                            event.setCancelled(true);
                        }

                    } else if (y1.getType() == Material.IRON_BLOCK) {

                    }
                } else if (block.getType() == Material.STONE_PLATE) {
                    Player entityPlayer = event.getPlayer();

                    Location loc = block.getLocation();
                    loc.setY(loc.getY() + 1);
                    Block y1 = loc.getWorld().getBlockAt(loc);

                    if (y1.getType() == Material.IRON_BLOCK && !entityPlayer.hasPermission("fridges.anvil.break")) {
                        event.setCancelled(true);
                    }
                } else if (block.getType() == Material.CHEST) {
                    Player entityPlayer = event.getPlayer();

                    Location loc = block.getLocation();
                    loc.setY(loc.getY() + 1);
                    Block y1 = loc.getWorld().getBlockAt(loc);

                    Location loc2 = block.getLocation();
                    loc2.setY(loc2.getY() + 2);
                    Block y2 = loc2.getWorld().getBlockAt(loc2);

                    if (y1.getType() == Material.IRON_BLOCK && y2.getType() == Material.IRON_BLOCK && !entityPlayer.hasPermission("fridges.fridge.break")) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    public void openChest(Block block, Player entityPlayer) {
        Chest chest = (Chest) block.getState();
        Inventory inventory = chest.getInventory();
        entityPlayer.openInventory(inventory);

        if (this.plugin.getConfig().getBoolean("fridges.debug")) {
            this.plugin.log.severe("A fridge has been open");
        }
    }
}