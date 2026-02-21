package archives.tater.tagex.mixin;

import archives.tater.tagex.api.FabricTagAppenderExtension;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FabricTagBuilder;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.data.tags.TagsProvider.TagAppender;
import net.minecraft.tags.TagBuilder;

@Mixin(FabricTagBuilder.class)
public abstract class FabricTagBuilderMixin<T> extends TagAppender<T> implements FabricTagAppenderExtension<T> {
    protected FabricTagBuilderMixin(TagBuilder builder) {
        super(builder);
    }

    @Override
    public FabricTagProvider<T>.FabricTagBuilder tagex_exclude(T value) {

        return FabricTagAppenderExtension.super.tagex_exclude(value);
    }

    @Override
    public FabricTagProvider<T>.FabricTagBuilder tagex_excludeOptional(T value) {
        return FabricTagAppenderExtension.super.tagex_excludeOptional(value);
    }
}
