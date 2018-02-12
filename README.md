# Installation

To run this example you need the following software installed:

- MongoDB 3.0
- Drill 1.12

First start the MongoDB server:

```
"<path to MongoDB>/Server/3.0/bin/mongod.exe"
```

Then start a Drill Connection

```
<path to Drill>/bin/sqlline -u "jdbc:drill:zk=local"
```

Your MongoDB installation must have a version of the TunedZip.json file imported in the database example.tuned_zips.
