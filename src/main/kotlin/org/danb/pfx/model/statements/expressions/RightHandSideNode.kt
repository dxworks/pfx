package org.danb.pfx.model.statements.expressions

import org.danb.pfx.model.expressions.ScalarNode

sealed class RightHandSideNode<T> (
    val value: T
) {
    class ScalarValue(value: ScalarNode) : RightHandSideNode<ScalarNode>(value)
}