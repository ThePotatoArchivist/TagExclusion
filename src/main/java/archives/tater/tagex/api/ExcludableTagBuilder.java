package archives.tater.tagex.api;

import archives.tater.tagex.impl.ExcludableTagBuilderImpl;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FabricTagBuilder;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;

import org.jetbrains.annotations.ApiStatus;

/**
 * Similar to {@link FabricTagBuilder} but has methods to exclude entries
 * @param <T> The type of the tag
 */
@ApiStatus.NonExtendable
public abstract class ExcludableTagBuilder<T> extends TagsProvider.TagAppender<T> {

    protected ExcludableTagBuilder(TagBuilder builder) {
        super(builder);
    }

    /**
     * Excludes an element from the tag
     */
    public abstract ExcludableTagBuilder<T> tagex_exclude(T value);

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    public abstract ExcludableTagBuilder<T> tagex_excludeOptional(T value);

    public abstract ExcludableTagBuilder<T> add(T value);

    public abstract ExcludableTagBuilder<T> addOptional(T value);

    @Override
    public ExcludableTagBuilder<T> add(ResourceKey<T> key) {
        super.add(key);
        return this;
    }

    @SafeVarargs
    @Override
    public final ExcludableTagBuilder<T> add(ResourceKey<T>... keys) {
        super.add(keys);
        return this;
    }

    @Override
    public ExcludableTagBuilder<T> addOptional(ResourceLocation location) {
        super.addOptional(location);
        return this;
    }

    @Override
    public ExcludableTagBuilder<T> addTag(TagKey<T> tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public ExcludableTagBuilder<T> addOptionalTag(ResourceLocation location) {
        super.addOptionalTag(location);
        return this;
    }

    public abstract ExcludableTagBuilder<T> forceAddTag(TagKey<T> tag);

    /**
     * Excludes an element from the tag
     */
    @Override
    public ExcludableTagBuilder<T> tagex_exclude(ResourceKey<T> key) {
        super.tagex_exclude(key);
        return this;
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    @Override
    public ExcludableTagBuilder<T> tagex_excludeOptional(ResourceKey<T> key) {
        super.tagex_excludeOptional(key);
        return this;
    }

    /**
     * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
     */
    @Override
    public ExcludableTagBuilder<T> tagex_excludeOptional(ResourceLocation location) {
        super.tagex_excludeOptional(location);
        return this;
    }

    /**
     * Excludes a tag from the tag
     */
    @Override
    public ExcludableTagBuilder<T> tagex_excludeTag(TagKey<T> tag) {
        super.tagex_excludeTag(tag);
        return this;
    }

    /**
     * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
     */
    @Override
    public ExcludableTagBuilder<T> tagex_excludeOptionalTag(TagKey<T> tag) {
        super.tagex_excludeOptionalTag(tag);
        return this;
    }

    /**
     * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't registered during datagen
     * <p>
     * Analogous to {@link FabricTagBuilder#forceAddTag})
     */
    @Override
    public ExcludableTagBuilder<T> tagex_forceExcludeTag(TagKey<T> tag) {
        super.tagex_forceExcludeTag(tag);
        return this;
    }

    /**
     * Creates an {@link ExcludableTagBuilder} from a {@link FabricTagProvider} and a {@link TagKey}. Use in place of
     * {@link FabricTagProvider#getOrCreateTagBuilder} when the tag needs exclusion entries
     */
    public static <T> ExcludableTagBuilder<T> getOrCreate(FabricTagProvider<T> provider, TagKey<T> tag) {
        return new ExcludableTagBuilderImpl<>(provider, tag);
    }
}
