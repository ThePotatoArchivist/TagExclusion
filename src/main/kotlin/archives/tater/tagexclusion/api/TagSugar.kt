@file:Suppress("NOTHING_TO_INLINE", "unused")

package archives.tater.tagexclusion.api

import net.minecraft.data.tags.TagAppender
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagBuilder
import net.minecraft.tags.TagKey

inline fun TagBuilder.excludeElement(element: Identifier) = tagexclusion_excludeElement(element)
inline fun TagBuilder.excludeOptionalElement(element: Identifier) = tagexclusion_excludeOptionalElement(element)
inline fun TagBuilder.excludeTag(tag: Identifier) = tagexclusion_excludeTag(tag)
inline fun TagBuilder.excludeOptionalTag(tag: Identifier) = tagexclusion_excludeOptionalTag(tag)
inline fun TagBuilder.excludeForcedTag(tag: Identifier) = tagexclusion_excludeForcedTag(tag)

inline fun <E: Any, T: Any> TagAppender<E, T>.exclude(block: E) = tagexclusion_exclude(block)
inline fun <E: Any, T: Any> TagAppender<E, T>.excludeOptional(block: E) = tagexclusion_excludeOptional(block)
inline fun <E: Any, T: Any> TagAppender<E, T>.excludeTag(tag: TagKey<T>) = tagexclusion_excludeTag(tag)
inline fun <E: Any, T: Any> TagAppender<E, T>.excludeOptionalTag(tag: TagKey<T>) = tagexclusion_excludeOptionalTag(tag)
inline fun <E: Any, T: Any> TagAppender<E, T>.forceExcludeTag(tag: TagKey<T>) = tagexclusion_forceExcludeTag(tag)
