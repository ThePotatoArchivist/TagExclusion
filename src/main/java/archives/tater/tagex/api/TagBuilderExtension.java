package archives.tater.tagex.api;

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
    default TagBuilder tagex_excludeElement(Identifier element) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Adds an excluded optional element
     */
    default TagBuilder tagex_excludeOptionalElement(Identifier element) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Adds an excluded tag
     */
    default TagBuilder tagex_excludeTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Adds an excluded optional tag
     */
    default TagBuilder tagex_excludeOptionalTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Force-adds an excluded tag
     */
    default TagBuilder tagex_excludeForcedTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }
}
