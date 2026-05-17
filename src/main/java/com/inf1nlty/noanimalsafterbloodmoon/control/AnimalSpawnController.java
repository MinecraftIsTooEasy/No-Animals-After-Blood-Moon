package com.inf1nlty.noanimalsafterbloodmoon.control;

import com.inf1nlty.noanimalsafterbloodmoon.NoAnimalsAfterBloodMoonConfigs;
import com.inf1nlty.noanimalsafterbloodmoon.NoAnimalTriggerMode;
import net.minecraft.Entity;
import net.minecraft.EntityAnimal;
import net.minecraft.EntityHorse;
import net.minecraft.EntityTameable;
import net.minecraft.EntityWaterMob;
import net.minecraft.EnumCreatureType;
import net.minecraft.IMob;
import net.minecraft.World;
import net.minecraft.WorldServer;

import java.util.ArrayList;

public final class AnimalSpawnController {

    public static void onOverworldServerTick(WorldServer world) {
        if (world == null || !world.isOverworld() || !NoAnimalsAfterBloodMoonConfigs.isAnimalControlEnabled()) {
            return;
        }

        updateNoAnimalMode(world);

        if (AnimalControlState.isNoAnimalMode()) {
            if (NoAnimalsAfterBloodMoonConfigs.clearAnimalsWhenNoAnimalModeStarts.getBooleanValue()) {
                clearLoadedAnimals(world);
            }
        }
    }

    public static boolean shouldBlockAnimalSpawning(World world, Entity entity) {
        if (!NoAnimalsAfterBloodMoonConfigs.isAnimalControlEnabled()
            || !NoAnimalsAfterBloodMoonConfigs.shouldBlockAnimalSpawningInNoAnimalMode()
            || !AnimalControlState.isNoAnimalMode()) {
            return false;
        }

        return isOverworld(world) && isControlledAnimal(entity);
    }

    public static boolean shouldBlockWorldGenSpawning(World world, EnumCreatureType creatureType) {
        return NoAnimalsAfterBloodMoonConfigs.isAnimalControlEnabled()
            && NoAnimalsAfterBloodMoonConfigs.shouldBlockAnimalSpawningInNoAnimalMode()
            && AnimalControlState.isNoAnimalMode()
            && isOverworld(world)
            && creatureType == EnumCreatureType.animal;
    }

    public static boolean shouldForceAnimalSpawningPeriod(World world) {
        return NoAnimalsAfterBloodMoonConfigs.isAnimalControlEnabled()
            && NoAnimalsAfterBloodMoonConfigs.spawnBeforeFirstBloodMoon.getBooleanValue()
            && !AnimalControlState.isNoAnimalMode()
            && isOverworld(world);
    }

    private static void updateNoAnimalMode(WorldServer world) {
        boolean shouldStart = false;

        if (NoAnimalsAfterBloodMoonConfigs.noAnimalTriggerMode.getEnumValue() == NoAnimalTriggerMode.DAY) {
            shouldStart = world.getDayOfWorld() >= NoAnimalsAfterBloodMoonConfigs.noAnimalStartDay.getIntegerValue();
        } else if (world.isBloodMoon(false)) {
            shouldStart = true;
            if (AnimalControlState.getFirstBloodMoonDay() < 0) {
                AnimalControlState.setFirstBloodMoonDay(world.getDayOfWorld());
            }
        }

        if (shouldStart && !AnimalControlState.isNoAnimalMode()) {
            AnimalControlState.setNoAnimalMode(true);
            world.getSaveHandler().saveWorldInfo(world.getWorldInfo());
        }
    }

    @SuppressWarnings("unchecked, rawtypes")
    private static void clearLoadedAnimals(WorldServer world) {
        ArrayList toRemove = new ArrayList();

        for (Object object : world.loadedEntityList) {
            if (object instanceof Entity entity && isControlledAnimal(entity)) {
                toRemove.add(entity);
            }
        }

        for (Object object : toRemove) {
            world.removeEntity((Entity)object);
        }
    }

    private static boolean isOverworld(World world) {
        return world != null && world.isOverworld();
    }

    private static boolean isControlledAnimal(Entity entity) {
        if (entity == null || entity instanceof IMob) {
            return false;
        }

        if (!(entity instanceof EntityAnimal) && !(entity instanceof EntityWaterMob)) {
            return false;
        }

        return !NoAnimalsAfterBloodMoonConfigs.shouldKeepTamedAnimals() || !isTamed(entity);
    }

    private static boolean isTamed(Entity entity) {
        if (entity instanceof EntityTameable tameable) {
            return tameable.isTamed();
        }

        return entity instanceof EntityHorse horse && horse.isTame();
    }
}
