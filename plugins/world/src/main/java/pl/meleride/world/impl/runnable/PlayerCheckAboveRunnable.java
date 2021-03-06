package pl.meleride.world.impl.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

import pl.meleride.api.i18n.MessageBundler;
import pl.meleride.api.message.MessageType;
import pl.meleride.user.MelerideUser;
import pl.meleride.user.disease.Disease;
import pl.meleride.user.entity.User;
import pl.meleride.world.MelerideWorld;
import pl.meleride.world.impl.weather.Weather;

public class PlayerCheckAboveRunnable extends BukkitRunnable {

  private final MelerideWorld instance;
  private final MelerideUser meleUser = JavaPlugin.getPlugin(MelerideUser.class);
  private User user;
  private Weather weather;

  public PlayerCheckAboveRunnable(MelerideWorld instance) {
    this.instance = instance;
  }

  @Override
  public void run() {
    Location location;
    World world;
    this.weather = instance.getWeatherInstance();

    if (this.weather.getNewerForecast().equalsIgnoreCase("hurricane")
        || this.weather.getNewerForecast().contains("storm")
        || this.weather.getNewerForecast().contains("thunder")
        || this.weather.getNewerForecast().contains("rain")
        || this.weather.getNewerForecast().contains("snow")) {

      for (Player player : Bukkit.getOnlinePlayers()) {
        location = player.getLocation();
        world = player.getLocation().getWorld();

        if (world.getHighestBlockAt(location).getY() < player.getEyeLocation().getY()) {
          if (player.getInventory().getChestplate() == null) {
            this.freezingConditions(player);
          }
        }
      }
    }
  }

  private void freezingConditions(Player player) {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    this.user = this.meleUser.getUserManager().getUser(player).get();

    if (!(this.meleUser.getUserManager().hasDisease(user, Disease.FEVER))) {
      int rnd = random.nextInt(100);
      if (rnd <= 10) {
        this.meleUser.getUserManager().addDisease(user, Disease.FEVER);

        MessageBundler.create("disease.fever.title")
            .target(MessageType.TITLE)
            .sendTo(player);

        MessageBundler.create("disease.fever.subtitle")
            .target(MessageType.SUB_TITLE)
            .sendTo(player);

        MessageBundler.create("disease.fever.message")
            .target(MessageType.CHAT)
            .sendTo(player);
      }
    }
  }

}
