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
    Bid bid = auctionService.placeBid(alice, oldSofa, 50);
    Assert.assertNotNull(bid, "No bid returned by placeBid()");
    Assert.assertEquals(bid.getBidder(), alice);
    Assert.assertEquals(bid.getBidItem(), oldSofa);
    Assert.assertEquals(bid.getBidAmount(), 50d);
  }

}
