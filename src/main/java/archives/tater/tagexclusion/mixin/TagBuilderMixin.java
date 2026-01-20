package archives.tater.tagexclusion.mixin;

import archives.tater.tagexclusion.TagBuilderExtension;
import archives.tater.tagexclusion.TagEntryExtension;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;

@Mixin(TagBuilder.class)
public abstract class TagBuilderMixin implements TagBuilderExtension {

    @Shadow
    public abstract TagBuilder add(TagEntry entry);

    @Override
    public TagBuilder tagexclusion_excludeElement(Identifier element) {
        add(TagEntryExtension.excludeElement(element));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagexclusion_excludeOptionalElement(Identifier element) {
        add(TagEntryExtension.excludeOptionalElement(element));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagexclusion_excludeTag(Identifier tag) {
        add(TagEntryExtension.excludeTag(tag));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagexclusion_excludeOptionalTag(Identifier tag) {
        add(TagEntryExtension.excludeOptionalTag(tag));
        return (TagBuilder) (Object) this;
    }
}
