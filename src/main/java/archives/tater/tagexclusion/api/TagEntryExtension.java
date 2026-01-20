package archives.tater.tagexclusion.api;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagEntry;

/**
 * Interface-injected to {@link net.minecraft.tags.TagEntry}
 */
@SuppressWarnings("unused")
public interface TagEntryExtension {

    default boolean tagexclusion_exclude() {
        throw new AssertionError("Implemented by mixin");
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
