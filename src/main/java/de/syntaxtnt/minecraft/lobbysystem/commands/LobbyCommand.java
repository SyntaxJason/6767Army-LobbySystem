package de.syntaxtnt.minecraft.lobbysystem.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import static de.syntaxtnt.minecraft.lobbysystem.LobbySystem.*;

import de.syntaxtnt.minecraft.lobbysystem.LobbySystem;
import de.syntaxtnt.minecraft.lobbysystem.config.Config;
import de.syntaxtnt.minecraft.lobbysystem.util.BukkitColor;

public class LobbyCommand implements CommandExecutor {

	public static ArrayList<UUID> build = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player player = (Player) sender;
		
		if(args.length == 0) {
			if(player.hasPermission("Lobby.lobby")) {
			player.sendMessage(PREFIX + BukkitColor.apply("» &eLobby Commands"));
			player.sendMessage(BukkitColor.apply("&6/lobby set &8»&7 Setze die Positionen des Teleporters"));
			player.sendMessage(BukkitColor.apply("&6/lobby rl &8»&7 Lade die Config des Plugins neu"));
			player.sendMessage(BukkitColor.apply("&6/lobby reload &8»&7 Lade die Config des Plugins neu"));
			} else {
				player.sendMessage(PREFIX + BukkitColor.apply("&cDu hast keine Berechtigungen!"));
			}
		}

		switch (args[0].toLowerCase()) {
		case "reload": {
			if (player.hasPermission("lobby.reload")) {
				try {
					Config.Config.load(Config.ConfigFile);
				} catch (IOException | InvalidConfigurationException e) {
					e.printStackTrace();
				}
				Config.save();
				player.sendMessage(PREFIX + "Die Config wird reloaded");
				LobbySystem.initializeConfig();
				return true;
			}
			player.sendMessage(PREFIX + BukkitColor.apply("&cDu hast keine Berechtigungen!"));
			break;
		}

		case "rl": {
			if (player.hasPermission("lobby.reload")) {
				try {
					Config.Config.load(Config.ConfigFile);
				} catch (IOException | InvalidConfigurationException e) {
					e.printStackTrace();
				}
				Config.save();
				player.sendMessage(PREFIX + "Die Config wird reloaded");
				LobbySystem.initializeConfig();
				return true;
			}
			player.sendMessage(PREFIX + BukkitColor.apply("&cDu hast keine Berechtigungen!"));
		}

		case "set": {
			if (player.hasPermission("lobby.set")) {
				Location loc = player.getLocation();
				switch (args[1].toLowerCase()) {
				
				case "screen-boxen": {
					Config.setLocation("screen", loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
							loc.getPitch(), loc.getYaw());
					player.sendMessage(PREFIX + BukkitColor.apply("Du hast erfolgreich &2&lScreen-Boxen &9gesetzt"));
					Config.save();
					break;
				}
				
				case "pvp1": {
					Config.setLocation("pvp1", loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
							loc.getPitch(), loc.getYaw());
					player.sendMessage(PREFIX + BukkitColor.apply("Du hast erfolgreich &2&lPvP1 &9gesetzt"));
					Config.save();
					break;
				}
				
				case "pvp2": {
					Config.setLocation("pvp2", loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
							loc.getPitch(), loc.getYaw());
					player.sendMessage(PREFIX + BukkitColor.apply("Du hast erfolgreich &2&lPvP2 &9gesetzt"));
					Config.save();
					break;
				}
				
				case "spawn": {
					Config.setLocation("spawn", loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
							loc.getPitch(), loc.getYaw());
					player.sendMessage(PREFIX + BukkitColor.apply("Du hast erfolgreich &2&lSpawn &9gesetzt"));
					Config.save();
					break;
				}
				
				case "citybuild": {
					Config.setLocation("citybuild", loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
							loc.getPitch(), loc.getYaw());
					player.sendMessage(PREFIX + BukkitColor.apply("Du hast erfolgreich &2&lCityBuild &9gesetzt"));
					Config.save();
					break;
				}
				
				case "community": {
					Config.setLocation("community", loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
							loc.getPitch(), loc.getYaw());
					player.sendMessage(PREFIX + BukkitColor.apply("Du hast erfolgreich &2&lCommunity &9gesetzt"));
					Config.save();
					break;
				}
				}
				break;
			}
			player.sendMessage(PREFIX + BukkitColor.apply("&cDu hast keine Berechtigungen!"));
		}
			break;
		}

		return false;
	}

}
