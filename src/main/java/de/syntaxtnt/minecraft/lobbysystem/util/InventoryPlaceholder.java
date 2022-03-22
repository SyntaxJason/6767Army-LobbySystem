package de.syntaxtnt.minecraft.lobbysystem.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import de.syntaxtnt.minecraft.lobbysystem.config.Config;

public class InventoryPlaceholder {

	private InventoryPlaceholder() {
	}

	public static final ItemStack NAVIGATOR;
	public static final ItemStack INFOS;
	public static final ItemStack SPEED;
	public static final ItemStack SHOW_PLAYER;
	public static final ItemStack HIDE_PLAYER;
	public static final ItemStack SPAWN;
	public static final ItemStack SCREEN;
	public static final ItemStack COMMUNITY;
	public static final ItemStack PVP1;
	public static final ItemStack PVP2;
	public static final ItemStack CITYBUILD;
	public static final ItemStack PLACEHOLDER;
	
	public static final Inventory NAVIGATOR_INV = Bukkit.createInventory(null, 27, BukkitColor.apply("&2&lNavigator"));
	
	public static ItemStack getBook() {
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setAuthor("6767Army");
		meta.setTitle(BukkitColor.apply("&2&lInfos"));
		meta.setPages(Config.getText());
		List<String> pages = meta.getPages();
		List<String> newpages = new ArrayList<>();
		for(String page : pages) {
			newpages.add(BukkitColor.apply("&9" + page));
			meta.setPages(newpages);
		}
		book.setItemMeta(meta);
		return book;
	}
	
	
	static {

		NAVIGATOR = new ItemStack(Material.EMERALD);
		ItemMeta meta = NAVIGATOR.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lNavigator"));
		NAVIGATOR.setItemMeta(meta);
		
		INFOS = new ItemStack(Material.ENCHANTED_BOOK);
		meta = INFOS.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lInfos"));
		INFOS.setItemMeta(meta);
		
		SPEED = new ItemStack(Material.FEATHER);
		meta = SPEED.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&b&lGeschwindigkeit"));
		SPEED.setItemMeta(meta);
		
		SHOW_PLAYER = new ItemStack(Material.LIME_DYE);
		meta = SHOW_PLAYER.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lAlle Spieler anzeigen"));
		SHOW_PLAYER.setItemMeta(meta);
		
		HIDE_PLAYER = new ItemStack(Material.GRAY_DYE);
		meta = HIDE_PLAYER.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lAlle Spieler verstecken"));
		HIDE_PLAYER.setItemMeta(meta);
		
		SPAWN = new ItemStack(Material.EMERALD);
		meta = SPAWN.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lSpawn"));
		SPAWN.setItemMeta(meta);
		
		SCREEN = new ItemStack(Material.NAME_TAG);
		meta = SCREEN.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lScreen-Boxen"));
		SCREEN.setItemMeta(meta);
		
		COMMUNITY = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
		meta = COMMUNITY.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lCommunity-Bühne"));
		COMMUNITY.setItemMeta(meta);
		
		PVP1 = new ItemStack(Material.DIAMOND_SWORD);
		meta = PVP1.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lPvP-Zone1"));
		PVP1.setItemMeta(meta);
		
		PVP2 = new ItemStack(Material.IRON_SWORD);
		meta = PVP2.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lPvP-Zone2"));
		PVP2.setItemMeta(meta);
		
		CITYBUILD = new ItemStack(Material.GRASS_BLOCK);
		meta = CITYBUILD.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&lCityBuild"));
		CITYBUILD.setItemMeta(meta);
		
		PLACEHOLDER = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		meta = PLACEHOLDER.getItemMeta();
		meta.setDisplayName(BukkitColor.apply("&9&l"));
		PLACEHOLDER.setItemMeta(meta);
		
	}

}
