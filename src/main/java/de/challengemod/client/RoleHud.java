package de.challengemod.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

import java.util.ArrayList;
import java.util.List;

/** Kleine, dauerhafte HUD-Anzeige der eigenen Rollen (oben links). */
public final class RoleHud {

    private static final float SCALE = 0.75f;
    private static final int COLOR = 0xFFFF5555; // hellrot

    private static volatile boolean blind;
    private static volatile boolean mute;
    private static volatile boolean deaf;

    private RoleHud() {
    }

    public static void register() {
        HudRenderCallback.EVENT.register(RoleHud::render);
    }

    public static void set(boolean newBlind, boolean newMute, boolean newDeaf) {
        blind = newBlind;
        mute = newMute;
        deaf = newDeaf;
    }

    public static void reset() {
        set(false, false, false);
    }

    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) {
            return;
        }

        List<String> roles = new ArrayList<>();
        if (blind) roles.add("Blind");
        if (mute) roles.add("Mute");
        if (deaf) roles.add("Deaf");
        if (roles.isEmpty()) {
            return;
        }

        String label = "Rolle: " + String.join(", ", roles);
        TextRenderer textRenderer = client.textRenderer;

        context.getMatrices().push();
        context.getMatrices().scale(SCALE, SCALE, 1.0f);
        context.drawTextWithShadow(textRenderer, label, 6, 6, COLOR);
        context.getMatrices().pop();
    }
}
