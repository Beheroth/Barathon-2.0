# TDD and CI in Java
Barathon was originally designed by a Linux users team. Some details may change on other operating systems. This file can be useful to continue the project.
It contains yet the maven architecture.

## Read Me
This `README.md` is the official information file for the comprehenssion of this project.

You can find differents files generated during the developpement process. We keep them so we can find our reflexion during the developpement process.

## "Useless" parts
Two projects were bound: the TDD and the NoSQL projects. Some classes/files are useless now. The `DBAccess` and `DB` classes are no more really usefull. The DBAccess contains all the methods to access to the neo4j database (connect, createCaracteristics, ...). The DB contains the methods to create the database and the querries needed for the NoSQL project.
In the directory rapport2, you may be find some answers to your questions in the NoSQL report.

The `DB.java` is not a part of the project. We kept the file because it's an usefull works to generate the database and to learn some queries for the search strategies. The `DB.java` must not be considered for the metrics and the codage convention of the project.

## User manual
In this section, there are usefull informations for the user on how to install and how to use this project.

### How to install
Some `.jar`are needed to compile and to execute the project. They are automatically loaded with maven. Do not forget to install and to include them in the classpath to run the application on your computer.
These are the following library needed:
* (old) JSON: a library to read or write JSON files ;
* GSON: a high-level library to read or write JSON files ;
* Apache Commons-IO: a library to proceed operations on files ;
* Junit: a test module ;
* Neo4j: a library to access Neo4j, a database working with graphs.

You need to run a neo4j server on your computer to run the application and set the variables to connect to your databse in the `DBAcces.java` file:
```
private static String ip = "localhost:7687";
private static String login = "neo4j";
private static String password = "NEO4J";
```

### How to use
After compiled the project, you must run `App.class`.

The app is currently a CLI (command line interface). At the beginning, the CLI prompts you to select your preferences and your pseudo, then it displays the appropriate list of bars.
 *(this methodes needs to be improved)*.

The application will show you a list of differents places which correspond to your wishes *(this method needs to be implemented)*.

---

When a user runs the app on his smartphone and the database (users, bars,carcateristics and relation) is on a server away. Two actions are possible:
* He chooses a pseudo and answers the questions to get his caracteristics.

The pseudo needs to be unique. With the position of the device, the user's position is updated in the database and the property distance of the AWAY relation too.

* He logs in to his account and can use the application to find his trip.

There can be two kinds of trips according to his preferences: "Trip" gives him a real bar trip whereas "Bars" gives him a selection of bars he can choose among.


## Metrics
We use the codage convention of sun without the line length convention.

The metrics of the code are the followed:
* the comment density between 30% and 40%.
* 4the tests covers 40% of the code.
* the execution of the serch is lower than 30 sec.

## How it works
### Use
We run the application on a smartphone. We connect to the user in the neo4j database through the pseudo.

the user set the desire caracteristics.
We search bars who match the caracteristics through a query in the neo4j database.

We search informations about the bars through the json database.

We show all the selected bars with their information to the user.

### classes
The application is composed of 15 classes:

#### `DBAccess` and `JSONAccess`
These classes are used to connect to the differents DB (neo4j, json).

The `DBAcces` class has variables to connect to your neo4j database (There is information above).

#### `App`
This class is the acces's point of the program for the user.

The class will generate the user with his caracteristics and will run the research. We can develop the project by mofifying the differents requests made by the CLI.

#### `Place`
This class represents the bars. The place is link with the `Menu`, the `Address` and the `Caracteristics`. The places are identified by a id. The application will search the places in the database by his id.

#### `Menu`
This class is composed of food and drink.

#### `Food`, `Drink` and `Component`
The class `Food` and class `Drink` are completing the class `Component` to have an homogeneity. The food and the drink have similarities:
 * a name
 * a price
 * multiples methods to manipulate the objects.

#### `Preferences` and `Caracteristics`
The class `Preferences` use the class `Caracteristics`. The bars have caracteristics and the user has a preference. The preferences of the user is composed of caracteristic, it helps to match bars following them caracteristics.

The choice of the user for a trip or not will appear in the preferences but will not help to see if the bar match with the wanted caracteristics.

#### `Address` and `Position`
The class `Address` us the class `Position`. The bars have an address and the user has a position. The address of the bars is composed of a position, it helps to match the bars following their geographical position.

For the bars, we need more information to display than the latitude and the longitude of their position.

#### `Strategy`, `StrategyBars` and `StrategyTrips`
We have here a design pattern stratgegy. We implement different search following the choice of the user. It's a good idea to encapsulate this and use a design pattern strategy.

The class `StrategyBars` is used to finds bars around and near a point depending on a radius given by the user.

The class `StrategyTrips` is used to finds differents across a place to travel and discover the city.

## To Do
The project is not finished. There is improvement to make and another functions to implements.
This is a non exhaustive list of modifications possible:
 * create a CLI for a barman to create a bar in our system (manipulate food, drinks, place);
 * add choice in the user CLI;
 * develop the search strategies (we can find example in the `DB.java`).

We would like to respect the codage convention explained upper. We didn't have enough time to have a project respecting this conventions.
