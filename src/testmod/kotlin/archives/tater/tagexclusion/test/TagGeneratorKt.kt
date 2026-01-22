package archives.tater.tagexclusion.test

import archives.tater.tagexclusion.api.exclude
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class TagGeneratorKt(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(wrapperLookup: HolderLookup.Provider) {
        with (valueLookupBuilder(ItemTags.LEAVES)) {
            exclude(Items.PALE_OAK_LEAVES)
        }
    }

    override fun getName(): String = "Item tags 2"
}