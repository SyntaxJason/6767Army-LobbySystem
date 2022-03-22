package de.syntaxtnt.minecraft.lobbysystem.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import de.syntaxtnt.minecraft.lobbysystem.commands.LobbyCommand;

public class MainListener implements Listener {
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(LobbyCommand.build.contains(event.getPlayer().getUniqueId())) {
			event.setCancelled(false);
			return;
		}
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(LobbyCommand.build.contains(event.getPlayer().getUniqueId())) {
			event.setCancelled(false);
			return;
		}
		event.setCancelled(true);
	}
}
