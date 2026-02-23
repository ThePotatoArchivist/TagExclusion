package archives.tater.tagex.impl;

import archives.tater.tagex.api.ExclusionTag;
import archives.tater.tagex.api.ExcludableTagBuilder;
import archives.tater.tagex.mixin.FabricTagProviderInvoker;
import archives.tater.tagex.mixin.TagAppenderAccessor;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.impl.datagen.ForcedTagEntry;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;

public class ExcludableTagBuilderImpl<T> extends ExcludableTagBuilder<T> {

    private final FabricTagProviderInvoker<T> provider;
    private final TagBuilder builder;

    @SuppressWarnings("unchecked")
    public ExcludableTagBuilderImpl(FabricTagProvider<T> provider, TagsProvider.TagAppender<T> parent) {
        super(((TagAppenderAccessor) parent).getBuilder());
        this.provider = (FabricTagProviderInvoker<T>) provider;
        this.builder = ((TagAppenderAccessor) parent).getBuilder();
    }

    @SuppressWarnings("unchecked")
    public ExcludableTagBuilderImpl(FabricTagProvider<T> provider, TagKey<T> tag) {
        this(provider, ((FabricTagProviderInvoker<T>) provider).invokeGetOrCreateTagBuilder(tag));
    }

    @Override
    public ExcludableTagBuilder<T> tagex_exclude(T value) {
        builder.add(ExclusionTag.excludeElement(provider.invokeReverseLookup(value).location()));
        return this;
    }

    @Override
    public ExcludableTagBuilder<T> tagex_excludeOptional(T value) {
        builder.add(ExclusionTag.excludeOptionalElement(provider.invokeReverseLookup(value).location()));
        return this;
    }

    @Override
    public ExcludableTagBuilder<T> add(T value) {
        add(provider.invokeReverseLookup(value));
        return this;
    }

    @Override
    public ExcludableTagBuilder<T> addOptional(T value) {
        addOptional(provider.invokeReverseLookup(value).location());
        return this;
    }

    /**
     * @see net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FabricTagBuilder#forceAddTag
     */
    @Override
    public ExcludableTagBuilder<T> forceAddTag(TagKey<T> tag) {
        builder.add(new ForcedTagEntry(TagEntry.element(tag.location())));
        return this;
    }
}
