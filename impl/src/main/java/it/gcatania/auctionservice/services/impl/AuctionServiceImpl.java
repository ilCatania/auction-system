package it.gcatania.auctionservice.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;
import it.gcatania.auctionservice.dataobjects.impl.PersistentBid;
import it.gcatania.auctionservice.dataobjects.impl.PersistentBidKey;
import it.gcatania.auctionservice.services.AuctionService;
import it.gcatania.auctionservice.utils.Checks;

@Service
public class AuctionServiceImpl implements AuctionService {

  @PersistenceContext
  EntityManager em;

  @Override
  public void placeBid(Bid b) throws IllegalArgumentException {
    Checks.notNull(b, "Null bid provided");
    String bidderName = Checks.notNullName(b.getBidder());
    String bidItemName = Checks.notNullName(b.getBidItem());
    double bidAmount = b.getBidAmount();
    Checks.ensure(bidAmount > 0, "Invalid bid amount: {0}, must be positive", bidAmount);
    PersistentBidKey k = new PersistentBidKey(bidderName, bidItemName);
    PersistentBid pb = em.find(PersistentBid.class, k);

    if (pb == null) {
      pb = new PersistentBid(bidderName, bidItemName, bidAmount);
      em.persist(pb);
    } else {
      // TODO ensure that new bid is higher;
      pb.setBidAmount(bidAmount);
      em.merge(pb);
    }
    em.flush();
  }

  @Override
  public Bid getWinningBid(Item item) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Bid> getBids(Item item) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Item> getBidItems(User user) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    return null;
  }

}
