REACTIVE CHAT WITH REACTOR
==============

Spring Boot application that implements a reactive chat using the
project Reactor.
The GUI is made using the framework Vaadin.

Modules:
========
- Spring Boot
- Vaadin - Java web framework - https://vaadin.com
- Project Reactor

Build the jar:
-------------------------
./gradlew build

Run the jar:
-------------------------
java -jar build/libs/reactor-chat-0.0.1-SNAPSHOT.jar

How to use the application:
-------------------------
Connect to the server via http://localhost:8080 and start sending 
messages.
At least two chat users are needed.

You can see that the application is reactive, when any time one 
user sends a message the other users are automatically updated.
