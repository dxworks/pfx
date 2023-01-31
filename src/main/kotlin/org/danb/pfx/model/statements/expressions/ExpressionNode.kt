package org.danb.pfx.model.statements.expressions

import org.danb.pfx.model.common.Variable

class ExpressionNode(
    var leftHandSide: Variable,
    //todo implement right hand side (can be other variable, lambda function, value, etc)
) {
    constructor() : this(Variable())
}