package archives.tater.tagexclusion.mixin;

import archives.tater.tagexclusion.api.TagAppenderExtension;

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
        public TagAppender<ResourceKey<T>, T> tagexclusion_exclude(ResourceKey<T> resourceKey) {
            val$builder.tagexclusion_excludeElement(resourceKey.identifier());
            return (TagAppender<ResourceKey<T>, T>) this;
        }

        @Override
        public TagAppender<ResourceKey<T>, T> tagexclusion_excludeOptional(ResourceKey<T> resourceKey) {
            val$builder.tagexclusion_excludeOptionalElement(resourceKey.identifier());
            return (TagAppender<ResourceKey<T>, T>) this;
        }

        @Override
        public TagAppender<ResourceKey<T>, T> tagexclusion_excludeTag(TagKey<T> tag) {
            val$builder.tagexclusion_excludeTag(tag.location());
            return (TagAppender<ResourceKey<T>, T>) this;
        }

        @Override
        public TagAppender<ResourceKey<T>, T> tagexclusion_excludeOptionalTag(TagKey<T> tag) {
            val$builder.tagexclusion_excludeOptionalTag(tag.location());
            return (TagAppender<ResourceKey<T>, T>) this;
        }

        @Override
        public TagAppender<ResourceKey<T>, T> tagexclusion_forceExcludeTag(TagKey<T> tag) {
            val$builder.tagexclusion_excludeForcedTag(tag.location());
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
        public TagAppender<U, T> tagexclusion_exclude(U block) {
            val$original.tagexclusion_exclude(val$converter.apply(block));
            return (TagAppender<U, T>) this;
        }

        @Override
        public TagAppender<U, T> tagexclusion_excludeOptional(U block) {
            val$original.tagexclusion_excludeOptional(val$converter.apply(block));
            return (TagAppender<U, T>) this;
        }

        @Override
        public TagAppender<U, T> tagexclusion_excludeTag(TagKey<T> tag) {
            val$original.tagexclusion_excludeTag(tag);
            return (TagAppender<U, T>) this;
        }

        @Override
        public TagAppender<U, T> tagexclusion_excludeOptionalTag(TagKey<T> tag) {
            val$original.tagexclusion_excludeOptionalTag(tag);
            return (TagAppender<U, T>) this;
        }

        @Override
        public TagAppender<U, T> tagexclusion_forceExcludeTag(TagKey<T> tag) {
            val$original.tagexclusion_forceExcludeTag(tag);
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
