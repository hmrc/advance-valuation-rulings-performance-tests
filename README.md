
# advance-valuation-rulings-performance-tests

### Services

Start Mongo Docker container as follows:

```bash
docker run --rm -d --name mongo -d -p 27017:27017 mongo:4.0
```

Start ARS dependant  services as follows:

```bash
sm2 --start ARS_ALL
```

---

### Running the tests

Run smoke test (locally) as follows:

```bash
./run_local_smoke.sh
```

Run full performance test (locally) as follows:

```bash
./run_local.sh
```

### Running the tests on Staging Env

To run the tests on Staging please use the performance test jenkins jobs

---

### Logging

The default log level for all HTTP requests is set to `WARN`. Configure [logback.xml](src/test/resources/logback.xml) to update this if required.

---

### Formatting and dependencies

```bash
./run_format_and_deps.sh
```

---

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
