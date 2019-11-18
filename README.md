<img src="https://raw.githubusercontent.com/coffeenotfound/milkflasks/master/src/main/resources/milkflasks_logo.png"></img>

# Milk Flasks
A little mod that adds flasks of milk which hastily cure one random potion effect. Useful when in a pinch.

### Developing
For Eclipse run `./gradlew eclipse`, then import as an existing Gradle project.

Launch configurations can be generated with `./gradlew genEclipseRuns` and then be
imported with `File > Import... > Run/Debug > Launch Configurations`, but note that running them does
not rebuild the mod.

When editing resources make sure to refresh the project (`The project (Right click) > Refresh`), otherwise
the changes may not be visible in game. This is because when starting from a non-Gradle start in Eclipse
the resources from the `bin` folder are used which gets updated by Eclipse, not Gradle.

### Building
Building is simply done via `./gradlew build`.
