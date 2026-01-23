package archives.tater.tagex.api;

import net.fabricmc.fabric.impl.datagen.ForcedTagEntry;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagEntry;

/**
 * Interface-injected to {@link net.minecraft.tags.TagEntry}
 */
@SuppressWarnings("unused")
public interface TagEntryExtension {

    /**
     * @return If the tag entry is an exclusion entry
     */
    default boolean tagex_exclude() {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Set if the tag entry should be an exclusion entry
     */
    default TagEntry tagex_setExclude(boolean exclude) {
        throw new AssertionError("Implemented by mixin");
    }

    /**
     * Creates an excluded tag {@link TagEntry}
     */
    static TagEntry excludeTag(Identifier tag) {
        return TagEntry.tag(tag)
                .tagex_setExclude(true);
    }

    /**
     * Creates an excluded optional tag {@link TagEntry}
     */
    static TagEntry excludeOptionalTag(Identifier tag) {
        return TagEntry.optionalTag(tag)
                .tagex_setExclude(true);
    }

    /**
     * Creates an excluded {@link ForcedTagEntry}
     */
    static TagEntry excludeForcedTag(Identifier tag) {
        return new ForcedTagEntry(tag)
                .tagex_setExclude(true);
    }

    /**
     * Creates an excluded element {@link TagEntry}
     */
    static TagEntry excludeElement(Identifier element) {
        return TagEntry.element(element)
                .tagex_setExclude(true);
    }

    /**
     * Creates an excluded optional element {@link TagEntry}
     */
    static TagEntry excludeOptionalElement(Identifier element) {
        return TagEntry.optionalTag(element)
                .tagex_setExclude(true);
    }
}
