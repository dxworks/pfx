package org.danb.pfx.model.methods

import org.danb.pfx.model.common.ASTObject
import org.danb.pfx.model.common.Modifier
import org.danb.pfx.model.common.VariableBaseNode

class Method(
    name: String,
    modifier: Modifier,
    var parameters: MutableList<MethodParameter>,
    //todo refactor statement into declared variables and called methods
    //todo add return type if exists
    var variables: MutableMap<String, VariableBaseNode>
) : ASTObject(name, modifier) {
    constructor() : this("", Modifier.None, mutableListOf(), mutableMapOf())
}