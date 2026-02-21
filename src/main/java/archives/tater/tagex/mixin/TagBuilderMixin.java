package archives.tater.tagex.mixin;

import archives.tater.tagex.api.TagBuilderExtension;
import archives.tater.tagex.api.ExclusionTag;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;

@Mixin(TagBuilder.class)
public abstract class TagBuilderMixin implements TagBuilderExtension {

    @Shadow
    public abstract TagBuilder add(TagEntry entry);

    @Override
    public TagBuilder tagex_excludeElement(ResourceLocation element) {
        add(ExclusionTag.excludeElement(element));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagex_excludeOptionalElement(ResourceLocation element) {
        add(ExclusionTag.excludeOptionalElement(element));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagex_excludeTag(ResourceLocation tag) {
        add(ExclusionTag.excludeTag(tag));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagex_excludeOptionalTag(ResourceLocation tag) {
        add(ExclusionTag.excludeOptionalTag(tag));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagex_excludeForcedTag(ResourceLocation tag) {
        add(ExclusionTag.excludeForcedTag(tag));
        return (TagBuilder) (Object) this;
    }
}
