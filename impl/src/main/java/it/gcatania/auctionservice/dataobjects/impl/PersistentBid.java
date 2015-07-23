package it.gcatania.auctionservice.dataobjects.impl;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

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
