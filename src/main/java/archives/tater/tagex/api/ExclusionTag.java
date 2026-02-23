package archives.tater.tagex.api;

import archives.tater.tagex.impl.ExcludableTagBuilderImpl;
import archives.tater.tagex.impl.TagExclusion;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.impl.datagen.ForcedTagEntry;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;

/**
 * Allows creating exclusion tag entries
 */
public class ExclusionTag {
    private ExclusionTag() {}

    /**
     * Creates an excluded tag {@link TagEntry}
     */
    public static TagEntry excludeTag(ResourceLocation tag) {
        return TagExclusion.setExclude(TagEntry.tag(tag));
    }

    /**
     * Creates an excluded optional tag {@link TagEntry}
     */
    public static TagEntry excludeOptionalTag(ResourceLocation tag) {
        return TagExclusion.setExclude(TagEntry.optionalTag(tag));
    }

    /**
     * Creates an excluded {@link ForcedTagEntry}
     */
    public static TagEntry excludeForcedTag(ResourceLocation tag) {
        return TagExclusion.setExclude(new ForcedTagEntry(TagEntry.element(tag))); // TODO test
    }

    /**
     * Creates an excluded element {@link TagEntry}
     */
    public static TagEntry excludeElement(ResourceLocation element) {
        return TagExclusion.setExclude(TagEntry.element(element));
    }

    /**
     * Creates an excluded optional element {@link TagEntry}
     */
    public static TagEntry excludeOptionalElement(ResourceLocation element) {
        return TagExclusion.setExclude(TagEntry.optionalTag(element));
    }

    public static <T> ExcludableTagBuilder<T> createExcludableBuilder(FabricTagProvider<T> provider, TagsProvider.TagAppender<T> parent) {
        return new ExcludableTagBuilderImpl<>(provider, parent);
    }

    public static <T> ExcludableTagBuilder<T> getOrCreateExcludableBuilder(FabricTagProvider<T> provider, TagKey<T> tag) {
        return new ExcludableTagBuilderImpl<>(provider, tag);
    }
}
