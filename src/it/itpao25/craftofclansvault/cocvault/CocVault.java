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
	
	// Available valute
	public enum Valute {
		GEMS, GOLD, ELIXIR, DARK_ELIXIR
	};
	
	// Valute chosen
	public static Valute Valuta_chosen;

	public static CocVault getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		// Vault
		Vault_hook vault = new Vault_hook();
		isVault = vault.enable;
		
		getCommand("cocvault").setExecutor(new Command());
		
		if (isVault) {
			
			// Search the choose valute from config
			loadconfig();
			setValute();
			
			// Initialize Vault API
			getInstance().getServer().getServicesManager().register(net.milkbowl.vault.economy.Economy.class, new Economy_CraftOfClans(), getInstance(), ServicePriority.Highest);
			new Vault_Register();
			
		} else {
			Bukkit.getConsoleSender().sendMessage(CocVault.translate("&c[&6CraftOfClans Vault&c] &cVault is not installed on this server. Please install it and retry."));
			this.setEnabled(false);
			return;
		}
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(CocVault.translate("&c[&6CraftOfClans Vault&c] &4Plugin disabled."));
	}
	
	public static void setValute() {
		String nome_valuta = null;
		if (Config.getString("vault-valute") != null) {
			nome_valuta = Config.getString("vault-valute");
		} else {
			nome_valuta = "GEMS";
		}
		Valuta_chosen = Valute.valueOf(nome_valuta);
		Bukkit.getConsoleSender().sendMessage(CocVault.translate("&c[&6CraftOfClans Vault&c] &cVault setted. Valute chosen " + Valuta_chosen.toString()));
	}

	public static String translate(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}

	@SuppressWarnings("unused")
	private boolean loadconfig() {
		Config CraftOfClansC = new Config();
		return true;
	}
}
