package it.gcatania.auctionservice.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import it.gcatania.auctionservice.dataobjects.impl.PersistentBid;
import it.gcatania.auctionservice.dataobjects.impl.PersistentBidKey;

@RepositoryDefinition(domainClass = PersistentBid.class, idClass = PersistentBidKey.class)
public interface PersistentBidRepository extends CrudRepository<PersistentBid, PersistentBidKey> {

  PersistentBid findTopByKeyItem(String itemName, Sort sort);
}
