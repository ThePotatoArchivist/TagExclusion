package archives.tater.tagex.impl;

import net.fabricmc.api.ModInitializer;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.util.ExtraCodecs;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

@ApiStatus.Internal
public class TagExclusion implements ModInitializer {
	public static final String MOD_ID = "tagex";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static <T extends TagEntry> T setExclude(T tagEntry) {
		tagEntry.tagex_setExclude(true);
		return tagEntry;
	}


	/**
	 * @see ExtraCodecs#TAG_OR_ELEMENT_ID
	 */
	private static DataResult<ExtraCodecs.TagOrElementLocation> parseTagOrElement(String string) {
		return string.startsWith("#")
				? ResourceLocation.read(string.substring(1)).map(identifier -> new ExtraCodecs.TagOrElementLocation(identifier, true))
				: ResourceLocation.read(string).map(identifier -> new ExtraCodecs.TagOrElementLocation(identifier, false));
	}

	private static TagEntry getTagEntry(ExtraCodecs.TagOrElementLocation location, boolean required) {
		return location.tag()
				? required
						? TagEntry.tag(location.id())
						: TagEntry.optionalTag(location.id())
				: required
						? TagEntry.element(location.id())
						: TagEntry.optionalElement(location.id());
	}

	// Utility
	public static <T> T getOrSet(LocalRef<@Nullable T> ref, Supplier<T> create) {
		var value = ref.get();
		if (value != null) return value;
		var newValue = create.get();
		ref.set(newValue);
		return newValue;
	}

	public static final Codec<TagEntry> TAG_ENTRY_SHORT_CODEC = Codec.STRING.comapFlatMap(
			string -> {
				boolean exclude;
				boolean required;

				if (string.startsWith("!")) {
					exclude = true;
					string = string.substring(1);
				} else
					exclude = false;

                if (string.endsWith("?")) {
					required = false;
					string = string.substring(0, string.length() - 1);
				} else
					required = true;

                return parseTagOrElement(string).map(location -> getTagEntry(location, required)
						.tagex_setExclude(exclude));
			},
			TagEntry::toString // Is this dangerous?
	);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

	}
}