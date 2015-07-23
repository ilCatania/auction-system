package it.gcatania.auctionservice.dataobjects.impl;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;

@Entity
public class PersistentBid {

  @EmbeddedId
  private PersistentBidKey key;
  @Column(nullable = false)
  private double bidAmount;

  public PersistentBid() {}

  public PersistentBid(String user, String item, double amount) {
    key = new PersistentBidKey(user, item);
    bidAmount = amount;
  }

  public Bid toBid() {
    String user;
    String item;
    if (key != null) {
      user = key.getUser();
      item = key.getItem();
    } else {
      user = null;
      item = null;
    }
    return new Bid(new User(user), new Item(item), bidAmount);
  }

  public PersistentBidKey getKey() {
    return key;
  }

  public void setKey(PersistentBidKey key) {
    this.key = key;
  }

  public double getBidAmount() {
    return bidAmount;
  }

  public void setBidAmount(double bidAmount) {
    this.bidAmount = bidAmount;
  }

}
