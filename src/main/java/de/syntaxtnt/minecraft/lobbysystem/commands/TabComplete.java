package de.syntaxtnt.minecraft.lobbysystem.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> players = new ArrayList<>();
		List<String> rl = new ArrayList<>();
		if (args.length == 1) {
			if (sender.hasPermission("lobby.reload")) {
				rl.add("reload");
				rl.add("rl");
				rl.add("set");
				return rl;
			}
		}
		List<String> modis = new ArrayList<>();
		switch (args[0].toLowerCase()) {
		case "set": {
			if (sender.hasPermission("lobby.set")) {
				if (args.length == 2) {
					modis.add("spawn");
					modis.add("citybuild");
					modis.add("screen-boxen");
					modis.add("community");
					modis.add("pvp1");
					modis.add("pvp2");
					return modis;
				}
			}

		}
		}
		for(Player player : Bukkit.getOnlinePlayers()) {
			players.add(player.getName());
		}
		return players;
	}

}
