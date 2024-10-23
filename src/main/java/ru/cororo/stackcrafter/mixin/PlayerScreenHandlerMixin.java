package ru.cororo.stackcrafter.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PlayerScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.cororo.stackcrafter.StackCrafterMod;

@Mixin(PlayerScreenHandler.class)
public class PlayerScreenHandlerMixin {
    @Inject(method = "onContentChanged", at = @At("RETURN"))
    private void onContentChanged(Inventory inventory, CallbackInfo ci) {
        if (Screen.hasShiftDown()) {
            StackCrafterMod.startCraft();
        }
    }
}
