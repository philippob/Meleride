package pl.meleride.cars.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import pl.meleride.cars.car.CarType;
import pl.meleride.cars.event.MelerideCarSpawnEvent;
import pl.meleride.cars.car.BasicCar;

public class MelerideCarsAPI {

  private static Map<UUID, BasicCar> cars = new HashMap<>();
  private static Map<UUID, UUID> seatconnect = new HashMap<>();

  public void registerPublicCar(Location loc, BasicCar basicCar) {

  }

  public void spawnCar(Player p, CarType carType, double max_speed, int durability, int max_passengers, byte model,
                       byte brand, Location l) {

    ArmorStand c2 = l.getWorld().spawn(l, ArmorStand.class);
    c2.setVisible(false);
    c2.setGravity(true);
    c2.setCustomName("Auto");
    c2.setCustomNameVisible(false);
    c2.setFireTicks(0);
    c2.setMarker(false);

    BasicCar basicCar = new BasicCar(c2, carType, max_speed, durability, max_passengers, model, brand, l, p);

    MelerideCarSpawnEvent event = new MelerideCarSpawnEvent(p, basicCar);
    Bukkit.getServer().getPluginManager().callEvent(event);
    if (event.isCancelled()) {
      basicCar.getSeat().remove();
      basicCar.getCar().remove();
      return;
    }
    cars.put(c2.getUniqueId(), basicCar);
  }

  public static Map<UUID, BasicCar> getCarsMap() {
    return cars;
  }

  public static Map<UUID, UUID> getSeatConnect() {
    return seatconnect;
  }

}