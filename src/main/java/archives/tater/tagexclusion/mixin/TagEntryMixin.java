package archives.tater.tagexclusion.mixin;

import archives.tater.tagexclusion.TagEntryExtension;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.objectweb.asm.Opcodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.tags.TagEntry;

import java.util.function.Function;

@Mixin(TagEntry.class)
public class TagEntryMixin implements TagEntryExtension {
    @Unique
    private boolean exclude = false;

    @Override
    public boolean tagexclusion_exclude() {
        return exclude;
    }

    @Override
    public void tagexclusion_setExclude(boolean exclude) {
        this.exclude = exclude;
    }

    @WrapOperation(
            method = "method_43938",
            at = @At(value = "FIELD", target = "Lnet/minecraft/tags/TagEntry;required:Z", opcode = Opcodes.GETFIELD)
    )
    private static boolean longFormIfExclude(TagEntry instance, Operation<Boolean> original) {
        return original.call(instance) && !instance.tagexclusion_exclude();
    }

//    @SuppressWarnings("InvalidInjectorMethodSignature")
//    @WrapOperation(
//            method = "method_43941",
//            at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/Products$P2;apply(Lcom/mojang/datafixers/kinds/Applicative;Ljava/util/function/BiFunction;)Lcom/mojang/datafixers/kinds/App;")
//    )
//    private static <T1, T2> App<RecordCodecBuilder.Mu<TagEntry>, TagEntry> extendCodec(Products.P2<RecordCodecBuilder.Mu<TagEntry>, T1, T2> instance, RecordCodecBuilder.Instance<TagEntry> instance2, BiFunction<T1, T2, TagEntry> function, Operation<App<RecordCodecBuilder.Mu<TagEntry>, TagEntry>> original) {
//        return original.call(instance, instance2, function);
//    }

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;")
    )
    private static Codec<TagEntry> test(Codec<TagEntry> original) {
        return Codec.lazyInitialized(() -> Codec.withAlternative(
                RecordCodecBuilder.create(instance -> instance.group(
                        MapCodec.assumeMapUnsafe(original).forGetter(Function.identity()),
                        Codec.BOOL.optionalFieldOf("tagexclusion:exclude", false).forGetter(TagEntry::tagexclusion_exclude)
                ).apply(instance, (data, exclude) -> {
                    data.tagexclusion_setExclude(exclude);
                    return data;
                })),
                original
        ));
    }
}
