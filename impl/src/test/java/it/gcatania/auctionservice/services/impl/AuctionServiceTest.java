package it.gcatania.auctionservice.services.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Optional;

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
    assertThat(pb.getBidAmount(), equalTo(50d));
  }

  @Test
  public void testFindWinningBid() {
    String itemName = "A pineapple";
    pbRepo.save(Arrays.asList(new PersistentBid("Alice", itemName, 11)));
    pbRepo.save(Arrays.asList(new PersistentBid("Bob", itemName, 33)));
    pbRepo.save(Arrays.asList(new PersistentBid("Carl", itemName, 18)));
    pbRepo.save(Arrays.asList(new PersistentBid("Dave", itemName, 32)));

    Optional<Bid> b = auctionService.getWinningBid(new Item(itemName));
    Bid winningBid = b.get();
    assertThat(winningBid.getBidAmount(), equalTo(33d));
    assertThat(winningBid.getBidder(), equalTo(new User("Bob")));
  }

}
