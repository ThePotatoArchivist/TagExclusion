package archives.tater.tagexclusion;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagEntry;

/**
 * Extensions to {@link net.minecraft.tags.TagEntry}
 */
public interface TagEntryExtension {
    default boolean tagexclusion_exclude() {
        return false;
    }

    default TagEntry tagexclusion_setExclude(boolean exclude) {
        throw new AssertionError("Implemented by mixin");
    }

    static TagEntry excludeTag(Identifier tag) {
        return TagEntry.tag(tag)
                .tagexclusion_setExclude(true);
    }

    static TagEntry excludeOptionalTag(Identifier tag) {
        return TagEntry.optionalTag(tag)
                .tagexclusion_setExclude(true);
    }

    static TagEntry excludeElement(Identifier element) {
        return TagEntry.element(element)
                .tagexclusion_setExclude(true);
    }

    static TagEntry excludeOptionalElement(Identifier element) {
        return TagEntry.optionalTag(element)
                .tagexclusion_setExclude(true);
    }
}
