package archives.tater.tagex.mixin;

import archives.tater.tagex.impl.TagEntryExtension;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.tags.TagEntry;

@Mixin(TagEntry.class)
public class TagEntryMixin implements TagEntryExtension {
    @Unique
    private boolean exclude = false;

    @Override
    public boolean tagex_exclude() {
        return exclude;
    }

    @Override
    public TagEntry tagex_setExclude(boolean exclude) {
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
}
