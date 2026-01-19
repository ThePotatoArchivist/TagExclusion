package archives.tater.tagexclusion;

/**
 * Extensions to {@link net.minecraft.tags.TagEntry}
 */
public interface TagEntryExtension {
    default boolean tagexclusion_exclude() {
        return false;
    }

    default void tagexclusion_setExclude(boolean exclude) {
        throw new AssertionError("Implemented by mixin");
    }
}
