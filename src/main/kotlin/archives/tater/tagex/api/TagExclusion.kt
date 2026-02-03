@file:Suppress("unused")

package archives.tater.tagex.api

import net.minecraft.data.tags.TagAppender
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagBuilder
import net.minecraft.tags.TagKey

fun TagBuilder.excludeElement(element: Identifier) { tagex_excludeElement(element) }
fun TagBuilder.excludeOptionalElement(element: Identifier) { tagex_excludeOptionalElement(element) }
fun TagBuilder.excludeTag(tag: Identifier) { tagex_excludeTag(tag) }
fun TagBuilder.excludeOptionalTag(tag: Identifier) { tagex_excludeOptionalTag(tag) }
fun TagBuilder.excludeForcedTag(tag: Identifier) { tagex_excludeForcedTag(tag) }

fun <E: Any, T: Any> TagAppender<E, T>.exclude(block: E) { tagex_exclude(block) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeOptional(block: E) { tagex_excludeOptional(block) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeTag(tag: TagKey<T>) { tagex_excludeTag(tag) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeOptionalTag(tag: TagKey<T>) { tagex_excludeOptionalTag(tag) }
fun <E: Any, T: Any> TagAppender<E, T>.forceExcludeTag(tag: TagKey<T>) { tagex_forceExcludeTag(tag) }
