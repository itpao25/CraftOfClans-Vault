package it.itpao25.craftofclansvault.cocvault;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import it.itpao25.craftofclansvault.handler.Economy_CraftOfClans;
import it.itpao25.craftofclansvault.handler.Vault_Register;
import it.itpao25.craftofclansvault.handler.Vault_hook;

public class CocVault extends JavaPlugin {

	private static CocVault instance;

	private static boolean isVault;

	public static CocVault getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;

		// Vault
		Vault_hook vault = new Vault_hook();
		isVault = vault.enable;

		if (isVault) {
			getServer().getServicesManager().register(net.milkbowl.vault.economy.Economy.class, new Economy_CraftOfClans(), getInstance(), ServicePriority.Highest);
			Bukkit.getConsoleSender().sendMessage(CocVault.translate("&c[&6CraftOfClans Vault&c] &cVault detected"));
			new Vault_Register();
		} else {
			Bukkit.getConsoleSender().sendMessage(CocVault.translate("&c[&6CraftOfClans Vault&c] &cVault is not installed on this server. Please install it and retry."));
			this.setEnabled(false);
		}
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(CocVault.translate("&c[&6CraftOfClans Vault&c] &4Plugin disabled."));
	}

	public static String translate(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}
}
