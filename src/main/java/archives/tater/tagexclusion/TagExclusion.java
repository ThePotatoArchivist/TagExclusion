package archives.tater.tagexclusion;

import net.fabricmc.api.ModInitializer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagEntry;
import net.minecraft.util.ExtraCodecs;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagExclusion implements ModInitializer {
	public static final String MOD_ID = "tagexclusion";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static boolean ENCODE_IN_SHORT_FORMAT = false;

	/**
	 * @see ExtraCodecs#TAG_OR_ELEMENT_ID
	 */
	public static DataResult<ExtraCodecs.TagOrElementLocation> parseTagOrElement(String string) {
		return string.startsWith("#")
				? Identifier.read(string.substring(1)).map(identifier -> new ExtraCodecs.TagOrElementLocation(identifier, true))
				: Identifier.read(string).map(identifier -> new ExtraCodecs.TagOrElementLocation(identifier, false));
	}

	private static @NonNull TagEntry getTagEntry(ExtraCodecs.TagOrElementLocation location, boolean required) {
		return location.tag()
				? required
						? TagEntry.tag(location.id())
						: TagEntry.optionalTag(location.id())
				: required
						? TagEntry.element(location.id())
						: TagEntry.optionalElement(location.id());
	}

	public static final Codec<TagEntry> TAG_ENTRY_SHORT_CODEC = Codec.STRING.comapFlatMap(
			string -> {
				boolean exclude;
				boolean required;
				if (string.startsWith("!")) {
					exclude = true;
					string = string.substring(1);
				} else {
                    exclude = false;
                }
                if (string.endsWith("?")) {
					required = false;
					string = string.substring(0, string.length() - 1);
				} else
					required = true;
                return parseTagOrElement(string).map(location -> {
					var entry = getTagEntry(location, required);
					entry.tagexclusion_setExclude(exclude);
					return entry;
				});
			},
			tagEntry -> tagEntry.tagexclusion_exclude() ? "!" + tagEntry : tagEntry.toString()
	);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


	}
}