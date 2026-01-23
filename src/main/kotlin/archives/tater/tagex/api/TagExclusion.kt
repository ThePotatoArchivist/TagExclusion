@file:Suppress("unused")

package archives.tater.tagex.api

import net.minecraft.data.tags.TagAppender
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagBuilder
import net.minecraft.tags.TagEntry
import net.minecraft.tags.TagKey

var TagEntry.isExclude
    get() = tagex_exclude()
    set(value) {
        tagex_setExclude(value)
    }

fun TagBuilder.excludeElement(element: Identifier) = apply { tagex_excludeElement(element) }
fun TagBuilder.excludeOptionalElement(element: Identifier) = apply { tagex_excludeOptionalElement(element) }
fun TagBuilder.excludeTag(tag: Identifier) = apply { tagex_excludeTag(tag) }
fun TagBuilder.excludeOptionalTag(tag: Identifier) = apply { tagex_excludeOptionalTag(tag) }
fun TagBuilder.excludeForcedTag(tag: Identifier) = apply { tagex_excludeForcedTag(tag) }

fun <E: Any, T: Any> TagAppender<E, T>.exclude(block: E) = apply { tagex_exclude(block) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeOptional(block: E) = apply { tagex_excludeOptional(block) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeTag(tag: TagKey<T>) = apply { tagex_excludeTag(tag) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeOptionalTag(tag: TagKey<T>) = apply { tagex_excludeOptionalTag(tag) }
fun <E: Any, T: Any> TagAppender<E, T>.forceExcludeTag(tag: TagKey<T>) = apply { tagex_forceExcludeTag(tag) }
