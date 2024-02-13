# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared tests`     | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

### Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```

[Server Design UML Diagram](https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWOZVYSnfoccKQCLAwwAIIgQKAM4TMAE0HAARsAkoYMhZkwBzKBACu2GAGI0wKgE8YAJRRakEsFEFIIaYwHcAFkjAdEqUgBaAD4WakoALhgAbQAFAHkyABUAXRgAej0VKAAdNABvLMpTAFsUABoYXCl3aBlKlBLgJAQAX0wKcNgQsLZxKKhbe18oAAoiqFKKquUJWqh6mEbmhABKDtZ2GB6BIVFxKSitFDAAVWzx7Kn13ZExSQlt0PUosgBRABk3uCSYCamYAAzXQlP7ZTC3fYPbY9Tp9FBRNB6BAIDbULY7eRQw4wECDQQoc6US7FYBlSrVOZ1G5Y+5SJ5qBRRACSADl3lZfv8ydNKfNFssWjA2Ul4mDaHCMaFIXSJFE8SgCcI9GBPCTJjyaXtZQyXsL2W9OeKNeSYMAVZ4khAANbofWis0WiG0g6PQKwzb9R2qq22tBo+Ew0JwyLey029AByhB+DIdBgKIAJgADMm8vlzT6I2h2ugZJodPpDEZoLxjjAPhA7G4jF4fH440Fg6xQ3FEqkMiopC40Onuaa+XV2iHus30V7K9X1VMKbN+etJeIGTLXUcTkSxv2UFq7q7dUzyJ9vlyrjygSDjc7tXv3WP4VFM5544QCRvpzzZzVqQGpTAVw95XxXxlVVd8yh3bE3WeA82Q5E9SVNR9fTtEUxUfK9d2hW9enYB8LWQ-1F1UbCRzwrM-Sjboen8eMk1TdMkOzXM0HzbRdAMYwdBQO1J30Zha28XxMBoptelbPgjySN40nSLsJB7PJGIokcGSIqJeJVUYlPQBdPWI6UXQAisqz4rT8OzCCdR6PUJK+KSwwI88IFBdD-3pEi9KiCQ9FlH8lw8rovJ811KJjESExgFNk0wPMC3Y4tBhkCthhgABxHlHgE+thMbZgPUCmJUreDt0i0HlFPM5SW1HHCJ2GdKygkMzyJ0vz9L-QycQ+eqMua8M-Usm9oNeI8fgc7MnJcp03LdfL7zDZ8QAJPqCMGrDhsPL4xrKspuocSaYB2jgZpjNTDp5Zk+EqOtfA3GcYAUBBQGtO6P3OspWR5NrTuqo4LqumAbsJU9TUe57XtNI7PrKULsPCujkxgPIYHyI7LuuwTgYQ6YwZAF6QemKGeWY1jCw4oxsD0KBsAQVQ4CA1QGtULKhPC1TfpiBJkhko6Kpa3sCiJsp2jZuaJWq9nxwRXEGaZlbs0qIXt2+zFryMhUCTl7S0EVnloeVk7rIPd4tt+bWDtczrZrvXCFowF9fHlgaMMg-cWQNI0lftMUjpdqy5tttG+BVsWoD+spLth6jcoR9Mg5JuKi2McwUBRCB3BgAApCAkDcJnjFx60coCPK71bWJThK3nTEq9B03CuAIAQaBdYjvhhwlgP+gAKxztA5ZAJuW-elBLt0qXlytqJe9zgeh6gVvR74Nb3I2k3j3Gv0Lemq2fqlsin3tpbHe1leoMZd24NxefKgmGBc8gEe-ZvLvpe83yiL3mgw5gd+QpUuGMdIqphiixRO5MdDAEsIgRUsBgDYBpg7HsHhMbF3jJLb+URoi2WKtJDI6hNAAJtv0EAtM8B8AAEKjHHoGVWmEcSkNgZQ6hz91oXz-JJN4MAABU29VSsNXuwnB9leHAmcpeQ2G1hFcNEReX2htX5BQ-npL+oY-4PCjqEeGwDoqxSAA)