package de.syntaxtnt.minecraft.lobbysystem.listener;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CommandListener implements Listener {

	ArrayList<String> commands = new ArrayList<>();

	private boolean commandTab = true;

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSendCommands(PlayerCommandSendEvent event) {
		if (!event.getPlayer().hasPermission("lobby.tabcomplete")) {
			if (commands.isEmpty()) {
				commands.addAll(event.getCommands());
			}
			if (!commandTab) {
				return;
			}
			Collection<String> newCommands = event.getCommands();
			event.getCommands().clear();
			event.getCommands().addAll(newCommands);
		}
	}

	@EventHandler
	public void onCommandProcess(PlayerCommandPreprocessEvent event) {

	}

}
