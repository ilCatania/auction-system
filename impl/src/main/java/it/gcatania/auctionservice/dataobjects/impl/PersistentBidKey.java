package it.gcatania.auctionservice.dataobjects.impl;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersistentBidKey implements Serializable {

  private static final long serialVersionUID = -8650734733222008291L;

  @Column(nullable = false, length = 255)
  private String user;
  @Column(nullable = false, length = 255)
  private String item;

  public PersistentBidKey() {}

  public PersistentBidKey(String user, String item) {
    this.user = user;
    this.item = item;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof PersistentBidKey)) {
      return false;
    }
    PersistentBidKey o = (PersistentBidKey) obj;
    return Objects.equals(user, o.user) && Objects.equals(item, o.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, item);
  }

}
