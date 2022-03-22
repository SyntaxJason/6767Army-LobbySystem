package de.syntaxtnt.minecraft.lobbysystem.scoreboard;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.syntaxtnt.minecraft.lobbysystem.util.BukkitColor;
import de.syntaxtnt.minecraft.lobbysystem.LobbySystem;
import de.syntaxtnt.minecraft.lobbysystem.config.Config;

public class Scoreboard extends ScoreboardBuilder {

	private int Id0;
	private int Id1;
	private int Id2;
	private int Id3;

	public Scoreboard(Player player) {
		super(player, BukkitColor.apply("&9&l6767&2&lArmy&6&l.de"));
		Id0 = 0;

		run();
	}

	@Override
	public void createScoreboard() {
		ArrayList<String> array = new ArrayList<>();
		for (String string : Config.getScoreboard()) {
			array.add(string.replaceAll("%player%", player.getName()).replaceAll("%world%", player.getWorld().getName())
					.replaceAll("%onlinePlayer%", "" + Bukkit.getOnlinePlayers().size())
					.replaceAll("%maxPlayers%", "" + Bukkit.getMaxPlayers()));
		}

		setScore(array.get(0), 15);
		setScore(array.get(1), 14);
		setScore(array.get(2), 13);
		setScore(array.get(3), 12);
		setScore(array.get(4), 11);
		setScore(array.get(5), 10);
		setScore(array.get(6), 9);
		setScore(array.get(7), 8);
		setScore(array.get(8), 7);
		setScore(array.get(9), 6);
		setScore(array.get(10), 5);
		setScore(array.get(11), 4);
		setScore(array.get(12), 3);
		setScore(array.get(13), 2);
		setScore(array.get(14), 1);
		setScore(array.get(15), 0);
	}

	@Override
	public void update() {

	}

	private void run() {
		new BukkitRunnable() {
			@Override
			public void run() {

				switch (Id0) {
				case 0:
					setScore("§2 " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers(), 3);
					setScore("§6> §9§lCoins:", 10);
					break;
				case 1:
					setScore("§2 " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers(), 3);
					setScore("§6> §9§lBank:", 10);
					break;
				case 2:
					setScore("§2 " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers(), 3);
					setScore("§6> §9§lLevel:", 10);
					break;
				}

				switch (Id1) {
				case 0:
					setScore("§2 %money%⛁", 9);
					break;
				case 1:
					setScore("§2 %guthaben%⛁", 9);
					break;
				case 2:
					setScore("§2 %level%", 9);
					break;
				}

				switch (Id2) {
				case 0:
					setScore("§6> §9§lJob:", 1);
					break;
				case 1:
					setScore("§6> §9§lFreunde:", 1);
					break;
				case 2:
					setScore("§6> §9§lClan:", 1);
					break;
				}

				switch (Id3) {
				case 0:
					setScore("§2 %job%", 0);
					break;
				case 1:
					setScore("§2 %friends%", 0);
					break;
				case 2:
					setScore("§2 %clan%", 0);
					break;
				}

				if (Id0 >= 3) {
					Id0 = 0;
				}
				if (Id1 >= 3) {
					Id1 = 0;
				}
				if (Id2 >= 3) {
					Id2 = 0;
				}
				if (Id3 >= 3) {
					Id3 = 0;
				}

				Id0++;
				Id1++;
				Id2++;
				Id3++;

			}
		}.runTaskTimer(LobbySystem.getInstance(), 20, 60);
	}

}
