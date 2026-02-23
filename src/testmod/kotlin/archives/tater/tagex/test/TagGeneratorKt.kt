package archives.tater.tagex.test

import archives.tater.tagex.api.exclude
import archives.tater.tagex.api.getOrCreateExcludableBuilder
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class TagGeneratorKt(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(wrapperLookup: HolderLookup.Provider) {
        with (getOrCreateExcludableBuilder(ItemTags.LEAVES)) {
            exclude(Items.OAK_LEAVES)
            exclude(Items.NETHER_WART_BLOCK)
            add(Items.OAK_LEAVES)
        }
    }

    override fun getName(): String = "Item tags 2"
}