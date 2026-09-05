package com.spectrasonic.VoidGen.Gen;

import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import com.spectrasonic.VoidGen.Main;
import com.spectrasonic.VoidGen.Config.ConfigManager;
import com.spectrasonic.VoidGen.Provider.VoidBiomeProvider;

public class VoidChunkGenerator extends ChunkGenerator {

    private final ConfigManager configManager;

    public VoidChunkGenerator(Main plugin) {
        this.configManager = ConfigManager.getInstance(plugin);
    }

    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return List.of();
    }

    @Override
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ,
            @NotNull ChunkData chunkData) {
        // No need to generate noise, its a empty world
    }

    @Override
    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ,
            @NotNull ChunkData chunkData) {
        // No need to generate surface, its a empty world
    }

    @Override
    public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ,
            @NotNull ChunkData chunkData) {
        int radius = configManager.getPlatformRadius();
        int platformY = configManager.getPlatformY();

        int r2 = radius * radius;

        // World-coordinate range covered by this chunk
        int worldMinX = chunkX * 16;
        int worldMaxX = worldMinX + 15;
        int worldMinZ = chunkZ * 16;
        int worldMaxZ = worldMinZ + 15;

        // Check whether this chunk overlaps with the disk of radius `radius` centered at (0,0)
        boolean touchesCircle = false;
        for (int wx = worldMinX; wx <= worldMaxX && !touchesCircle; wx++) {
            for (int wz = worldMinZ; wz <= worldMaxZ; wz++) {
                if (wx * wx + wz * wz <= r2) {
                    touchesCircle = true;
                    break;
                }
            }
        }

        // Check whether the world origin (0,0) lies within this chunk (for the bedrock center)
        boolean touchesCenter = worldMinX <= 0 && worldMaxX >= 0
                && worldMinZ <= 0 && worldMaxZ >= 0;

        if (!touchesCircle && !touchesCenter) {
            return;
        }

        // First: fill the disk with GRASS_BLOCK. ChunkData uses chunk-local coords (0-15).
        for (int wx = worldMinX; wx <= worldMaxX; wx++) {
            for (int wz = worldMinZ; wz <= worldMaxZ; wz++) {
                if (wx * wx + wz * wz <= r2) {
                    chunkData.setBlock(wx - worldMinX, platformY, wz - worldMinZ, Material.GRASS_BLOCK);
                }
            }
        }

        // Then: overwrite the center block with bedrock
        if (touchesCenter) {
            chunkData.setBlock(-worldMinX, platformY, -worldMinZ, Material.BEDROCK);
        }
    }

    @Override
    public void generateCaves(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ,
            @NotNull ChunkData chunkData) {
        // No need to generate caves, its a empty world
    }

    @Override
    public BiomeProvider getDefaultBiomeProvider(@NotNull WorldInfo worldInfo) {
        return new VoidBiomeProvider();
    }

    @Override
    public boolean canSpawn(@NotNull World world, int x, int z) {
        return true;
    }

    @Override
    public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
        return configManager.getFirstSpawnLocation(world);
    }
}