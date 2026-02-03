package archives.tater.tagex.api;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricProvidedTagBuilder;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagBuilder;

/**
 * Interface-injected to {@link TagBuilder} for use in datagen
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface TagBuilderExtension {

    /**
     * Excludes an element from the tag
     */
    default TagBuilder tagex_excludeElement(Identifier element) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    default TagBuilder tagex_excludeOptionalElement(Identifier element) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes a tag from the tag
     */
    default TagBuilder tagex_excludeTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
     */
    default TagBuilder tagex_excludeOptionalTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't initialized
     * <p>
     * Analogous to {@link FabricProvidedTagBuilder#forceAddTag})
     */
    default TagBuilder tagex_excludeForcedTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }
}
