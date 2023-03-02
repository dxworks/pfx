package org.danb.pfx.model.statements.expressions

import org.danb.pfx.model.expressions.VariableNode

class AssignmentNode(
    var leftHandSide: VariableNode,
    //todo implement right hand side (can be other variable, lambda function, value, etc)
    var rightHandSide: Any
) : ExpressionStatementNode() {
    constructor() : this(VariableNode(), Any())
}