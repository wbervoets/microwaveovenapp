# Microwave Oven app

This microwave Oven app simulates a Microwave oven. It consists of a door, heater, lightbulb and count down timer

## Technologies

This is a native Android app using the following technologies:

* JetPack Compose UI + Material Components for Compose
* AndroidX Viewmodel
* Kotlin 1.8 / Kotlin Flows
* Timber (logging)
* JUnit5
* JUnit4
* Mockk (Kotlin Mocking framework)
* Turbine (testing Flows)

It includes unit tests written with JUnit5 and UI Tests for the Compose UI composables using a JUnit4 runner.

## Build

```
gradlew assemble
```

## Run Unit Tests

```
gradlew test
```
## Run UI Tests

```
gradlew connectedAndroidTest
```
