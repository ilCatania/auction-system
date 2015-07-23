package it.gcatania.auctionservice.services.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.gcatania.auctionservice.dao.PersistentBidRepository;
import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;
import it.gcatania.auctionservice.dataobjects.impl.PersistentBid;
import it.gcatania.auctionservice.dataobjects.impl.PersistentBidKey;
import it.gcatania.auctionservice.services.AuctionService;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration
public class AuctionServiceTest {

  @Autowired
  private AuctionService auctionService;

  @Autowired
  private PersistentBidRepository pbRepo;

  @Test
  public void testPlaceBid() {
    User alice = new User("Alice");
    Item oldSofa = new Item("Old sofa");
    Bid bid = new Bid(alice, oldSofa, 50);
    auctionService.placeBid(bid);

    PersistentBid pb = pbRepo.findOne(new PersistentBidKey("Alice", "Old sofa"));
    assertThat(pb, notNullValue());
    assertThat(pb.getBidAmount(), equalTo(50d));
  }

}
