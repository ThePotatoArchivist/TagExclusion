package archives.tater.tagex.mixin;

import archives.tater.tagex.api.TagAppenderExtension;

import net.fabricmc.fabric.impl.datagen.ForcedTagEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.data.tags.TagsProvider.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;

@SuppressWarnings("unchecked")
@Mixin(TagAppender.class)
public class TagAppenderMixin<T> implements TagAppenderExtension<T> {

    @Shadow
    @Final
    private TagBuilder builder;

    @Override
    public TagAppender<T> tagex_exclude(ResourceKey<T> resourceKey) {
        builder.tagex_excludeElement(resourceKey.location());
        return (TagAppender<T>) (Object) this;
    }

    @Override
    public TagAppender<T> tagex_excludeOptional(ResourceKey<T> key) {
        builder.tagex_excludeOptionalElement(key.location());
        return (TagAppender<T>) (Object) this;
    }

    @Override
    public TagAppender<T> tagex_excludeOptional(ResourceLocation location) {
        builder.tagex_excludeOptionalElement(location);
        return (TagAppender<T>) (Object) this;
    }

    @Override
    public TagAppender<T> tagex_excludeTag(TagKey<T> tag) {
        builder.tagex_excludeTag(tag.location());
        return (TagAppender<T>) (Object) this;
    }

    @Override
    public TagAppender<T> tagex_excludeOptionalTag(TagKey<T> tag) {
        builder.tagex_excludeOptionalTag(tag.location());
        return (TagAppender<T>) (Object) this;
    }

    @Override
    public TagAppender<T> tagex_forceExcludeTag(TagKey<T> tag) {
        builder.tagex_excludeForcedTag(tag.location());
        return (TagAppender<T>) (Object) this;
    }

    @Override
    public TagAppender<T> tagex_forceAddTag(TagKey<T> tag) {
        builder.add(new ForcedTagEntry(TagEntry.element(tag.location())));
        return (TagAppender<T>) (Object) this;
    }
}
