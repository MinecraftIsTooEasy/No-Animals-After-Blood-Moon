package com.inf1nlty.noanimalsafterbloodmoon.mixin;

import com.inf1nlty.noanimalsafterbloodmoon.control.AnimalSpawnController;
import net.minecraft.BiomeGenBase;
import net.minecraft.EnumCreatureType;
import net.minecraft.SpawnerAnimals;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(SpawnerAnimals.class)
public abstract class SpawnerAnimalsMixin {

    @Inject(method = "isBlueMoonAnimalSpawningPeriod", at = @At("HEAD"), cancellable = true)
    private void noanimalsafterbloodmoon$forceAnimalSpawningBeforeNoAnimalMode(World world, CallbackInfoReturnable<Boolean> cir) {
        if (AnimalSpawnController.shouldForceAnimalSpawningPeriod(world)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "performWorldGenSpawning", at = @At("HEAD"), cancellable = true)
    private static void noanimalsafterbloodmoon$blockWorldGenAnimalSpawning(World world, BiomeGenBase biome, EnumCreatureType creatureType, int minX, int minZ, int rangeX, int rangeZ, Random random, CallbackInfo ci) {
        if (AnimalSpawnController.shouldBlockWorldGenSpawning(world, creatureType)) {
            ci.cancel();
        }
    }
}
