@file:Suppress("unused")

package archives.tater.tagex.api

import net.fabricmc.fabric.api.datagen.v1.provider.FabricProvidedTagBuilder
import net.minecraft.data.tags.TagAppender
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagBuilder
import net.minecraft.tags.TagKey

/**
 * Extension function wrappers for interface-injected methods so you can call the methods without having to write
 * `tagex_` at the beginning
 */

/**
 * Excludes an element from the tag
 */
fun TagBuilder.excludeElement(element: Identifier) {
    tagex_excludeElement(element)
}

/**
 * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
 */
fun TagBuilder.excludeOptionalElement(element: Identifier) {
    tagex_excludeOptionalElement(element)
}

/**
 * Excludes a tag from the tag
 */
fun TagBuilder.excludeTag(tag: Identifier) {
    tagex_excludeTag(tag)
}

/**
 * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
 */
fun TagBuilder.excludeOptionalTag(tag: Identifier) {
    tagex_excludeOptionalTag(tag)
}

/**
 * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't initialized
 *
 * Analogous to [FabricProvidedTagBuilder.forceAddTag]
 */
fun TagBuilder.excludeForcedTag(tag: Identifier) {
    tagex_excludeForcedTag(tag)
}

/**
 * Excludes an element from the tag
 */
fun <E: Any, T: Any> TagAppender<E, T>.exclude(block: E) {
    tagex_exclude(block)
}

/**
 * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
 */
fun <E: Any, T: Any> TagAppender<E, T>.excludeOptional(block: E) {
    tagex_excludeOptional(block)
}

/**
 * Excludes a tag from the tag
 */
fun <E: Any, T: Any> TagAppender<E, T>.excludeTag(tag: TagKey<T>) {
    tagex_excludeTag(tag)
}

/**
 * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
 */
fun <E: Any, T: Any> TagAppender<E, T>.excludeOptionalTag(tag: TagKey<T>) {
    tagex_excludeOptionalTag(tag)
}

/**
 * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't initialized
 *
 * Analogous to [FabricProvidedTagBuilder.forceAddTag]
 */
fun <E: Any, T: Any> TagAppender<E, T>.forceExcludeTag(tag: TagKey<T>) {
    tagex_forceExcludeTag(tag)
}
