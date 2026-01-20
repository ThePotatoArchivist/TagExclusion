package archives.tater.tagexclusion.api;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricProvidedTagBuilder;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;

/**
 * Interface-injected to {@link TagAppender}
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface TagAppenderExtension<E, T> {

    /**
     * Adds an excluded element
     */
    default TagAppender<E, T> tagexclusion_exclude(E block) {
        return (TagAppender<E, T>) this;
    }

    /**
     * Adds an excluded optional element
     */
    default TagAppender<E, T> tagexclusion_excludeOptional(E block) {
        return (TagAppender<E, T>) this;
    }

    /**
     * Adds an excluded tag
     */
    default TagAppender<E, T> tagexclusion_excludeTag(TagKey<T> tag) {
        return (TagAppender<E, T>) this;
    }

    /**
     * Adds an excluded optional tag
     */
    default TagAppender<E, T> tagexclusion_excludeOptionalTag(TagKey<T> tag) {
        return (TagAppender<E, T>) this;
    }

    /**
     * Force-adds an excluded tag (doesn't fail if the tag isn't initialized, analogous to {@link FabricProvidedTagBuilder#forceAddTag})
     */
    default TagAppender<E, T> tagexclusion_forceExcludeTag(TagKey<T> tag) {
        return (TagAppender<E, T>) this;
    }
}
