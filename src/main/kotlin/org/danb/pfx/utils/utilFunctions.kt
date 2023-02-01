package org.danb.pfx.utils

import org.danb.pfx.model.common.Modifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): Logger {
    if (T::class.isCompanion) {
        return LoggerFactory.getLogger(T::class.java.enclosingClass)
    }
    return LoggerFactory.getLogger(T::class.java)
}

fun getModifierFromInteger(modifierValue: Int): Modifier {
    return when (modifierValue) {
        64 -> Modifier.Public
        16 -> Modifier.Private
        else -> Modifier.None
    }
}