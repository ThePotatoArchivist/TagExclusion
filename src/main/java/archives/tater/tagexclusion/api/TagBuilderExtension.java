package archives.tater.tagexclusion.api;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagBuilder;

/**
 * Interface-injected to {@link TagBuilder}
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface TagBuilderExtension {

    /**
     * Adds an excluded element
     */
    default TagBuilder tagexclusion_excludeElement(Identifier element) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Adds an excluded optional element
     */
    default TagBuilder tagexclusion_excludeOptionalElement(Identifier element) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Adds an excluded tag
     */
    default TagBuilder tagexclusion_excludeTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Adds an excluded optional tag
     */
    default TagBuilder tagexclusion_excludeOptionalTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Force-adds an excluded tag
     */
    default TagBuilder tagexclusion_excludeForcedTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }
}
