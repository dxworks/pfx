package org.danb.pfx.model.statements

class UseStatementNode(var statementParts: MutableList<UseStatementNodePart>) {
    constructor() : this(mutableListOf())
}