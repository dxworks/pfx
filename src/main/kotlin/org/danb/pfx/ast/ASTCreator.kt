package org.danb.pfx.ast

import org.eclipse.php.core.PHPVersion
import org.eclipse.php.core.ast.nodes.ASTParser

class ASTCreator {

    fun createAst(phpFiles: List<String>) {
        phpFiles.forEach {
            val parser = ASTParser.newParser(PHPVersion.getLatestVersion(), true, true)
            parser.setSource(it.reader())
            val program = parser.createAST(null)
            program.getColumnNumber(0)
        }
//        val parser = ASTParser.newParser(PHPVersion.getLatestVersion(), true, true)
//        parser.createAST(null)
    }

}