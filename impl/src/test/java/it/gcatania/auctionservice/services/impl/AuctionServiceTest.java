package it.gcatania.auctionservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;
import it.gcatania.auctionservice.services.AuctionService;

@ContextConfiguration
public class AuctionServiceTest extends AbstractTransactionalTestNGSpringContextTests {

  @Autowired
  private AuctionService auctionService;

  @Test
  public void testPlaceBid() {
    User alice = new User("Alice");
    Item oldSofa = new Item("Old sofa");
    Bid bid = new Bid(alice, oldSofa, 50);
    auctionService.placeBid(bid);

    int count = countRowsInTableWhere("PersistentBid",
        "\"user\" = 'Alice' and item = 'Old sofa' and bidAmount = 50");
    Assert.assertEquals(count, 1, "Bid was not persisted on the database");
  }

}
