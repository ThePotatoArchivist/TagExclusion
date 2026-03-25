package archives.tater.tagex.test

import archives.tater.tagex.api.exclude
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class TagGeneratorKt(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagsProvider.ItemTagsProvider(output, registriesFuture) {
    override fun addTags(wrapperLookup: HolderLookup.Provider) {
        with (valueLookupBuilder(ItemTags.LEAVES)) {
            exclude(Items.PALE_OAK_LEAVES)
            exclude(Items.NETHER_WART_BLOCK)
            add(Items.PALE_OAK_LEAVES)
        }
    }

    override fun getName(): String = "Item tags 2"
}