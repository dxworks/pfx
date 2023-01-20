package org.danb.pfx.model.common

import org.danb.pfx.model.common.Modifier

abstract class ASTObject(
    var name: String,
    var modifier: Modifier
) {
    fun isPublic() = Modifier.Public == modifier

    fun isProtected() = Modifier.Protected == modifier

    fun isPrivate() = Modifier.Private == modifier
}