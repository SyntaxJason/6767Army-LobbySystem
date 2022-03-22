package de.syntaxtnt.minecraft.lobbysystem.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.syntaxtnt.minecraft.lobbysystem.commands.LobbyCommand;
import de.syntaxtnt.minecraft.lobbysystem.config.Config;
import de.syntaxtnt.minecraft.lobbysystem.util.Cooldown;
import de.syntaxtnt.minecraft.lobbysystem.util.InventoryPlaceholder;
import static de.syntaxtnt.minecraft.lobbysystem.LobbySystem.*;
import static de.syntaxtnt.minecraft.lobbysystem.util.InventoryPlaceholder.*;

import java.util.ArrayList;
import java.util.UUID;

public class InteractListener implements Listener {

	public ArrayList<UUID> speed = new ArrayList<>();
	public static ArrayList<UUID> HIDE_PLAYER = new ArrayList<>(); 
	

	Plugin plugin;

	public InteractListener(Plugin plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (event.getItem() == null) {
			return;
		}
		if (event.getItem().equals(InventoryPlaceholder.SPEED)) {

			if (Cooldown.checkCooldown(player) == true) {
				return;
			}

			if (!speed.contains(player.getUniqueId())) {
				speed.add(player.getUniqueId());
				PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 999999999, Config.getSpeed());
				player.addPotionEffect(effect);
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
				event.setCancelled(true);
				player.sendMessage(PREFIX + "Du bist jetzt schneller");
				Cooldown.setCooldown(player, 5);
				return;
			}

			if (speed.contains(player.getUniqueId())) {
				speed.remove(player.getUniqueId());
				player.removePotionEffect(PotionEffectType.SPEED);
				player.sendMessage(PREFIX + "Dir wurde der Speed effekt entfernt!");
				Cooldown.setCooldown(player, 5);
				return;
			}
		}
		
		if(event.getItem().equals(InventoryPlaceholder.NAVIGATOR)) {
			player.openInventory(NAVIGATOR_INV);
			player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 5, 1);
			for(int i = 0; i < 27; i++) {
				NAVIGATOR_INV.setItem(i, PLACEHOLDER);
			}
			NAVIGATOR_INV.setItem(4, SPAWN);
			NAVIGATOR_INV.setItem(9, SCREEN);
			NAVIGATOR_INV.setItem(17, COMMUNITY);
			NAVIGATOR_INV.setItem(20, PVP1);
			NAVIGATOR_INV.setItem(22, CITYBUILD);
			NAVIGATOR_INV.setItem(24, PVP2);
		}
		
		if(event.getItem().equals(INFOS)) {
			player.openBook(getBook());
			player.playSound(player.getLocation(), Sound.BLOCK_BARREL_OPEN, 5, 1);
		}
		
		if(event.getItem().equals(InventoryPlaceholder.HIDE_PLAYER)) {
			if(Cooldown.checkCooldown(player) == true) {
				player.sendMessage(PREFIX + "Du musst noch " + Cooldown.getCooldown(player) + " Sekunden warten"); 
				return;
			}
			Cooldown.setCooldown(player, 5);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.sendMessage(PREFIX + "Dir wird nun jeder Spieler ausgeblendet");
			for(Player online : Bukkit.getOnlinePlayers()) {
				player.hidePlayer(online);
				HIDE_PLAYER.add(player.getUniqueId());
				player.getInventory().setItem(5, SHOW_PLAYER);
			}
		}
		
		if(event.getItem().equals(SHOW_PLAYER)) {
			if(Cooldown.checkCooldown(player) == true) {
				player.sendMessage(PREFIX + "Du musst noch " + Cooldown.getCooldown(player) + " Sekunden warten"); 
				return;
			}
			Cooldown.setCooldown(player, 5);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
			player.sendMessage(PREFIX + "Dir wird nun jeder Spieler angezeigt");
			for(Player online : Bukkit.getOnlinePlayers()) {
				player.showPlayer(online);
			}
			HIDE_PLAYER.remove(player.getUniqueId());
			player.getInventory().setItem(5, InventoryPlaceholder.HIDE_PLAYER);
		}
		
		if(LobbyCommand.build.contains(player.getUniqueId())) {
			event.setCancelled(false);
			return;
		}

		event.setCancelled(true);
	}

}
