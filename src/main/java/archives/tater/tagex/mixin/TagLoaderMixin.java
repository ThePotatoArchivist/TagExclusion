package archives.tater.tagex.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.datafixers.util.Either;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagLoader;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static archives.tater.tagex.impl.TagExclusion.getOrSet;

// This may break other mods trying to use the ImmutableSet.Builder, but I don't think there's really a better way to
// accomplish this besides maybe a bunch of accessors to ImmutableSet$*
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

    @Inject(
            method = "build(Lnet/minecraft/tags/TagEntry$Lookup;Ljava/util/List;)Lcom/mojang/datafixers/util/Either;",
            at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z")
    )
    private <T> void appendCustomEntries(TagEntry.Lookup<T> lookup, List<TagLoader.EntryWithSource> entries, CallbackInfoReturnable<Either<Collection<TagLoader.EntryWithSource>, Collection<T>>> cir, @Local ImmutableSet.Builder<T> builder, @Share("entries") LocalRef<@Nullable Set<T>> entriesRef) {
        var cEntries = entriesRef.get();
        if (cEntries == null) return;
        builder.addAll(cEntries);
    }
}
