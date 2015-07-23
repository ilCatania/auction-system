package it.gcatania.auctionservice.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import it.gcatania.auctionservice.dao.PersistentBidRepository;
import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;
import it.gcatania.auctionservice.dataobjects.impl.PersistentBid;
import it.gcatania.auctionservice.dataobjects.impl.PersistentBidKey;
import it.gcatania.auctionservice.services.AuctionService;
import it.gcatania.auctionservice.utils.Checks;

@Service
public class AuctionServiceImpl implements AuctionService {

  @Autowired
  private PersistentBidRepository pbRepo;

  @Override
  public void placeBid(Bid b) throws IllegalArgumentException {
    Checks.notNull(b, "Null bid provided");
    String bidderName = Checks.notNullName(b.getBidder());
    String bidItemName = Checks.notNullName(b.getBidItem());
    double bidAmount = b.getBidAmount();
    Checks.ensure(bidAmount > 0, "Invalid bid amount: {0}, must be positive", bidAmount);
    PersistentBidKey k = new PersistentBidKey(bidderName, bidItemName);
    PersistentBid pb = pbRepo.findOne(k);

    if (pb == null) {
      pb = new PersistentBid(bidderName, bidItemName, bidAmount);
    } else {
      // TODO ensure that new bid is higher;
      pb.setBidAmount(bidAmount);
    }
    pbRepo.save(pb);
  }

  @Override
  public Optional<Bid> getWinningBid(Item item) throws IllegalArgumentException {
    String itemName = Checks.notNullName(item);
    PersistentBid found = pbRepo.findTopByKeyItem(itemName, new Sort(Direction.DESC, "bidAmount"));
    if (found == null) {
      return Optional.empty();
    }
    return Optional.of(found.toBid());
  }

  @Override
  public List<Bid> getBids(Item item) throws IllegalArgumentException {
    String itemName = Checks.notNullName(item);
    return pbRepo.findByKeyItem(itemName).stream().map(b -> b.toBid()).collect(Collectors.toList());
  }

  @Override
  public List<Item> getBidItems(User user) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    return null;
  }

}
