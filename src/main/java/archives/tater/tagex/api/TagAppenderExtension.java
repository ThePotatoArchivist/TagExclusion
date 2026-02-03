package archives.tater.tagex.api;

import archives.tater.tagex.impl.TagExclusion;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricProvidedTagBuilder;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;

/**
 * Interface-injected to {@link TagAppender} for use in datagen
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface TagAppenderExtension<E, T> {

    /**
     * Excludes an element from the tag
     */
    @kotlin.Deprecated(message = TagExclusion.KOTLIN_DEPRECATION)
    default TagAppender<E, T> tagex_exclude(E block) {
        return (TagAppender<E, T>) this;
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    @kotlin.Deprecated(message = TagExclusion.KOTLIN_DEPRECATION)
    default TagAppender<E, T> tagex_excludeOptional(E block) {
        return (TagAppender<E, T>) this;
    }

    /**
     * Excludes a tag from the tag
     */
    @kotlin.Deprecated(message = TagExclusion.KOTLIN_DEPRECATION)
    default TagAppender<E, T> tagex_excludeTag(TagKey<T> tag) {
        return (TagAppender<E, T>) this;
    }

    /**
     * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
     */
    @kotlin.Deprecated(message = TagExclusion.KOTLIN_DEPRECATION)
    default TagAppender<E, T> tagex_excludeOptionalTag(TagKey<T> tag) {
        return (TagAppender<E, T>) this;
    }

    /**
     * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't registered during datagen
     * <p>
     * Analogous to {@link FabricProvidedTagBuilder#forceAddTag})
     */
    @kotlin.Deprecated(message = TagExclusion.KOTLIN_DEPRECATION)
    default TagAppender<E, T> tagex_forceExcludeTag(TagKey<T> tag) {
        return (TagAppender<E, T>) this;
    }
}
