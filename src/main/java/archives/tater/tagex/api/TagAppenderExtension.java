package archives.tater.tagex.api;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FabricTagBuilder;

import net.minecraft.data.tags.TagsProvider.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

/**
 * Interface-injected to {@link TagAppender} for use in datagen
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface TagAppenderExtension<T> {

    /**
     * Excludes an element from the tag
     */
    default TagAppender<T> tagex_exclude(ResourceKey<T> key) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    default TagAppender<T> tagex_excludeOptional(ResourceKey<T> key) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    default TagAppender<T> tagex_excludeOptional(ResourceLocation location) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes a tag from the tag
     */
    default TagAppender<T> tagex_excludeTag(TagKey<T> tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
     */
    default TagAppender<T> tagex_excludeOptionalTag(TagKey<T> tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't registered during datagen
     * <p>
     * Analogous to {@link FabricTagBuilder#forceAddTag})
     */
    default TagAppender<T> tagex_forceExcludeTag(TagKey<T> tag) {
        throw new AssertionError("Implemented by mixin");
    }

    default TagAppender<T> tagex_forceAddTag(TagKey<T> tag) {
        throw new AssertionError("Implemented by mixin");
    }
}
