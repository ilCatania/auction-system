package it.gcatania.auctionservice.mvc;

import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;

public class BidDataHolder {
  private String user;
  private String item;
  private double amount;

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

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Bid toBid() {
    return new Bid(new User(user), new Item(item), amount);
  }

  public void fromBid(Bid b) {
    User bidder = b.getBidder();
    user = bidder != null ? bidder.getName() : null;
    Item bidItem = b.getBidItem();
    item = bidItem != null ? bidItem.getName() : null;
    amount = b.getBidAmount();
  }
}
