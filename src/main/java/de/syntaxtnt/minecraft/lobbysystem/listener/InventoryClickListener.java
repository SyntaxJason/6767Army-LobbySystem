package de.syntaxtnt.minecraft.lobbysystem.listener;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.syntaxtnt.minecraft.lobbysystem.commands.LobbyCommand;
import de.syntaxtnt.minecraft.lobbysystem.config.Config;

import static de.syntaxtnt.minecraft.lobbysystem.util.InventoryPlaceholder.*;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		Location spawn = Config.getLocation("spawn");
		Location citybuild = Config.getLocation("citybuild");
		Location screen = Config.getLocation("screen");
		Location community = Config.getLocation("community");
		Location pvp1 = Config.getLocation("pvp1");
		Location pvp2 = Config.getLocation("pvp2");
		
		if(event.getCurrentItem().equals(SPAWN)) {
			player.teleport(spawn);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}
		
		if(event.getCurrentItem().equals(CITYBUILD)) {
			player.teleport(citybuild);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}
		
		if(event.getCurrentItem().equals(SCREEN)) {
			player.teleport(screen);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}
		
		if(event.getCurrentItem().equals(COMMUNITY)) {
			player.teleport(community);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}
		
		if(event.getCurrentItem().equals(PVP1)) {
			player.teleport(pvp1);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}
		
		if(event.getCurrentItem().equals(PVP2)) {
			player.teleport(pvp2);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}
		
		if(LobbyCommand.build.contains(player.getUniqueId())) {
			event.setCancelled(false);
			return;
		}
		
		event.setCancelled(true);
	}

}
