package archives.tater.tagex.mixin;

import archives.tater.tagex.impl.TagEntryExtension;
import archives.tater.tagex.impl.TagExclusion;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagFile;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Mixin(TagFile.class)
public class TagFileMixin {
    @ModifyArg(
            method = "method_43950",
            at = @At(value = "INVOKE:FIRST", target = "Lcom/mojang/serialization/MapCodec;forGetter(Ljava/util/function/Function;)Lcom/mojang/serialization/codecs/RecordCodecBuilder;")
    )
    private static Function<TagFile, List<TagEntry>> filterTags(Function<TagFile, List<TagEntry>> getter) {
        return getter.andThen(entries -> entries.stream().filter(tagEntry -> !tagEntry.tagex_exclude()).toList());
    }

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;")
    )
    private static Codec<TagFile> addRemoveField(Codec<TagFile> original) {
        return RecordCodecBuilder.create(instance -> instance.group(
                MapCodec.assumeMapUnsafe(original).forGetter(Function.identity()),
                TagEntry.CODEC.listOf().optionalFieldOf("remove", List.of()).forGetter(file -> file.entries().stream().filter(TagEntryExtension::tagex_exclude).toList())
        ).apply(instance, (file, removals) -> new TagFile(
                Stream.concat(file.entries().stream(), removals.stream().map(TagExclusion::setExclude)).toList(),
                file.replace()
        )));
    }
}
