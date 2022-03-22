package de.syntaxtnt.minecraft.lobbysystem.commands;

import static de.syntaxtnt.minecraft.lobbysystem.LobbySystem.PREFIX;
import static de.syntaxtnt.minecraft.lobbysystem.util.InventoryPlaceholder.HIDE_PLAYER;
import static de.syntaxtnt.minecraft.lobbysystem.util.InventoryPlaceholder.INFOS;
import static de.syntaxtnt.minecraft.lobbysystem.util.InventoryPlaceholder.NAVIGATOR;
import static de.syntaxtnt.minecraft.lobbysystem.util.InventoryPlaceholder.SPEED;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.syntaxtnt.minecraft.lobbysystem.util.BukkitColor;

public class BuildCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player player = (Player) sender;

		if (player.hasPermission("lobby.build")) {
			if (!LobbyCommand.build.contains(player.getUniqueId())) {
				LobbyCommand.build.add(player.getUniqueId());
				player.getInventory().clear();
				player.sendMessage(PREFIX + BukkitColor.apply("Du kannst jetzt Bauen!"));
				player.setGameMode(GameMode.CREATIVE);
				return true;
			}
			LobbyCommand.build.remove(player.getUniqueId());
			player.getInventory().setItem(6, SPEED);
			player.getInventory().setItem(2, NAVIGATOR);
			player.getInventory().setItem(3, INFOS);
			player.getInventory().setItem(5, HIDE_PLAYER);
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(PREFIX + BukkitColor.apply("Du bist jetzt nicht mehr Bauen"));
			return true;
		}
		player.sendMessage(PREFIX + BukkitColor.apply("&cDu hast keine Berechtigungen!"));

		return false;
	}

}
