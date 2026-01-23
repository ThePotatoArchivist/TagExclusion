package archives.tater.tagex.mixin;

import archives.tater.tagex.api.TagAppenderExtension;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;

import java.util.function.Function;

@SuppressWarnings("unchecked")
@Mixin(TagAppender.class)
public interface TagAppenderMixin<E, T> extends TagAppenderExtension<E, T> {
    @Mixin(targets = "net.minecraft.data.tags.TagAppender$1")
    abstract class ProvidedTagBuilder1Mixin<T> implements TagAppenderMixin<ResourceKey<T>, T> {
        @Shadow
        @Final
        TagBuilder val$builder;

        @Override
        public TagAppender<ResourceKey<T>, T> tagex_exclude(ResourceKey<T> resourceKey) {
            val$builder.tagex_excludeElement(resourceKey.identifier());
            return (TagAppender<ResourceKey<T>, T>) this;
        }

        @Override
        public TagAppender<ResourceKey<T>, T> tagex_excludeOptional(ResourceKey<T> resourceKey) {
            val$builder.tagex_excludeOptionalElement(resourceKey.identifier());
            return (TagAppender<ResourceKey<T>, T>) this;
        }

        @Override
        public TagAppender<ResourceKey<T>, T> tagex_excludeTag(TagKey<T> tag) {
            val$builder.tagex_excludeTag(tag.location());
            return (TagAppender<ResourceKey<T>, T>) this;
        }

        @Override
        public TagAppender<ResourceKey<T>, T> tagex_excludeOptionalTag(TagKey<T> tag) {
            val$builder.tagex_excludeOptionalTag(tag.location());
            return (TagAppender<ResourceKey<T>, T>) this;
        }

        @Override
        public TagAppender<ResourceKey<T>, T> tagex_forceExcludeTag(TagKey<T> tag) {
            val$builder.tagex_excludeForcedTag(tag.location());
            return (TagAppender<ResourceKey<T>, T>) this;
        }
    }

    @Mixin(targets = "net.minecraft.data.tags.TagAppender$2")
    abstract class ProvidedTagBuilder2Mixin<E, U, T> implements TagAppenderMixin<U, T> {
        @Shadow
        @Final
        TagAppender<E, T> val$original;

        @Shadow
        @Final
        Function<U, E> val$converter;

        @Override
        public TagAppender<U, T> tagex_exclude(U block) {
            val$original.tagex_exclude(val$converter.apply(block));
            return (TagAppender<U, T>) this;
        }

        @Override
        public TagAppender<U, T> tagex_excludeOptional(U block) {
            val$original.tagex_excludeOptional(val$converter.apply(block));
            return (TagAppender<U, T>) this;
        }

        @Override
        public TagAppender<U, T> tagex_excludeTag(TagKey<T> tag) {
            val$original.tagex_excludeTag(tag);
            return (TagAppender<U, T>) this;
        }

        @Override
        public TagAppender<U, T> tagex_excludeOptionalTag(TagKey<T> tag) {
            val$original.tagex_excludeOptionalTag(tag);
            return (TagAppender<U, T>) this;
        }

        @Override
        public TagAppender<U, T> tagex_forceExcludeTag(TagKey<T> tag) {
            val$original.tagex_forceExcludeTag(tag);
            return (TagAppender<U, T>) this;
        }

        // Fixes vanilla bug
        // Remove when porting to 26.1, merged into FAPI
        @WrapOperation(
                method = "addOptional",
                at = @At(value = "INVOKE", target = "Lnet/minecraft/data/tags/TagAppender;add(Ljava/lang/Object;)Lnet/minecraft/data/tags/TagAppender;")
        )
        private TagAppender<E, T> fixOptional(TagAppender<E, T> instance, E e, Operation<TagAppender<E, T>> original) {
            return instance.addOptional(e);
        }
    }
}
