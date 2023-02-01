package org.danb.pfx.model.common

import org.danb.pfx.model.expressions.ScalarNode
import org.danb.pfx.model.expressions.VariableNode

class Field(
    //todo add an expression value
    var name: VariableNode,
    var modifier: Modifier,
    var value: ScalarNode?
) {
    constructor() : this(VariableNode(), Modifier.None, null)
}