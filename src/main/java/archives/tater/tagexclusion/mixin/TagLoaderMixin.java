package archives.tater.tagexclusion.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.tags.TagLoader;

import java.util.SequencedSet;
import java.util.function.Consumer;

@Mixin(TagLoader.class)
public class TagLoaderMixin {
    @ModifyArg(
            method = "tryBuildTag",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/tags/TagEntry;build(Lnet/minecraft/tags/TagEntry$Lookup;Ljava/util/function/Consumer;)Z"),
            index = 1
    )
    private <T> Consumer<T> excludeEntries(Consumer<T> consumer, @Local TagLoader.EntryWithSource tagEntry, @Local SequencedSet<T> entries) {
        return tagEntry.entry().tagexclusion_exclude() ? entries::remove : consumer;
    }
}
