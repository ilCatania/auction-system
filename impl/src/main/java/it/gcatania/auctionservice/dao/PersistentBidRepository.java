package it.gcatania.auctionservice.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import it.gcatania.auctionservice.dataobjects.impl.PersistentBid;
import it.gcatania.auctionservice.dataobjects.impl.PersistentBidKey;

@RepositoryDefinition(domainClass = PersistentBid.class, idClass = PersistentBidKey.class)
public interface PersistentBidRepository extends CrudRepository<PersistentBid, PersistentBidKey> {

  PersistentBid findTopByKeyItem(String itemName, Sort sort);

  List<PersistentBid> findByKeyItem(String itemName);

  @Query("select key.item from PersistentBid where key.user = ?")
  List<String> findItemsByUser(String userName);
}
