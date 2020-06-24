# AB test task

Install Gradle and run the following command from the root directory:

```bash
$ ./gradlew build
```

Run tests and generate the report:

```bash
$ ./gradlew allureReport
```
The report will be generated to **build/reports/allure-report** folder.

To serve report run:

```bash
$ cd build && allure serve
```

