package com.para_mada.maryvidad.items.custom;

import com.para_mada.maryvidad.items.ItemManager;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BoxGauntlets extends AxeItem {
    public BoxGauntlets(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        boolean leftDominantSetup = (user.getMainHandStack().isOf(ItemManager.GAUNTLET_L) && user.getOffHandStack().isOf(ItemManager.GAUNTLET_R));
        boolean rightDominantSetup = (user.getMainHandStack().isOf(ItemManager.GAUNTLET_R) && user.getOffHandStack().isOf(ItemManager.GAUNTLET_L));
        if (!(leftDominantSetup && user.getMainArm() == Arm.LEFT) && !(rightDominantSetup && user.getMainArm() == Arm.RIGHT)) {
            user.sendMessage(Text.literal("Equipate tus dos guantes correctamente"));
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
        user.swingHand(Hand.OFF_HAND);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        return true;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        boolean leftDominantSetup = (user.getMainHandStack().isOf(ItemManager.GAUNTLET_L) && user.getOffHandStack().isOf(ItemManager.GAUNTLET_R));
        boolean rightDominantSetup = (user.getMainHandStack().isOf(ItemManager.GAUNTLET_R) && user.getOffHandStack().isOf(ItemManager.GAUNTLET_L));
        if (!(leftDominantSetup && user.getMainArm() == Arm.LEFT) && !(rightDominantSetup && user.getMainArm() == Arm.RIGHT)) {
            user.sendMessage(Text.literal("Equipate tus dos guantes correctamente"));
            return ActionResult.FAIL;
        }
        float cd = user.getAttackCooldownProgress(0.5f);
        boolean fullCD = cd > 0.9f;

        double damage = user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 0.5;

        boolean damageDone = entity.damage(user.getDamageSources().playerAttack(user), (float) damage);
        if (damageDone) {
            double knockback = 1;
            entity.takeKnockback(knockback, MathHelper.sin(user.getYaw() * ((float) Math.PI / 180)), -MathHelper.cos(user.getYaw() * ((float) Math.PI / 180)));
            user.setVelocity(user.getVelocity().multiply(0.6, 1.0, 0.6));
            user.setSprinting(false);
        }
        if (fullCD) {
            user.getWorld().playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, user.getSoundCategory(), 1.0f, 1.0f);
        } else {
            user.getWorld().playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, user.getSoundCategory(), 1.0f, 1.0f);
        }
        user.swingHand(Hand.OFF_HAND);
        return ActionResult.PASS;
    }
}
