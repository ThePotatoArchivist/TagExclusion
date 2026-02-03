package archives.tater.tagex.impl;

import net.minecraft.tags.TagEntry;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface TagEntryExtension {

    default boolean tagex_exclude() {
        throw new AssertionError("Implemented by mixin");
    }

    default TagEntry tagex_setExclude(boolean exclude) {
        throw new AssertionError("Implemented by mixin");
    }

}
