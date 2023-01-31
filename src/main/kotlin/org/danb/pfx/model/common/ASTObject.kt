package org.danb.pfx.model.common

abstract class ASTObject(
    var name: String,
    var modifier: Modifier
) {
    fun isPublic() = Modifier.Public == modifier

    fun isProtected() = Modifier.Protected == modifier

    fun isPrivate() = Modifier.Private == modifier

    fun isAbstract() = Modifier.Abstract == modifier
}