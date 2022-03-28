package de.syntaxtnt.minecraft.lobbysystem.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import de.syntaxtnt.minecraft.lobbysystem.config.Config;

public class DoubleJumpListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if(event.getPlayer().getAllowFlight()) return;
		this.groundUpdate(event.getPlayer());
	}
	
	@EventHandler
	public void onToggleFlight(PlayerToggleFlightEvent event) {
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE || event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
		event.setCancelled(true);
		event.getPlayer().setAllowFlight(false);
		event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(Config.getDoubleJump().get(0)).setY(Config.getDoubleJump().get(1)));
	}
	
	public void groundUpdate(Player player) {
		Location location = player.getPlayer ().getLocation ();
		location = location.subtract (0, 1, 0);

		Block block = location.getBlock ();
		if (!block.getType ().isSolid ()) return;

		player.setAllowFlight (true);
	}

}
