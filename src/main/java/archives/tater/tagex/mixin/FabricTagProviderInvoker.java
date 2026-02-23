package archives.tater.tagex.mixin;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

@Mixin(FabricTagProvider.class)
public interface FabricTagProviderInvoker<T> {
    @Invoker
    ResourceKey<T> invokeReverseLookup(T element);

    @Invoker
    FabricTagProvider<T>.FabricTagBuilder invokeGetOrCreateTagBuilder(TagKey<T> tag);
}
