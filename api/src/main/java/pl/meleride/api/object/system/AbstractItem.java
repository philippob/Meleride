package pl.meleride.api.object.system;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class AbstractItem implements Listener {

  private String name;
  private ItemStack itemStack;

  protected AbstractItem(String name) {
    this.name = name.toLowerCase();
  }

  public String getName() {
    return this.name;
  }

  public ItemStack getItemStack() {
    return this.itemStack;
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent e) {
  }

}
