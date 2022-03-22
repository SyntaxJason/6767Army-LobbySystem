package de.syntaxtnt.minecraft.lobbysystem.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.syntaxtnt.minecraft.lobbysystem.config.Config;
import de.syntaxtnt.minecraft.lobbysystem.scoreboard.Scoreboard;
import de.syntaxtnt.minecraft.lobbysystem.util.BukkitColor;

import static de.syntaxtnt.minecraft.lobbysystem.util.InventoryPlaceholder.*;
import static de.syntaxtnt.minecraft.lobbysystem.LobbySystem.*;

public class PlayerJoinLeaveListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		player.getInventory().setItem(6, HIDE_PLAYER);
		player.getInventory().setItem(2, NAVIGATOR);
		player.getInventory().setItem(3, INFOS);
		player.getInventory().setItem(5, SPEED);


		for (int i = 0; i < 100; i++) {
			StringBuilder builder = new StringBuilder();
			if (i != 100) {
				builder.append(BukkitColor.apply("&b&l"));
				player.sendMessage(builder.toString());
			}
		}
		
		player.sendTitle(BukkitColor.apply("&9Willkommen &2" + player.getName()),
				BukkitColor.apply("&9auf &9&l6767Army"));

		event.setJoinMessage(PREFIX + BukkitColor.apply("Willkommen &2" + player.getName() + " &9auf 6767Army"));

		if (player.hasPermission("lobby.admin")) {
			if (Config.getLocation("spawn") == null) {
				player.sendMessage(PREFIX
						+ "Es müssen noch alle Spawns gesetzt werden! mache /Lobby set <Spawn, CityBuild, PvP1, PvP2, Screen-Boxen, Community>");
			}
		}

		new Scoreboard(player);

	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
	}

}
