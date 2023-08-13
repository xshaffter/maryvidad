package com.para_mada.maryvidad.blocks.custom;

import com.para_mada.maryvidad.blocks.bases.RotableBlockWithEntity;
import com.para_mada.maryvidad.blocks.blockstates.InfiniteInventory;
import com.para_mada.maryvidad.blocks.entities.ShopStandBlockEntity;
import com.para_mada.maryvidad.util.PlayerEntityBridge;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.para_mada.maryvidad.blocks.blockstates.PropertyManager.INFINITE_INVENTORY;

public class ShopBlock extends RotableBlockWithEntity {

    public ShopBlock() {
        super(
                FabricBlockSettings.create()
                        .collidable(true)
                        .hardness(1f)
                        .strength(4f)
                        .nonOpaque(),
                VoxelShapes.fullCube()
        );
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ShopStandBlockEntity(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.FAIL;
        }

        if (hand == Hand.OFF_HAND) {
            return ActionResult.FAIL;
        }

        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

        if (player.isCreative()) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
            return ActionResult.SUCCESS;
        }

        ShopStandBlockEntity entity = (ShopStandBlockEntity) world.getBlockEntity(pos);

        int slot = Objects.requireNonNull(entity).firstNonEmpty();

        if (slot == -1) {
            player.sendMessage(Text.translatable("shop_stand.status.empty"));
            player.swingHand(hand);
            return ActionResult.FAIL;
        }

        ItemStack stack = entity.getStack(slot);

        int balance = PlayerEntityBridge.getMoney(player);
        int price = entity.getPrice(slot);

        if (balance < price) {
            player.sendMessage(Text.translatable("shop_stand.status.no_enough_money"));
            player.swingHand(hand);
            return ActionResult.FAIL;
        }

        PlayerEntityBridge.spendMoney(serverPlayer, price);
        player.giveItemStack(stack);

        if (state.get(INFINITE_INVENTORY).equals(InfiniteInventory.NO)) {
            entity.setStack(slot, ItemStack.EMPTY);
        }


        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(INFINITE_INVENTORY));
    }
}
