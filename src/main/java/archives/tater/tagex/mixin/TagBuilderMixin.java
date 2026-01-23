package archives.tater.tagex.mixin;

import archives.tater.tagex.api.TagBuilderExtension;
import archives.tater.tagex.api.TagEntryExtension;

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
    public TagBuilder tagex_excludeElement(Identifier element) {
        add(TagEntryExtension.excludeElement(element));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagex_excludeOptionalElement(Identifier element) {
        add(TagEntryExtension.excludeOptionalElement(element));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagex_excludeTag(Identifier tag) {
        add(TagEntryExtension.excludeTag(tag));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagex_excludeOptionalTag(Identifier tag) {
        add(TagEntryExtension.excludeOptionalTag(tag));
        return (TagBuilder) (Object) this;
    }

    @Override
    public TagBuilder tagex_excludeForcedTag(Identifier tag) {
        add(TagEntryExtension.excludeForcedTag(tag));
        return (TagBuilder) (Object) this;
    }
}
