# Example of processing large amount of data from the datasource

Usually, developers work with high abstractions that exposes Spring for us. Developers forget about underlying
technologies and could have problems in unusual cases. One of the cases - reading/processing large data sets. Here I
demonstrate something for you. The solution is based on the `ResultSet` abstraction.

## Wrong ways to process large data set

- use pagination with `LIMIT` and `OFFSET` due to performance issue

## Run project

This is elementary:

First you need to set up local postgres instance. To do that:

1) `cd docker`
2) `docker compose up`

Then we are going to run the app:

1) `./mvnw clean install`
2) `./mvwn spring-boot:run`

The app available now: localhost:8082.

## Using

1) Send `GET` request by `/data/all` path - it will trigger full read (`select *`). This will cause OutOfMemory
   exception.
2) Then send `GET` request by `/data/all/batch` path - it will trigger same query, but process the data with 100_000
   chunk size. No OutOfMemory expected here.

## ⚠ From author ⚠

To make `ResultSet` fetch data set in portions, we need:

- create statement with `ResultSet.TYPE_FORWARD_ONLY` type (by default)
- set `automommit` for `ResultSet` to `false` value

Otherwise, postgre driver fetches all data at once.
___
To calculate how many times jdbc driver trip to the database you need to divide **total rows** on your **fetch size**
and round up. In my example calculation will be: `1_000_000 / 100_000 = 10` trips.
___
Try to play with different settings of `Connection` and `Statement` abstractions, you will see difference in
data set processing in logs.

## Ref

Postgre sql driver, issuing a query - [link](https://jdbc.postgresql.org/documentation/query/)