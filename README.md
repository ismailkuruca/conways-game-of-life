# conways-game-of-life
A simple implementation of the famous Conway's Game of Life

This projects consists of an API module which is written with Spring Boot Web Starter and a very crude jQuery based frontend module to consume and visualize the backend REST APIs.

You can just run it with __mvn spring-boot:run__ and access the frontend with __http://localhost:8080/__
Click New button to generate 10x10 board (or with special dimensions if you specify the width and height properies beforehand)
Click Next to generate next state _or_ click Play to continously generate next states.
