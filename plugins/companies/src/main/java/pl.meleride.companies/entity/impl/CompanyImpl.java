package pl.meleride.companies.entity.impl;

import com.google.common.base.Objects;
import java.util.List;
import java.util.UUID;
import pl.meleride.companies.entity.Company;
import pl.meleride.companies.entity.User;

public final class CompanyImpl implements Company {

  private final UUID identifier;
  private String name;
  private User owner;
  private List<String> workers;
  private int level;

  CompanyImpl(UUID identifier, String name, User owner, List<String> workers, int level) {
    this.identifier = identifier;
    this.name = name;
    this.owner = owner;
    this.workers = workers;
    this.level = level;
  }

  @Override
  public UUID getIdentifier() {
    return this.identifier;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public User getOwner() {
    return this.owner;
  }

  @Override
  public List<String> getWorkers() {
    return this.workers;
  }

  @Override
  public int getLevel() {
    return this.level;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setOwner(User owner) {
    this.owner = owner;
  }

  @Override
  public void setWorkers(List<String> workers) {
    this.workers = workers;
  }

  @Override
  public void setLevel(int level) {
    this.level = level;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompanyImpl company = (CompanyImpl) o;
    return level == company.level &&
        Objects.equal(identifier, company.identifier) &&
        Objects.equal(name, company.name) &&
        Objects.equal(owner, company.owner) &&
        Objects.equal(workers, company.workers);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(identifier, name, owner, workers, level);
  }

}