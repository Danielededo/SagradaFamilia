Legend:
From Client to Server ->
From Server to Client <-


start_server()


->
lookup()
let the client know who to talk with


->
addObserver()
let the client join the game
returns a boolean: if false the client has not been added to the list of future players

<-
if addObserver is true
setupconnection()
returns to the server the client's nickname

<-
ping()
client verify every second if server is up

->
connection_verify()
server verify every second if client is up

<-
notify(Client o, String arg)
arg messages are sent only to the client o by calling method update in client

<-
notifyOthers(Client o, String arg)
arg messages are sent to every client except for o by calling update in client

<-
notifyObserver(String arg)
arg messages are sent to every client successfully added to le listofObserver

->
update(String msg)
update client interface whit msg

->
selectionint()
let the client return to server what it wants

