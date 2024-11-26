# VoidWorldGen

A simple minecraft plugin to generate a void world.

Compatible with Paper, Spigot and Folia from **1.19** to **1.21.3**

It can be used to prevent world generation outside of the world border. To prevent that fully generate your world with a plugin as Chunky then add this plugin & configure your bukkit.yml.

## Instalation

Place the .jar in `plugins/` , and then reload the server

## Usage

bukkit.yml

```yml
worlds:
    world:
        generator: VoidWorldGen
```

If your world is not called `world`, replace with your world name. You can set VoidWorldGen as generator for the other worlds.

---
