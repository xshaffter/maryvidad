package com.para_mada.maryvidad.mixins;

import com.para_mada.maryvidad.Maryvidad;
import com.para_mada.maryvidad.hud.AnimatedHudElement;
import com.para_mada.maryvidad.hud.HudElement;
import com.para_mada.maryvidad.items.ItemManager;
import com.para_mada.maryvidad.util.MoneyManager;
import com.para_mada.maryvidad.util.PlayerEntityBridge;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.gui.widget.ClickableWidget.WIDGETS_TEXTURE;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow
    protected abstract PlayerEntity getCameraPlayer();

    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;
    @Shadow
    private int ticks;

    @Shadow
    protected abstract void renderHotbarItem(DrawContext context, int i, int j, float f, PlayerEntity playerEntity, ItemStack itemStack, int k);

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    public abstract TextRenderer getTextRenderer();

    private static final Identifier MB1_TEXTURE = new Identifier(Maryvidad.MOD_ID, "textures/hud/mb1.png");
    private static final Identifier MB2_TEXTURE = new Identifier(Maryvidad.MOD_ID, "textures/hud/mb2.png");
    private static final Identifier MARYCOIN_TEXTURE = new Identifier(Maryvidad.MOD_ID, "textures/hud/coin.png");
    private AnimatedHudElement MB_1;
    private AnimatedHudElement MB_2;
    private HudElement ECONOMY;
    private int lastWidth = 0, lastHeight = 0;

    @Unique
    private void init() {
        int windowWidth = this.scaledWidth;
        int windowHeight = this.scaledHeight;
        int leftX = windowWidth / 2 - 91 - 27;
        int rightX = windowWidth / 2 + 91 + 8;
        if ((MB_1 == null && MB_2 == null) || windowWidth != lastWidth || windowHeight != lastHeight) {
            MoneyManager.requestMoneySync();

            int primaryX = rightX;
            int secondaryX = leftX;

            assert client.player != null;
            if (client.player.getMainArm() == Arm.LEFT) {
                primaryX = leftX;
                secondaryX = rightX;
            }

            MB_1 = new AnimatedHudElement(
                    primaryX,
                    windowHeight - 24 - 16 - 2,
                    MB1_TEXTURE,
                    18,
                    16,
                    18,
                    32,
                    20,
                    1
            );
            MB_2 = new AnimatedHudElement(
                    secondaryX,
                    windowHeight - 24 - 16 - 2,
                    MB2_TEXTURE,
                    18,
                    16,
                    18,
                    32,
                    20,
                    1
            );
        }
        ECONOMY = new HudElement(
                rightX + 18 + 4,
                windowHeight - 24 - 16 - 2,
                MARYCOIN_TEXTURE,
                56,
                56,
                56,
                56
        );
        lastWidth = windowWidth;
        lastHeight = windowHeight;

    }

    @Unique
    private void renderEconomy(DrawContext context) {
        ECONOMY.render(context, 16, 16);
        int balance = PlayerEntityBridge.getMoney(client.player);
        context.drawTextWithShadow(getTextRenderer(), Text.literal(String.valueOf(balance)), ECONOMY.x + 16 + 4, ECONOMY.y + 8 - (getTextRenderer().fontHeight / 2), 0xFFF);
    }

    @Inject(method = "renderHotbar", at = @At("TAIL"))
    private void renderHotbar(float tickDelta, DrawContext context, CallbackInfo ci) {
        init();
        int center = this.scaledWidth / 2;
        int hotbarWidth = 91;
        int hotbarX = center - hotbarWidth;

        PlayerEntity player = this.getCameraPlayer();
        if ((player.getMainHandStack().isOf(ItemManager.GAUNTLET_L) && player.getMainArm() == Arm.LEFT) ||
                (player.getMainHandStack().isOf(ItemManager.GAUNTLET_R) && player.getMainArm() == Arm.RIGHT)) {
            MB_1.render(context, this.ticks);
        }
        if ((player.getOffHandStack().isOf(ItemManager.GAUNTLET_R) && player.getMainArm() == Arm.LEFT) ||
                (player.getOffHandStack().isOf(ItemManager.GAUNTLET_L) && player.getMainArm() == Arm.RIGHT)) {
            MB_2.render(context, this.ticks);
        }

        Arm arm = player.getMainArm().getOpposite();
        if (arm != Arm.LEFT) {
            context.drawTexture(WIDGETS_TEXTURE, hotbarX - 29, this.scaledHeight - 23, 24, 22, 29, 24);
        } else {
            context.drawTexture(WIDGETS_TEXTURE, center + 91, this.scaledHeight - 23, 53, 22, 29, 24);
        }

        var itemStack = player.getInventory().getStack(player.getInventory().selectedSlot);

        if (!itemStack.isEmpty()) {
            int m = this.scaledHeight - 16 - 3;
            if (arm != Arm.LEFT) {
                this.renderHotbarItem(context, center - 91 - 26, m, tickDelta, player, itemStack, 8);
            } else {
                this.renderHotbarItem(context, center + 91 + 10, m, tickDelta, player, itemStack, 8);
            }
        }
        renderEconomy(context);
    }
}
