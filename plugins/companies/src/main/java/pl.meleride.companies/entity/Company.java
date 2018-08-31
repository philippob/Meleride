package pl.meleride.companies.entity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pl.meleride.api.entity.IdentifiableEntity;

public interface Company extends IdentifiableEntity<UUID> {

  Optional<String> getName();

  User getOwner();

  List<User> getWorkers();

  int getLevel();

  String getBusiness();

  void setName(String name);

  void setOwner(User owner);

  void setWorkers(List<User> workers);

  void setLevel(int level);

  void setBusiness(String business);

}