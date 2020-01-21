# Calculator

The app is a simple calculator that provides 4 basic operations: addition, subtraction, multiplication, division.

Only integer numbers are supported.

Calculation results are stored in the database (pgsql) located on the local server.

## Pre-install 

* The application uses Apache Tomcat as an external app server https://tomcat.apache.org/download-90.cgi
* PostgreSQL database needs to be installed https://www.postgresql.org/download/

## Installation

Using git bash

* cd path/to/project/dir
* git clone https://github.com/druespin/Calculator

### Libraries used

* UI based on JavaFX
* JAX-RS for RESTful API implementation
