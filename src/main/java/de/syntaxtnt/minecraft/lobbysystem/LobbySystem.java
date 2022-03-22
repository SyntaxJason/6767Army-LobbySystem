package de.syntaxtnt.minecraft.lobbysystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.syntaxtnt.minecraft.lobbysystem.commands.BuildCommand;
import de.syntaxtnt.minecraft.lobbysystem.commands.LobbyCommand;
import de.syntaxtnt.minecraft.lobbysystem.commands.TabComplete;
import de.syntaxtnt.minecraft.lobbysystem.config.Config;
import de.syntaxtnt.minecraft.lobbysystem.listener.CommandListener;
import de.syntaxtnt.minecraft.lobbysystem.listener.DamageListener;
import de.syntaxtnt.minecraft.lobbysystem.listener.InteractListener;
import de.syntaxtnt.minecraft.lobbysystem.listener.InventoryClickListener;
import de.syntaxtnt.minecraft.lobbysystem.listener.MainListener;
import de.syntaxtnt.minecraft.lobbysystem.listener.PlayerJoinLeaveListener;
import de.syntaxtnt.minecraft.lobbysystem.util.BukkitColor;
import de.syntaxtnt.minecraft.lobbysystem.util.Cooldown;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class LobbySystem extends JavaPlugin {

	public static String PREFIX = BukkitColor.apply("&8[&9Lobby&2System&8] &9");

	private static LobbySystem instance;

	@Override
	public void onEnable() {
		registerEvents();
		initializeConfig();
		registerCommands();
		Actionbar();
		Cooldown.setupCooldown();
		Config.save();
		instance = this;

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylightCycle false");
		hidePlayer();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		try {
			Config.Config.load(Config.ConfigFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		Config.save();
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.showPlayer(player);
		}
	}

	public void registerEvents() {
		PluginManager manager = Bukkit.getPluginManager();
		manager.registerEvents(new PlayerJoinLeaveListener(), this);
		manager.registerEvents(new DamageListener(), this);
		manager.registerEvents(new InteractListener(this), this);
		manager.registerEvents(new MainListener(), this);
		manager.registerEvents(new InventoryClickListener(), this);
		manager.registerEvents(new CommandListener(), this);
	}

	public void registerCommands() {
		getCommand("lobby").setExecutor(new LobbyCommand());
		getCommand("lobby").setTabCompleter(new TabComplete());
		getCommand("build").setExecutor(new BuildCommand());
	}

	public static void initializeConfig() {

		FileConfiguration config = Config.get();
		if (!config.contains("Lobby.speed.multiplier")) {
			Config.setSpeed(8);
		}
		if (!config.contains("Lobby.infos")) {
			List<String> string = new ArrayList<String>();
			string.add("-");
			Config.setText(string);
		}
		if (!config.contains("Lobby.actionbar.text")) {
			List<String> string = new ArrayList<String>();
			string.add("test");
			Config.setMessage(string);
		}
		if(!config.contains("Lobby.actionbar.cooldown")) {
			Config.setActionCountdown(3);
		}
		if(!config.contains("Lobby.scoreboard")) {
			ArrayList<String> array = new ArrayList<>();
			for(int i = 0; i < 15; i++) {
				array.add("test");
			}
			Config.setScoreboard(array);
		}
	}

	@SuppressWarnings("deprecation")
	public void hidePlayer() {
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (InteractListener.HIDE_PLAYER.contains(player.getUniqueId())) {
					for (Player online : Bukkit.getOnlinePlayers()) {
						player.hidePlayer(online);
					}
				}
			}
		}, 0, 20);
	}

	public static LobbySystem getInstance() {
		return instance;
	}

	private static int i = 0;

	public void Actionbar() {

		Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {

			ArrayList<String> messages = (ArrayList<String>) Config.getMessages();

			i++;
			if (i == Config.getMessages().size()) {
				i = 0;
			}

			TextComponent component = new TextComponent(BukkitColor.apply(messages.get(i)));

			for (Player player : Bukkit.getOnlinePlayers()) {
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
			}
		}, 0, Config.getActionCountdown() * 20);
	}

}
