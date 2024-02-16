/*
 * Copyright 2023 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.bentenmod.entity;

import io.github.realyusufismail.bentenmod.core.init.EntityTypesInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class KraabBoltEntity extends AbstractArrowEntity {

    private static final int MAX_DURATION = 40;

    private int duration;

    public KraabBoltEntity(EntityType<? extends KraabBoltEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true);
        this.setBaseDamage(6);
    }

    public KraabBoltEntity(double x, double y, double z, World world, Entity owner) {
        super(EntityTypesInit.KRAAB_BOLT_ENTITY.get(), x, y, z, world);
        this.setNoGravity(true);
        this.setBaseDamage(6);
        this.setOwner(owner);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide && duration++ > MAX_DURATION) {
            remove();
        }

        if (level.isClientSide) {
            for (int i = 0; i < 10; i++)
                level.addParticle(
                        new RedstoneParticleData(1, 1, 0, 1), getRandomX(1), getRandomY(), getRandomZ(1), 0, 0, 0);
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        if (!level.isClientSide) remove();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        if (!level.isClientSide) {
            Entity target = result.getEntity();
            Entity shooter = getOwner();
            target.hurt(DamageSource.arrow(this, shooter == null ? this : shooter), (float) getBaseDamage());
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}