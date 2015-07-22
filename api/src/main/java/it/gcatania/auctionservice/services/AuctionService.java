package it.gcatania.auctionservice.services;

import java.util.List;

import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;

public interface AuctionService {

  /**
   * registers the placement of the input bid
   *
   * @param user the user placing the bid
   * @param item the item under bid
   * @return bid the placed bid
   * @throws IllegalArgumentException if the input parameters are null or with missing values for
   *         mandatory fields
   */
  Bid placeBid(User user, Item item, double bidAmount) throws IllegalArgumentException;

  /**
   * retrieves the current winning bid for an input item.
   *
   * @param item the item
   * @return the current winning bid, i.e. the bid with the higher amount. May be null if no bid was
   *         ever placed on the input item. <strong>Note:</strong> if two different users place bids
   *         on the same item with the same amount, the bid that was placed first will be considered
   *         as the winning bid
   * @throws IllegalArgumentException if the input item is null or with missing values
   */
  Bid getWinningBid(Item item) throws IllegalArgumentException;

  /**
   * retrieves all the bids recorded for an input item
   *
   * @param item the item
   * @return a list with all the bids recorded for the input item. The list may be empty, but never
   *         null
   * @throws IllegalArgumentException if the input item is null or with missing values
   */
  List<Bid> getBids(Item item) throws IllegalArgumentException;

  /**
   * retreves all the items for which the input user submitted at least one bid
   *
   * @param user the user
   * @return a list with all the items for which the user submitted any bids. The list may be empty,
   *         but never null
   * @throws IllegalArgumentException if the input user is null or with missing values
   */
  List<Item> getBidItems(User user) throws IllegalArgumentException;

}
