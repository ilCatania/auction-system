package it.gcatania.auctionservice.dataobjects;

import java.util.Objects;

public class User {

  private final String name;

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof User)) {
      return false;
    }
    User o = (User) obj;
    return Objects.equals(name, o.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }

  @Override
  public String toString() {
    return "user: " + name;
  }
}
