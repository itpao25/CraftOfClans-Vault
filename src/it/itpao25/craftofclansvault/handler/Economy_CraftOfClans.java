package it.itpao25.craftofclansvault.handler;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import it.itpao25.craftofclans.api.PlayerStore;
import it.itpao25.craftofclansvault.cocvault.CocVault;
import it.itpao25.craftofclansvault.cocvault.CocVault.Valute;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Economy_CraftOfClans implements Economy {

	private final String name = "CraftOfClans";

	@SuppressWarnings("deprecation")
	public PlayerStore getPlayer(String arg0) {
		OfflinePlayer poffline = Bukkit.getServer().getOfflinePlayer(arg0);
		if (poffline != null) {
			return new PlayerStore(poffline.getUniqueId());
		}
		return null;
	}

	@Override
	public String currencyNamePlural() {
		return CocVault.Valuta_chosen.toString().toLowerCase();
	}
	
	@Override
	public String currencyNameSingular() {
		return CocVault.Valuta_chosen.toString().toLowerCase();
	}
	
	@Override
	public EconomyResponse depositPlayer(String player, double amount) {

		boolean add_result = true;

		if (CocVault.Valuta_chosen.equals(Valute.GEMS)) {
			if (!getPlayer(player).addGems(amount)) {
				add_result = false;
			}
		} else if (CocVault.Valuta_chosen.equals(Valute.GOLD)) {
			if (!getPlayer(player).addGold(amount)) {
				add_result = false;
			}
		} else if (CocVault.Valuta_chosen.equals(Valute.ELIXIR)) {
			if (!getPlayer(player).addElixir(amount)) {
				add_result = false;
			}
		} else if (CocVault.Valuta_chosen.equals(Valute.DARK_ELIXIR)) {
			if (!getPlayer(player).addDarkElixir(amount)) {
				add_result = false;
			}
		}

		if (!add_result) {
			return new EconomyResponse(-1, -1, EconomyResponse.ResponseType.FAILURE, "Error deposit to player");
		}
		return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, "Successfully deposit to player.");
	}

	@Override
	public EconomyResponse withdrawPlayer(String player, double amount) {

		boolean remove_result = true;

		if (CocVault.Valuta_chosen.equals(Valute.GEMS)) {
			if (!getPlayer(player).removeGems(amount)) {
				remove_result = false;
			}
		} else if (CocVault.Valuta_chosen.equals(Valute.GOLD)) {
			if (!getPlayer(player).removeGold(amount)) {
				remove_result = false;
			}
		} else if (CocVault.Valuta_chosen.equals(Valute.ELIXIR)) {
			if (!getPlayer(player).removeElixir(amount)) {
				remove_result = false;
			}
		} else if (CocVault.Valuta_chosen.equals(Valute.DARK_ELIXIR)) {
			if (!getPlayer(player).removeDarkElixir(amount)) {
				remove_result = false;
			}
		}

		if (!remove_result) {
			return new EconomyResponse(-1, -1, EconomyResponse.ResponseType.FAILURE, "Error withdraw gems to player");
		}
		return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, "Successfully withdraw gems to player.");
	}
	
	@Override
	public String format(double arg0) {
		return CocVault.showNumero(arg0);
	}
	
	@Override
	public int fractionalDigits() {
		return 2;
	}

	@Override
	public double getBalance(String player) {
		double gems = 0;
		
		if (CocVault.Valuta_chosen.equals(Valute.GEMS)) {
			gems = getPlayer(player).getGems();
		} else if (CocVault.Valuta_chosen.equals(Valute.GOLD)) {
			gems = getPlayer(player).getGold();
		} else if (CocVault.Valuta_chosen.equals(Valute.ELIXIR)) {
			gems = getPlayer(player).getElixir();
		} else if (CocVault.Valuta_chosen.equals(Valute.DARK_ELIXIR)) {
			gems = getPlayer(player).getDarkElixir();
		}
		return gems;
	}
	
	@Override
	public EconomyResponse depositPlayer(String player, String world, double amount) {
		return depositPlayer(player, amount);
	}

	@Override
	public double getBalance(String player, String world) {
		return getBalance(player);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean has(String player, double amount) {
		return getPlayer(player).hasGems(amount);
	}

	@Override
	public boolean has(String player, String world, double amount) {
		return has(player, amount);
	}

	@Override
	public boolean hasAccount(String player) {
		return getPlayer(player) != null;
	}

	@Override
	public boolean hasAccount(String player, String world) {
		return hasAccount(player);
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public EconomyResponse withdrawPlayer(String player, String world, double amount) {
		return withdrawPlayer(player, amount);
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		return withdrawPlayer(arg0.getName(), arg1);
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		return withdrawPlayer(arg0.getName(), arg2);
	}

	@Override
	public boolean has(OfflinePlayer arg0, double arg1) {
		return has(arg0, arg1);
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		return has(arg0, arg2);
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		return hasAccount(arg0.getName());
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		return hasAccount(arg0.getName());
	}

	@Override
	public double getBalance(OfflinePlayer arg0) {
		return getBalance(arg0.getName());
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		return getBalance(arg0.getName());
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		return depositPlayer(arg0.getName(), arg1);
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		return depositPlayer(arg0.getName(), arg2);
	}

	@Override
	public boolean createPlayerAccount(String arg0) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(String arg0, String arg1) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		return true;
	}

	/**
	 * Disattivati
	 */
	@Override
	public EconomyResponse bankBalance(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		return null;
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		return null;
	}
}
