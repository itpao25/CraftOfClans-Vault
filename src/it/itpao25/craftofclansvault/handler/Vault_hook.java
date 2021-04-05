package it.itpao25.craftofclansvault.handler;

import org.bukkit.plugin.Plugin;

import it.itpao25.craftofclansvault.cocvault.CocVault;

public class Vault_hook {
	public boolean enable = false;
	public Plugin vault;

	public Vault_hook() {
		setup();
	}

	public void setup() {
		Plugin plugin = CocVault.getInstance().getServer().getPluginManager().getPlugin("Vault");
		if (plugin == null) {
			return;
		}
		this.vault = plugin;
		enable = true;
	}
}
