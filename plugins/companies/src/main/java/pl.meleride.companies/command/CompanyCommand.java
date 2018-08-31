package pl.meleride.companies.command;

import static pl.meleride.api.message.MessageUtil.colored;

import java.util.Collections;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.meleride.commands.CommandInfo;
import pl.meleride.commands.context.CommandContext;
import pl.meleride.companies.MelerideCompanies;
import pl.meleride.companies.configurator.CompanyConfigurator;
import pl.meleride.companies.entity.User;
import pl.meleride.companies.enums.MakeStatus;
import pl.meleride.companies.inventory.CompanyInventory;

public final class CompanyCommand {

  private final MelerideCompanies plugin;

  public CompanyCommand(MelerideCompanies plugin) {
    this.plugin = plugin;
  }

  @CommandInfo(name = "company", userOnly = true, permission = "meleride.companies.companies", usage = "[create/list]")
  public void companyCommand(CommandSender commandSender, CommandContext context) {
    Player player = (Player) commandSender;
    User user = this.plugin.getUserManager().getUser(player).get();

    if(context.getParamsLength() == 0) {
      if(!user.getCompany().isPresent()) {
        player.sendMessage(colored("&7&m -----------&f &8[ &9Firmy &8] &7&m-----------"));
        player.sendMessage(colored("&7» Firmy sa uniwersalnym systemem posiadania wlasnych dzialalnosci."));
        player.sendMessage(colored("&7» /company (potrzebna firma) - wyswietlenie szczegolow firmy"));
        player.sendMessage(colored("&7» PPM na NPC Urzad miasta, /company create - tworzenie firmy"));
        player.sendMessage(colored("&7» /company list - lista aktualnych firm"));

        return;
      }
      CompanyInventory inventory = new CompanyInventory(player);
      inventory.openInventory(player);
    } else {
      switch (context.getParam(0)) {
        case "create": {
          if(user.getCompany().isPresent()) {
            player.sendMessage(colored("&8» &cNie mozesz ponownie zakladac firmy! Juz posiadasz jedna."));
            break;
          }

          new CompanyConfigurator("Nie ustawiona", null, Collections.emptyList(), user).nextStep(player, MakeStatus.NAME);
          break;
        }
        case "list": {
          new CompanyListCommand(this.plugin).execute(commandSender, context);
          break;
        }
        default: {
          player.sendMessage(colored("&8» &7Nie ma takiego argumentu!"));
          player.sendMessage(colored("&8» &7Sprobuj uzyc argumentu \"list\"!"));
        }
      }
    }
  }

}