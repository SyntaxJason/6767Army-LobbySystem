package de.syntaxtnt.minecraft.lobbysystem.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.syntaxtnt.minecraft.lobbysystem.util.BukkitColor;

public class Config {

	public static File ConfigFile = new File("plugins/LobbySystem", "Config.yml");
	public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);

	public static void save() {
		try {
			Config.save(ConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getSpeed() {
		return Config.getInt("Lobby.speed.multiplier");
	}

	public static void setSpeed(int multiplier) {
		Config.set("Lobby.speed.multiplier", multiplier);
	}

	public static void setLocation(String gamePath, World world, int x, int y, int z, double pitch, double yaw) {
		String compact = world.getName() + ":" + x + ";" + y + ";" + z + ";" + pitch + ";" + yaw;
		Config.set("Lobby.location." + gamePath, compact);
	}

	public static Location getLocation(String gamePath) {
		String compact = Config.getString("Lobby.location." + gamePath);
		if(compact != null) {
		String world = compact.split(":")[0];
		double x = Double.parseDouble(compact.split(":")[1].split(";")[0]);
		double y = Double.parseDouble(compact.split(";")[1]);
		double z = Double.parseDouble(compact.split(";")[2]);
		float pitch = Float.parseFloat(compact.split(";")[3]);
		float yaw = Float.parseFloat(compact.split(";")[4]);
		Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
		return loc;
		}
		return null;
	}

	public static void setText(List<String> text) {
		Config.set("Lobby.infos", text);
	}

	public static List<String> getMessages() {
		return Config.getStringList("Lobby.actionbar.text");
	}

	public static void setMessage(List<String> text) {
		Config.set("Lobby.actionbar.text", text);
	}

	public static List<String> getText() {
		for (int i = 0; i < Config.getStringList("Lobby.infos").size(); i++) {
			BukkitColor.apply(Config.getStringList("Lobby.infos").get(i));
		}
		return Config.getStringList("Lobby.infos");
	}

	public static void setActionCountdown(int cooldown) {
		Config.set("Lobby.actionbar.cooldown", cooldown);
	}

	public static int getActionCountdown() {
		return Config.getInt("Lobby.actionbar.cooldown");
	}

	public static void setScoreboard(List<String> string) {
		Config.set("Lobby.scoreboard", string);
	}

	public static List<String> getScoreboard() {
		return Config.getStringList("Lobby.scoreboard");
	}
	
	public static void setDoubleJumpMutliply(double multiply) {
		Config.set("Lobby.doublejump.multiply", multiply);
	}

	public static void setDoubleJumpY(double Y) {
		Config.set("Lobby.doublejump.Y", Y);
	}
	
	public static ArrayList<Double> getDoubleJump() {
		ArrayList<Double> list = new ArrayList<>();
		Double multiply = Config.getDouble("Lobby.doublejump.multiply");
		Double Y = Config.getDouble("Lobby.doublejump.Y");
		list.add(multiply);
		list.add(Y);
		return list;
	}
	
	public static FileConfiguration get() {
		return Config;
	}

}
