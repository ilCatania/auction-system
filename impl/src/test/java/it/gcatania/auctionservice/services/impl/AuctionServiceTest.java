package it.gcatania.auctionservice.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;
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
    pbRepo.save(Arrays.asList(new PersistentBid("Alice", itemName, 11),
        new PersistentBid("Bob", itemName, 33), new PersistentBid("Carl", itemName, 18),
        new PersistentBid("Dave", itemName, 32)));

    Optional<Bid> b = auctionService.getWinningBid(new Item(itemName));
    Bid winningBid = b.get();
    assertThat(winningBid.getBidAmount(), equalTo(33d));
    assertThat(winningBid.getBidder(), equalTo(new User("Bob")));
  }

  @Test
  public void testFindBidsForItem() {
    String itemName = "Shiny new shoes";
    pbRepo.save(new PersistentBid("Alice", itemName, 6));
    pbRepo.save(new PersistentBid("Bob", itemName, 3));
    List<Bid> foundBids = auctionService.getBids(new Item(itemName));

    assertThat(foundBids, hasSize(2));
    Bid bobsBid = new Bid(new User("Bob"), new Item(itemName), 3);
    Bid alicesBid = new Bid(new User("Alice"), new Item(itemName), 6);
    assertThat(foundBids, hasItems(bobsBid, alicesBid));
  }

  @Test
  public void testFindItemsByUser() {
    pbRepo.save(Arrays.asList(new PersistentBid("Alice", "A book", 11),
        new PersistentBid("Alice", "Some old vinyl discs", 33),
        new PersistentBid("Alice", "Betty's engagement ring", 2),
        new PersistentBid("Bob", "A book", 33),
        new PersistentBid("Carl", "Some old vinyl discs", 18),
        new PersistentBid("Carl", "A paper plane", 1337),
        new PersistentBid("Dave", "Betty's engagement ring", 3)));

    assertThat(auctionService.getBidItems(new User("Alice")), hasSize(3));
    assertThat(auctionService.getBidItems(new User("Carl")), hasSize(2));
    assertThat(auctionService.getBidItems(new User("Dave")), hasSize(1));
  }
}
