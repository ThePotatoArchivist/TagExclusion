package archives.tater.tagex.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.tags.TagLoader;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

import static archives.tater.tagex.impl.TagExclusion.getOrSet;

@Mixin(TagLoader.class)
public class TagLoaderMixin {
    @ModifyArg(
            method = "build(Lnet/minecraft/tags/TagEntry$Lookup;Ljava/util/List;)Lcom/mojang/datafixers/util/Either;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/tags/TagEntry;build(Lnet/minecraft/tags/TagEntry$Lookup;Ljava/util/function/Consumer;)Z"),
            index = 1
    )
    private <T> Consumer<T> excludeEntries(Consumer<T> consumer, @Local TagLoader.EntryWithSource tagEntry, @Share("entries") LocalRef<@Nullable Set<T>> entriesRef) {
        var entries = getOrSet(entriesRef, LinkedHashSet::new);
        return tagEntry.entry().tagex_exclude() ? entries::remove : entries::add;
    }

    @WrapOperation(
            method = "build(Lnet/minecraft/tags/TagEntry$Lookup;Ljava/util/List;)Lcom/mojang/datafixers/util/Either;",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet$Builder;build()Lcom/google/common/collect/ImmutableSet;")
    )
    private <T> ImmutableSet<T> replaceBuild(ImmutableSet.Builder<T> instance, Operation<ImmutableSet<T>> original, @Share("entries") LocalRef<@Nullable Set<T>> entriesRef) {
        var entries = entriesRef.get();
        if (entries == null) return original.call(instance);
        return ImmutableSet.copyOf(entries);
    }
}
