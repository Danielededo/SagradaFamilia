Legend:
From Client to Server ->
From Server to Client <-


start_server()


->
lookup()
let the client know who to talk with


->
addObserver()
returns a boolean: if false the client has not been added to the list of future players
this method calls:

<-
loginconnection(Client o)
here we can check:
if the client can be added to the match that will start as soon as there are enough players;
if the client chose a nickname already taken by another player;
if there are no problems, the client is added to the players' list, 
which is saved in the "waiting room", an attribute class the server possess, with the method

addPlayer(String player)
this method starts the main timer when there are at least two clients successfully connected

attesa_partita()
this method calls

control()
this method see that every client is still connected when it's time to start the match, thanks to

->
setupPlayer
a method that askes the client to input a string so to know they are still there

<-
setupconnection
returns to the server the client's nickname


update(String msg)
prints the string, msg comes from the server thanks to

<-
notify(Client o, String arg)
arg messages are sent only to the client o
<-
notifyOthers(Client o, String arg)
arg messages are sent to every client except for o
<-
notifyObserver(String arg)
arg messages are sent to every client successfully added to le list of future players


<-
setMatch(Match match)
this method gives out scheme cards and private objective cards to every future player

thanks to
->
setupgame()
the client can choose a scheme card
