package archives.tater.tagexclusion.test;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

public class TagExclusionTestDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        pack.addProvider((output, registriesFuture) -> new FabricTagProvider.ItemTagProvider(output, registriesFuture) {
            @Override
            protected void addTags(HolderLookup.Provider wrapperLookup) {
                valueLookupBuilder(ItemTags.ANVIL)
                        .add(Items.AMETHYST_BLOCK);
            }
        });
    }
}
