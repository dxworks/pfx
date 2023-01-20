package org.danb.pfx.model.statements.imports

class UseStatementNode(var statementParts: MutableList<UseStatementNodePart>) {
    constructor() : this(mutableListOf())
}