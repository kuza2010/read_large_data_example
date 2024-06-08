# Example of processing large amount of data from the datasource

Usually, developers work with high abstractions that exposes Spring for us. Developers forget about underlying
technologies and could have problems in unusual cases. One of the cases - reading/processing large data sets. Here I
demonstrate something for you. The solution is based on the `ResultSet` abstraction.

## Wrong ways to process large data set

- use pagination with `LIMIT` and `OFFSET`

## Run project

This is elementary:

First you need to set up local postgres instance. To do that:

1) `cd docker`
2) `docker compose up`

Then we are going to run the app:

1) `./mvnw clean install`
2) `./mvwn spring-boot:run`
