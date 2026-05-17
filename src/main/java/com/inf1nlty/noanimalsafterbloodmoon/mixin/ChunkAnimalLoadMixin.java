package com.inf1nlty.noanimalsafterbloodmoon.mixin;

import com.inf1nlty.noanimalsafterbloodmoon.control.AnimalSpawnController;
import net.minecraft.Chunk;
import net.minecraft.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chunk.class)
public abstract class ChunkAnimalLoadMixin {

    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    private void noanimalsafterbloodmoon$blockLoadedAnimal(Entity entity, CallbackInfo ci) {
        Chunk chunk = (Chunk)(Object)this;

        if (AnimalSpawnController.shouldBlockAnimalSpawning(chunk.worldObj, entity)) {
            entity.setDead();
            ci.cancel();
        }
    }
}
