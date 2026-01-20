package archives.tater.tagexclusion.mixin;

import archives.tater.tagexclusion.TagExclusion;
import archives.tater.tagexclusion.api.TagEntryExtension;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.objectweb.asm.Opcodes;

import com.mojang.datafixers.util.Either;
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
    public TagEntry tagexclusion_setExclude(boolean exclude) {
        this.exclude = exclude;
        return (TagEntry) (Object) this;
    }

    @ModifyExpressionValue(
            method = "toString",
            at = @At(value = "NEW", target = "()Ljava/lang/StringBuilder;")
    )
    private StringBuilder representExclusion(StringBuilder original) {
        if (exclude)
            original.append('!');
        return original;
    }

    @WrapOperation(
            method = "method_43938",
            at = @At(value = "FIELD", target = "Lnet/minecraft/tags/TagEntry;required:Z", opcode = Opcodes.GETFIELD)
    )
    private static boolean longFormIfExclude(TagEntry instance, Operation<Boolean> original) {
        return original.call(instance) && !instance.tagexclusion_exclude();
    }

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;")
    )
    private static Codec<TagEntry> wrapCodec(Codec<TagEntry> original) {
        return Codec.lazyInitialized(() -> Codec.withAlternative(
                RecordCodecBuilder.create(instance -> instance.group(
                        MapCodec.assumeMapUnsafe(original).forGetter(Function.identity()),
                        Codec.BOOL.optionalFieldOf("tagexclusion:exclude", false).forGetter(TagEntry::tagexclusion_exclude)
                ).apply(instance, TagEntryExtension::tagexclusion_setExclude)),
                original
        ));
    }

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Codec;xmap(Ljava/util/function/Function;Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;")
    )
    private static Codec<TagEntry> addExtraCodec(Codec<TagEntry> original) {
        return Codec.either(TagExclusion.TAG_ENTRY_SHORT_CODEC, original)
                .xmap(
                        either -> either.map(Function.identity(), Function.identity()),
                        tagEntry -> TagExclusion.ENCODE_IN_SHORT_FORMAT ? Either.left(tagEntry) : Either.right(tagEntry)
                );
    }
}
