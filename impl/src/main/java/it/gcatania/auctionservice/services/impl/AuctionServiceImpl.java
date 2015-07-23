package it.gcatania.auctionservice.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;
import it.gcatania.auctionservice.services.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {

  @Override
  public Bid placeBid(User user, Item item, double bidAmount) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    return null;
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
