package org.danb.pfx.model.common

class Method(
    name: String,
    modifier: Modifier
) : ASTObject(name, modifier) {
    constructor() : this("", Modifier.None)
}