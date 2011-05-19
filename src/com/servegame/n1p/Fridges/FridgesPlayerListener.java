package com.servegame.n1p.Fridges;

import net.minecraft.server.EntityPlayer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ContainerBlock;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class FridgesPlayerListener extends PlayerListener {

	public static Fridges plugin;
	public FridgesPlayerListener(Fridges instance) {
	        plugin = instance;       	 	
	}
	
    public void onPlayerInteract (PlayerInteractEvent event){

		if (plugin.enabled){
			
			if (event.getClickedBlock() instanceof Block){
			
				Block block = (Block) event.getClickedBlock();
				
				if (block.getType() == Material.IRON_BLOCK){
					
				    EntityPlayer entityPlayer = ((CraftPlayer)event.getPlayer()).getHandle();
					
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
					
					if (x1.getType() == Material.CHEST){
						
						if(x1.getState() instanceof ContainerBlock)
						{
	
							openChest(x1,entityPlayer);
								
						}
						
					}else if (x2.getType() == Material.CHEST){
	
							if(x2.getState() instanceof ContainerBlock)
							{
	
								openChest(x2,entityPlayer);
							
							}
						
					}else if (z1.getType() == Material.CHEST){
						
							if(z1.getState() instanceof ContainerBlock)
							{
		
								openChest(z1,entityPlayer);
								
							}
						
					}else if (z2.getType() == Material.CHEST){
						
							if(z2.getState() instanceof ContainerBlock)
							{
		
								openChest(z2,entityPlayer);
								
							}
						
					}
				}
			}
		}
	}			
    
    public void openChest(Block block, EntityPlayer entityPlayer){
    	
	    CraftInventory inventory = (CraftInventory)((ContainerBlock)block.getState()).getInventory();
	    entityPlayer.a(inventory.getInventory());
    	
    }
}	