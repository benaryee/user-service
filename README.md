# user-service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

A postman documentation has been published [here](https://documenter.getpostman.com/view/6029718/2s9XxwxEM3)
This project is also available on GitHub [here](https://github.com/benaryee/user-service.git).

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.
