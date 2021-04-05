package it.itpao25.craftofclansvault.handler;

import java.util.Collection;

import org.bukkit.plugin.RegisteredServiceProvider;

import it.itpao25.craftofclansvault.cocvault.CocVault;
import it.itpao25.craftofclansvault.cocvault.LogHandler;
import net.milkbowl.vault.economy.Economy;

public class Vault_Register {
	public Vault_Register() {
		Collection<RegisteredServiceProvider<Economy>> econs = CocVault.getInstance().getServer().getServicesManager().getRegistrations(Economy.class);
		for (RegisteredServiceProvider<Economy> econ : econs) {
			String econName = econ.getProvider().getName().replace(" ", "");
			LogHandler.log("[Vault] Economy "+ econName +" enabled");
		}
		
		RegisteredServiceProvider<Economy> rsp = CocVault.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        Economy econ = null;
        if (rsp != null) {
            econ = rsp.getProvider();
            LogHandler.log("[Vault] Economy "+ econ.getName() +" it's first on this server.");
        }
	}
}
