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

		Location spawn = null;
		Location citybuild = null;
		Location screen = null;
		Location community = null;
		Location pvp1 = null;
		Location pvp2 = null;

		if (Config.getLocation("spawn") != null) {
			spawn = Config.getLocation("spawn");
		}
		if (Config.getLocation("citybuild") != null) {
			citybuild = Config.getLocation("citybuild");
		}
		if (Config.getLocation("screen") != null) {
			screen = Config.getLocation("screen");
		}
		if (Config.getLocation("community") != null) {
			community = Config.getLocation("community");
		}
		if (Config.getLocation("pvp1") != null) {
			pvp1 = Config.getLocation("pvp1");
		}
		if (Config.getLocation("pvp2") != null) {
			pvp2 = Config.getLocation("pvp2");
		}

		if (event.getCurrentItem().equals(SPAWN)) {
			if (spawn == null) {
				event.setCancelled(true);
				return;
			}
			player.teleport(spawn);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}

		if (event.getCurrentItem().equals(CITYBUILD)) {
			if (citybuild == null) {
				event.setCancelled(true);
				return;
			}
			player.teleport(citybuild);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}

		if (event.getCurrentItem().equals(SCREEN)) {
			if (screen == null) {
				event.setCancelled(true);
				return;
			}
			player.teleport(screen);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}

		if (event.getCurrentItem().equals(COMMUNITY)) {
			if (community == null) {
				event.setCancelled(true);
				return;
			}
			player.teleport(community);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}

		if (event.getCurrentItem().equals(PVP1)) {
			if (pvp1 == null) {
				event.setCancelled(true);
				return;
			}
			player.teleport(pvp1);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}

		if (event.getCurrentItem().equals(PVP2)) {
			if (pvp2 == null) {
				event.setCancelled(true);
				return;
			}
			player.teleport(pvp2);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.closeInventory();
			event.setCancelled(true);
			return;
		}

		if (LobbyCommand.build.contains(player.getUniqueId())) {
			event.setCancelled(false);
			return;
		}

		event.setCancelled(true);
	}

}
