/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2021, Yusuf Ismail
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 *
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.github.realyusufismail.bentenmod.common.events;

import io.github.realyusufismail.bentenmod.core.init.ItemInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;

public class HeatblastArmor extends ArmorItem implements IForgeItem {
    public final Abilities abilities = new Abilities();

    public HeatblastArmor(ArmorMaterial materialIn, EquipmentSlot slot, Item.Properties builder) {
        super(materialIn, slot, builder);

    }


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);

        if (boots.getItem() == ItemInit.HEATBLAST_BOOTS.get()
                && legs.getItem() == ItemInit.HEATBLAST_LEGGINGS.get()
                && chest.getItem() == ItemInit.HEATBLAST_CHESTPLATE.get()
                && head.getItem() == ItemInit.HEATBLAST_HELMET.get()) {
            player.addEffect(
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 3, false, false, true));
            player.addEffect(
                    new MobEffectInstance(MobEffects.REGENERATION, 100, 3, false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 3, false,
                    false, true));
            player.addEffect(
                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1, false, false, true));
            player.getAbilities().mayfly = true;
            player.fallDistance = 0.0F;
            player.getAbilities().setFlyingSpeed(0.1F);
        }


    }
}

