package archives.tater.tagex.api;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FabricTagBuilder;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

/**
 * Interface-injected to {@link FabricTagBuilder} for use in datagen
 */
public interface FabricTagAppenderExtension<T> extends TagAppenderExtension<T> {
    /**
     * Excludes an element from the tag
     */
    default FabricTagProvider<T>.FabricTagBuilder tagex_exclude(T value) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    default FabricTagProvider<T>.FabricTagBuilder tagex_excludeOptional(T value) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an element from the tag
     */
    @Override
    default FabricTagProvider<T>.FabricTagBuilder tagex_exclude(ResourceKey<T> key) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    @Override
    default FabricTagProvider<T>.FabricTagBuilder tagex_excludeOptional(ResourceKey<T> key) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    @Override
    default FabricTagProvider<T>.FabricTagBuilder tagex_excludeOptional(ResourceLocation location) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes a tag from the tag
     */
    @Override
    default FabricTagProvider<T>.FabricTagBuilder tagex_excludeTag(TagKey<T> tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
     */
    @Override
    default FabricTagProvider<T>.FabricTagBuilder tagex_excludeOptionalTag(TagKey<T> tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't registered during datagen
     * <p>
     * Analogous to {@link FabricTagBuilder#forceAddTag})
     */
    @Override
    default FabricTagProvider<T>.FabricTagBuilder tagex_forceExcludeTag(TagKey<T> tag) {
        throw new AssertionError("Implemented by mixin");
    }
}
