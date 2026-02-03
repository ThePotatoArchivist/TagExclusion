# Tag Exclusion

A Minecraft mod library & utility to allow removing entries from tags.

## Syntax

There is now an abbreivated syntax for tag entries. You can add a `?` at the end of an entry to mark it as optional and 
a `!` at the beginning of an entry to mark it as an exclusion entry.

Exclusion entries will be removed from the tag, including from previous entries in the same tag file and from entries
in lower packs.

Example:

```json5
{
  "values": [
    
    "#minecraft:doors",
    // Add all doors
    "!minecraft:spruce_door",
    // Except the spruce door
    
    "#minecraft:logs",
    // Add all logs
    "!#minecraft:logs_that_burn",
    // But not the ones that burn
    
    "create:brass_casing?",
    // Add create brass casing, if it's installed
    
    "!#enderscape:veiled_logs?"
    // Remove enderscape veiled logs, if it's installed
    
  ]
}
```

The standard expanded format is also extended.

```json
{
  "values": [
    "#minecraft:logs",
    {
      "id": "#minecraft:logs_that_burn",
      "tagex:exclude": true
    }
  ]
}
```

## Depending on Tag Exclusion

Currently, it is recommended to use JitPack to add the mod in Gradle and to jar-in-jar it.

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    modImplementation include("com.github.ThePotatoArchivist:TagExclusion:${project.tagex_version}")
}
```

Note: For some reason, JitPack cannot have `+` in version names, so when specifying the version in Gradle you must use
`-` instead, e.g. `1.0.0-mc1.21.11`. The version in the mod metadata still uses `+`, only the maven version uses `-`.

## Data Generation

To add tag exclusions in data generation, use the extension methods to `TagAppender`.

```java
valueLookupBuilder(ItemTags.ANVIL)
        .tagex_exclude(Items.CHIPPED_ANVIL);

valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
        .tagex_forceExcludeTag(ItemTags.DARK_OAK_LOGS);
```

If you're using Kotlin, there are also extension functions that don't have `tagex_` in the name.

```kotlin
with (valueLookupBuilder(ItemTags.ANVIL)) {
    exclude(Items.CHIPPED_ANVIL)
}
```

To avoid messing with datagen when it's not supposed to, datagen will only produce tag entries in abbreviated form if
the generating mod has specified its mod id for datagen and declares a hard dependency on `tagex`.

In `build.gradle`:

```groovy
fabricApi {
    configureDataGeneration {
        // ...
        modId = "testmod"
    }
}
```

You may need to regenerate your IDE run configurations.

In `fabric.mod.json`:

```json5
{
  // ...
  "depends": {
    // ...
    "tagex": "^1.0.0"
  }
}
```