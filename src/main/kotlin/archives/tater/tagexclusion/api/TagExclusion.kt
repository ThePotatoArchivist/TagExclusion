@file:Suppress("unused")

package archives.tater.tagexclusion.api

import net.minecraft.data.tags.TagAppender
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagBuilder
import net.minecraft.tags.TagEntry
import net.minecraft.tags.TagKey

var TagEntry.isExclude
    get() = tagexclusion_exclude()
    set(value) {
        tagexclusion_setExclude(value)
    }

fun TagBuilder.excludeElement(element: Identifier) = apply { tagexclusion_excludeElement(element) }
fun TagBuilder.excludeOptionalElement(element: Identifier) = apply { tagexclusion_excludeOptionalElement(element) }
fun TagBuilder.excludeTag(tag: Identifier) = apply { tagexclusion_excludeTag(tag) }
fun TagBuilder.excludeOptionalTag(tag: Identifier) = apply { tagexclusion_excludeOptionalTag(tag) }
fun TagBuilder.excludeForcedTag(tag: Identifier) = apply { tagexclusion_excludeForcedTag(tag) }

fun <E: Any, T: Any> TagAppender<E, T>.exclude(block: E) = apply { tagexclusion_exclude(block) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeOptional(block: E) = apply { tagexclusion_excludeOptional(block) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeTag(tag: TagKey<T>) = apply { tagexclusion_excludeTag(tag) }
fun <E: Any, T: Any> TagAppender<E, T>.excludeOptionalTag(tag: TagKey<T>) = apply { tagexclusion_excludeOptionalTag(tag) }
fun <E: Any, T: Any> TagAppender<E, T>.forceExcludeTag(tag: TagKey<T>) = apply { tagexclusion_forceExcludeTag(tag) }
