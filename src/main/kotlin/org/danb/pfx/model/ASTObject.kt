package org.danb.pfx.model

abstract class ASTObject(
    var name: String,
    var modifier: Modifier
) {
    fun isPublic() = Modifier.Public == modifier

    fun isProtected() = Modifier.Protected == modifier

    fun isPrivate() = Modifier.Private == modifier
}