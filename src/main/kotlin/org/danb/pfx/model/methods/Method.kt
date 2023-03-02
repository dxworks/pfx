package org.danb.pfx.model.methods

import org.danb.pfx.model.common.ASTObject
import org.danb.pfx.model.common.Modifier
import org.danb.pfx.model.statements.AbstractStatementNode

class Method(
    name: String,
    modifier: Modifier,
    var parameters: MutableList<MethodParameter>,
    var statements: MutableList<AbstractStatementNode>
) : ASTObject(name, modifier) {
    constructor() : this("", Modifier.None, mutableListOf(), mutableListOf())
}