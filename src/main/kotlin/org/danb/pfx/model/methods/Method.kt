package org.danb.pfx.model.methods

import org.danb.pfx.model.common.ASTObject
import org.danb.pfx.model.common.Modifier

class Method(
    name: String,
    modifier: Modifier,
    var parameters: MutableList<MethodParameter>
) : ASTObject(name, modifier) {
    constructor() : this("", Modifier.None, mutableListOf())
}