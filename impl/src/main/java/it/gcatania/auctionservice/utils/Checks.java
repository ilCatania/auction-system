package it.gcatania.auctionservice.utils;

import java.text.MessageFormat;

import it.gcatania.auctionservice.dataobjects.Item;
import it.gcatania.auctionservice.dataobjects.User;

public final class Checks {

  private Checks() {}

  public static IllegalArgumentException e(String message, Object... args) {
    return new IllegalArgumentException(MessageFormat.format(message, args));
  }

  public static void ensure(boolean condition, String message, Object... args) {
    if (!condition) {
      throw e(message, args);
    }
  }

  public static <T> T notNull(T ob, String message, Object... args) {
    ensure(ob != null, message, args);
    return ob;
  }

  public static String notNullName(Item item) {
    notNull(item, "Item is null");
    return notNull(item.getName(), "Item name is null");
  }

  public static String notNullName(User user) {
    notNull(user, "User is null");
    return notNull(user.getName(), "User name is null");
  }
}
