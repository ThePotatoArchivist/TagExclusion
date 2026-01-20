package archives.tater.tagexclusion;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagBuilder;

/**
 * Extensions to {@link TagBuilder}
 */
public interface TagBuilderExtension {
    default TagBuilder tagexclusion_excludeElement(Identifier element) {
        throw new AssertionError("Implemented by mixin");
    }

    default TagBuilder tagexclusion_excludeOptionalElement(Identifier element) {
        throw new AssertionError("Implemented by mixin");
    }

    default TagBuilder tagexclusion_excludeTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }

    default TagBuilder tagexclusion_excludeOptionalTag(Identifier tag) {
        throw new AssertionError("Implemented by mixin");
    }
}
