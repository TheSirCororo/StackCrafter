package ru.cororo.stackcrafter

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.item.Items
import net.minecraft.screen.AbstractRecipeScreenHandler
import net.minecraft.screen.CraftingScreenHandler
import net.minecraft.screen.PlayerScreenHandler
import net.minecraft.screen.slot.SlotActionType

object StackCrafterMod : ModInitializer {
    override fun onInitialize() {
        init()
    }

    private var pendingCraft = false

    private fun init() {
        ClientTickEvents.START_CLIENT_TICK.register {
            if (pendingCraft) {
                pendingCraft = false
                startCraft()
            }
        }
    }

    @JvmStatic
    fun startCraft() {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return
        val interactionManager = client.interactionManager ?: return
        val recipeScreenHandler = player.currentScreenHandler.takeIf {
            it is CraftingScreenHandler || it is PlayerScreenHandler
        } as? AbstractRecipeScreenHandler<*> ?: return

        val resultSlotIndex = recipeScreenHandler.craftingResultSlotIndex
        val outStack = recipeScreenHandler.slots[resultSlotIndex].stack
        if (outStack.item != Items.AIR) {
            interactionManager.clickSlot(
                recipeScreenHandler.syncId,
                resultSlotIndex,
                0,
                SlotActionType.QUICK_MOVE,
                player
            )
        }
    }
}
