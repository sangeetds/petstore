### Petstore

You can clone the project and run it from an IDE. 
If you want to run it through `gradle` then you can add these lines to your `build.gradle` file:
```
plugins {
  ...
  id 'application'
}

application {
    mainClass = 'com.petstore.ApplicationKt'
}
```
You can then run `./gradlew run` command to start the application
