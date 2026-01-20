package archives.tater.tagexclusion.api;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;

/**
 * Interface-injected to {@link TagAppender}
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface TagAppenderExtension<E, T> {

    default TagAppender<E, T> tagexclusion_exclude(E block) {
        return (TagAppender<E, T>) this;
    }

    default TagAppender<E, T> tagexclusion_excludeOptional(E block) {
        return (TagAppender<E, T>) this;
    }

    default TagAppender<E, T> tagexclusion_excludeTag(TagKey<T> tag) {
        return (TagAppender<E, T>) this;
    }

    default TagAppender<E, T> tagexclusion_excludeOptionalTag(TagKey<T> tag) {
        return (TagAppender<E, T>) this;
    }
}
