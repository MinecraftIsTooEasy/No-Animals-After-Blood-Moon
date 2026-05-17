package com.inf1nlty.noanimalsafterbloodmoon.mixin;

import com.inf1nlty.noanimalsafterbloodmoon.control.AnimalSpawnController;
import net.minecraft.Entity;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldAnimalSpawnMixin {

    @Inject(method = "spawnEntityInWorld", at = @At("HEAD"), cancellable = true)
    private void noanimalsafterbloodmoon$blockAnimalSpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (AnimalSpawnController.shouldBlockAnimalSpawning((World)(Object)this, entity)) {
            entity.setDead();
            cir.setReturnValue(false);
        }
    }
}
