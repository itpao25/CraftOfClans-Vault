package it.itpao25.craftofclansvault.cocvault;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command arg1, String label, String[] args) {
		
		if (!(sender instanceof ConsoleCommandSender)) {
			sender.sendMessage(CocVault.translate("&cOnly console can run this command!"));
			return false;
		}
		if (args.length < 1) {
			sender.sendMessage(CocVault.translate("&cUse /cocvault reload!"));
			return false;
		}

		switch (args[0]) {

		case "reload":
			
			Config.reload();
			CocVault.setValute();
			sender.sendMessage(CocVault.translate("&2Reloaded!"));
			return true;

		default:
			sender.sendMessage(CocVault.translate("&cUse /cocvault reload!"));
			break;
		}
		return false;
	}

}
