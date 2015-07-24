auction-system
==============
A simple auction system implementation, provides a bid tracker interface and concrete implementation with the following functionality:

* Record a user's bid on an item
* Get the current winning bid for an item
* Get all the bids for an item
* Get all the items on which a user has bid

The project includes an API, implementation, unit tests and a
barebones web frontend.

Run the web frontend (e.g. in linux) with the following commands:

```
mvn clean install
java -jar webapp/target/webapp-1.0.0-SNAPSHOT.jar
```

then browse to http://localhost:8080

For the known issues, please check the github issue tracker at https://github.com/ilCatania/auction-system/issues