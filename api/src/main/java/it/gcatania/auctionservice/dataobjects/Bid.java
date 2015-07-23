package it.gcatania.auctionservice.dataobjects;

import java.util.Objects;

public class Bid {

  private final User bidder;
  private final Item bidItem;
  private final double bidAmount;

  public Bid(User bidder, Item bidItem, double bidAmount) {
    this.bidder = bidder;
    this.bidItem = bidItem;
    this.bidAmount = bidAmount;
  }

  public User getBidder() {
    return bidder;
  }

  public Item getBidItem() {
    return bidItem;
  }

  public double getBidAmount() {
    return bidAmount;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Bid)) {
      return false;
    }
    Bid o = (Bid) obj;
    return Objects.equals(bidder, o.bidder) && Objects.equals(bidItem, o.bidItem)
        && bidAmount == o.bidAmount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(bidder, bidItem, bidAmount);
  }

  @Override
  public String toString() {
    return new StringBuilder().append(bidder).append(", ").append(bidItem).append(", ")
        .append(bidAmount).toString();
  }

}
