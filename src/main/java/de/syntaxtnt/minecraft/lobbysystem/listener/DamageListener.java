package de.syntaxtnt.minecraft.lobbysystem.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityDamagebyEntity(EntityDamageByEntityEvent event) {
		event.setCancelled(true);
	}

}
