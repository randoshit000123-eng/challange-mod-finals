package de.challengemod.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import de.challengemod.client.BlindClient;
import net.minecraft.client.render.BackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Verkleinert den Sichtradius des Blindness-Nebels.
 *
 * Vanilla setzt bei Blindness (unendliche Dauer) den Nebel auf Start 1.25 / Ende 5 Bloecke
 * (Himmel: Start 0 / Ende 4). Wir skalieren diese Werte nach dem Vanilla-Aufruf mit radius / 5.
 */
@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {

    // require = 0: Falls sich die Methode in einer anderen Version unterscheidet, startet das Spiel trotzdem
    @Inject(method = "applyFog", at = @At("TAIL"), require = 0)
    private static void challengemod$shrinkBlindFog(CallbackInfo ci) {
        if (!BlindClient.active) {
            return;
        }
        float end = RenderSystem.getShaderFogEnd();
        // Nur eingreifen, wenn gerade der Blindness-Nebel aktiv ist (Ende bei max. 5 Bloecken)
        if (end <= 0.0f || end > 5.01f) {
            return;
        }
        float scale = BlindClient.radius / 5.0f;
        RenderSystem.setShaderFogStart(RenderSystem.getShaderFogStart() * scale);
        RenderSystem.setShaderFogEnd(end * scale);
    }
}
