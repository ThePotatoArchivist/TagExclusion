@file:Suppress("unused")

package archives.tater.tagex.api

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FabricTagBuilder
import net.minecraft.data.tags.TagsProvider.TagAppender
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagBuilder
import net.minecraft.tags.TagKey

/**
 * Extension function wrappers for interface-injected methods so you can call the methods without having to write
 * `tagex_` at the beginning
 */

/**
 * Excludes an element from the tag
 */
fun TagBuilder.excludeElement(element: ResourceLocation) {
    tagex_excludeElement(element)
}

/**
 * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
 */
fun TagBuilder.excludeOptionalElement(element: ResourceLocation) {
    tagex_excludeOptionalElement(element)
}

/**
 * Excludes a tag from the tag
 */
fun TagBuilder.excludeTag(tag: ResourceLocation) {
    tagex_excludeTag(tag)
}

/**
 * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
 */
fun TagBuilder.excludeOptionalTag(tag: ResourceLocation) {
    tagex_excludeOptionalTag(tag)
}

/**
 * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't initialized
 *
 * Analogous to [FabricTagBuilder.forceAddTag]
 */
fun TagBuilder.excludeForcedTag(tag: ResourceLocation) {
    tagex_excludeForcedTag(tag)
}

/**
 * Excludes an element from the tag
 */
fun <T: Any> TagAppender<T>.exclude(key: ResourceKey<T>) {
    tagex_exclude(key)
}

/**
 * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
 */
fun <T: Any> TagAppender<T>.excludeOptional(key: ResourceKey<T>) {
    tagex_excludeOptional(key)
}

/**
 * Excludes a tag from the tag
 */
fun <T: Any> TagAppender<T>.excludeTag(tag: TagKey<T>) {
    tagex_excludeTag(tag)
}

/**
 * Excludes an optional tag from the tag, so it does not fail at runtime if the element does not exist
 */
fun <T: Any> TagAppender<T>.excludeOptionalTag(tag: TagKey<T>) {
    tagex_excludeOptionalTag(tag)
}

/**
 * Force-excludes a tag from the tag, so it does not fail during datagen if the tag isn't initialized
 *
 * Analogous to [FabricTagBuilder.forceAddTag]
 */
fun <T: Any> TagAppender<T>.forceExcludeTag(tag: TagKey<T>) {
    tagex_forceExcludeTag(tag)
}

/**
 * Excludes an element from the tag
 */
fun <T: Any> ExcludableTagBuilder<T>.exclude(value: T) {
    tagex_exclude(value)
}

/**
 * Excludes an optional element from the tag, so it does not fail at runtime if the element does not exist
 */
fun <T: Any> ExcludableTagBuilder<T>.excludeOptional(value: T) {
    tagex_excludeOptional(value)
}

fun <T: Any> FabricTagProvider<T>.getOrCreateExcludableBuilder(tag: TagKey<T>): ExcludableTagBuilder<T> =
    ExcludableTagBuilder.getOrCreate(this, tag)