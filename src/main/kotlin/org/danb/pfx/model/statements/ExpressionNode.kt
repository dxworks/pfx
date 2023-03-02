package org.danb.pfx.model.statements

import org.danb.pfx.model.expressions.VariableNode

class ExpressionNode(
    var leftHandSide: VariableNode,
    //todo implement right hand side (can be other variable, lambda function, value, etc)
) {
    constructor() : this(VariableNode())
}