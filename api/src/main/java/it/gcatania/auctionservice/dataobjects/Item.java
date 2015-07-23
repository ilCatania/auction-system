package it.gcatania.auctionservice.dataobjects;

import java.util.Objects;

public class Item {

  private final String name;

  public Item(String name) {
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
    if (!(obj instanceof Item)) {
      return false;
    }
    Item o = (Item) obj;
    return Objects.equals(name, o.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }
}
