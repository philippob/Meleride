package pl.meleride.api.impl.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import pl.meleride.api.basic.User;
import pl.meleride.api.impl.event.UserAbortEvent;
import pl.meleride.api.impl.event.UserLoadEvent;
import pl.meleride.api.manager.UserManager;

import javax.inject.Inject;

public class PlayerLoginListener implements Listener {

  @Inject
  private UserManager userManager;

  @EventHandler(priority = EventPriority.LOWEST)
  public void onPlayerLogin(PlayerLoginEvent event) {
    Player player = event.getPlayer();
    User user = this.userManager.getUser(player.getUniqueId()).get();

    UserLoadEvent userLoadEvent = new UserLoadEvent(user);

    if (userLoadEvent.isCancelled()) {
      UserAbortEvent userAbortEvent = new UserAbortEvent(user);
      Bukkit.getPluginManager().callEvent(userAbortEvent);
      event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Session abort.");
      return;
    }

    Bukkit.getPluginManager().callEvent(userLoadEvent);
  }

}
