package it.gcatania.auctionservice.dataobjects;

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


}
