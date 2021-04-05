package it.itpao25.craftofclansvault.cocvault;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	public static File dir = new File("plugins/CraftOfClans");
	public static File cfg = new File(dir, "vault.yml");

	private static YamlConfiguration cfgYml;

	public Config() {
		if (!dir.exists()) {
			LogHandler.error("Folder /CraftOfClans doesn't exists");
			return;
		}
		
		if (!cfg.exists()) {
			LogHandler.error("File CraftOfClans/vault.yml doesn't exists, try to create");
			try {
				cfg.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		cfgYml = YamlConfiguration.loadConfiguration(cfg);
		
		if (cfgYml.getString("vault-valute") == null) {
			cfgYml.set("vault-valute", "GEMS");
			save();
		}
	}

	public static String getString(String string) {
		if (cfgYml != null) {
			return cfgYml.getString(string);
		}
		return null;
	}

	public static Boolean getBoolean(String string) {
		if (cfgYml != null) {
			return cfgYml.getBoolean(string);
		}
		return null;
	}

	public static Integer getInt(String string) {
		if (cfgYml != null) {
			return cfgYml.getInt(string);
		}
		return null;
	}

	public static YamlConfiguration get() {
		if (cfgYml != null) {
			return cfgYml;
		}
		return null;
	}

	public static void reload() {
		cfgYml = YamlConfiguration.loadConfiguration(cfg);
	}

	public static boolean save() {
		try {
			cfgYml.save(cfg);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}