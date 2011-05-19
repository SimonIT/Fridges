package com.servegame.n1p.Fridges;

import net.minecraft.server.EntityPlayer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.ContainerBlock;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class FridgesPlayerListener extends PlayerListener {

	public static Fridges plugin;
	public FridgesPlayerListener(Fridges instance) {
	        plugin = instance;       	 	
	}
	
    public void onPlayerInteract (PlayerInteractEvent event){

		if (plugin.enabled){
			
			Block block = event.getClickedBlock();
			
			if (block.getType() == Material.IRON_BLOCK){
				
			    EntityPlayer entityPlayer = ((CraftPlayer)event.getPlayer()).getHandle();
				
				Location loc = block.getLocation();	
				loc.setX(loc.getX() + 1);	
				Block x1 = loc.getWorld().getBlockAt(loc);
				
				Location loc2 = block.getLocation();	
				loc.setX(loc2.getX() - 1);	
				Block x2 = loc2.getWorld().getBlockAt(loc2);
				
				Location loc3 = block.getLocation();	
				loc.setX(loc3.getX() - 1);	
				Block z1 = loc3.getWorld().getBlockAt(loc3);
				
				Location loc4 = block.getLocation();	
				loc.setX(loc4.getX() - 1);	
				Block z2 = loc4.getWorld().getBlockAt(loc4);
				
				if (x1.getType() == Material.CHEST){
					
					Chest chest = (Chest) x1.getState();
					
						if(x1.getState() instanceof ContainerBlock)
						{

							openChest(x1,entityPlayer);
							
						}
					
					chest.getInventory().addItem(new ItemStack(Material.WOOD,64));
					
				}else if (x2.getType() == Material.CHEST){
					
					Chest chest = (Chest) x2.getState();
					
					if(x2.getState() instanceof ContainerBlock)
					{

						openChest(x2,entityPlayer);
						
					}
					
					chest.getInventory().addItem(new ItemStack(Material.WOOD,64));
					
				}else if (z1.getType() == Material.CHEST){
					
					Chest chest = (Chest) z1.getState();
					
					if(z1.getState() instanceof ContainerBlock)
					{

						openChest(z1,entityPlayer);
						
					}
					
					chest.getInventory().addItem(new ItemStack(Material.WOOD,64));
					
				}else if (z2.getType() == Material.CHEST){
					
					Chest chest = (Chest) z2.getState();
					
					if(z2.getState() instanceof ContainerBlock)
					{

						openChest(z2,entityPlayer);
						
					}
					
					chest.getInventory().addItem(new ItemStack(Material.WOOD,64));
					
				}
				
			}
			
		}
	}			
    
    public void openChest(Block block, EntityPlayer entityPlayer){
    	
	    CraftInventory inventory = (CraftInventory)((ContainerBlock)block.getState()).getInventory();
	    entityPlayer.a(inventory.getInventory());
    	
    }
}	