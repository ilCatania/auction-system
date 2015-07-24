
package it.gcatania.auctionservice.mvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.gcatania.auctionservice.dataobjects.Bid;
import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;
import it.gcatania.auctionservice.services.AuctionService;

@Controller
public class AuctionController {
  private final AuctionService auctionService;

  @Autowired
  public AuctionController(AuctionService service) {
    auctionService = service;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String auctionService(Model model) {
    model.addAttribute("bidData", new BidDataHolder());
    return "home";
  }

  @RequestMapping(value = "/placeBid", method = RequestMethod.POST)
  public String placeBid(@ModelAttribute BidDataHolder bidData, Model model) {
    auctionService.placeBid(bidData.toBid());
    model.addAttribute("bidData", new BidDataHolder());
    // TODO report that the bid was inserted
    return "home";
  }

  @RequestMapping("/getWinningBid")
  public ModelAndView getWinningBid(@ModelAttribute BidDataHolder bidData) {
    String item = bidData.getItem();
    Optional<Bid> winningBid = auctionService.getWinningBid(new Item(item));
    if (winningBid.isPresent()) {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("bids", Collections.singletonList(winningBid.get()));
      model.put("title", "Winning bid for item: " + item);
      return new ModelAndView("viewBids", model);
    }
    // TODO error messages
    return new ModelAndView("home");
  }

  @RequestMapping("/getBidsOnItem")
  public ModelAndView getBidsOnItem(@ModelAttribute BidDataHolder bidData) {
    String item = bidData.getItem();
    List<Bid> bids = auctionService.getBids(new Item(item));
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("bids", bids);
    model.put("title", "Bids for item: " + item);
    return new ModelAndView("viewBids", model);
  }

  @RequestMapping("/getBidsFromUser")
  public ModelAndView getBidsFromUser(@ModelAttribute BidDataHolder bidData) {
    String user = bidData.getUser();
    List<Item> items = auctionService.getBidItems(new User(user));
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("items", items);
    model.put("user", user);
    return new ModelAndView("viewItems", model);
  }
}
