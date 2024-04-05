
# advance-valuation-rulings-performance-tests

## Starting Services

To start the required services via [service manager](https://github.com/hmrc/sm2), run:

```shell
sm2 --start ARS_ALL
```

## Running Tests

### Local

To run smoke performance tests locally, execute the script:

```bash
./run_local_smoke.sh
```

To run full performance tests locally, execute the script:

```bash
./run_local.sh
```

### Staging

To run smoke performance tests on Staging, execute the script:

```bash
./run_staging_smoke.sh
```

To run full performance tests on Staging, execute the script:

```bash
./run_staging.sh
```

These tests can also be executed on Staging via the performance test jenkins job.

## Formatting and dependencies

```bash
./run_format_and_deps.sh
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
