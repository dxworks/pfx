package org.danb.pfx.ast

import org.eclipse.php.core.PHPVersion
import org.eclipse.php.core.ast.nodes.*
import java.io.FileReader

class ASTCreator {

    private val visitor = ASTVisitor()

    fun createAst(phpFiles: List<String>) {
        phpFiles.forEach {
            val parser = ASTParser.newParser(PHPVersion.getLatestVersion(), true, true)
            parser.setSource(FileReader(it))
            val program = parser.createAST(null)
            parsePhpFile(program)
        }
    }

    private fun parsePhpFile(program: Program) {
        program.statements().forEach {
            visitBasedOnType(it)
            //todo also figure out a way to cast the children to the corresponding node
        }
    }

    private fun visitBasedOnType(node: ASTNode) {
        when (node) {
            is ArrayAccess -> visitor.visit(node)
            is ArrayCreation -> visitor.visit(node)
            is ArrayElement -> visitor.visit(node)
            is Assignment -> visitor.visit(node)
            is ASTError -> visitor.visit(node)
            is BackTickExpression -> visitor.visit(node)
            is Block -> visitor.visit(node)
            is BreakStatement -> visitor.visit(node)
            is CastExpression -> visitor.visit(node)
            is CatchClause -> visitor.visit(node)
            is StaticConstantAccess -> visitor.visit(node)
            is ClassDeclaration -> visitor.visit(node)
            is ClassInstanceCreation -> visitor.visit(node)
            is ClassName -> visitor.visit(node)
            is CloneExpression -> visitor.visit(node)
            is Comment -> visitor.visit(node)
            is ConditionalExpression -> visitor.visit(node)
            is ContinueStatement -> visitor.visit(node)
            is DeclareStatement -> visitor.visit(node)
            is DoStatement -> visitor.visit(node)
            is EchoStatement -> visitor.visit(node)
            is EmptyStatement -> visitor.visit(node)
            is ExpressionStatement -> visitor.visit(node)
            is FieldAccess -> visitor.visit(node)
            is FieldsDeclaration -> visitor.visit(node)
            is ForEachStatement -> visitor.visit(node)
            is FunctionDeclaration -> visitor.visit(node)
            is FunctionInvocation -> visitor.visit(node)
            is FunctionName -> visitor.visit(node)
            is GlobalStatement -> visitor.visit(node)
            is Identifier -> visitor.visit(node)
            is IfStatement -> visitor.visit(node)
            is IgnoreError -> visitor.visit(node)
            is Include -> visitor.visit(node)
            is InfixExpression -> visitor.visit(node)
            is InLineHtml -> visitor.visit(node)
            is InstanceOfExpression -> visitor.visit(node)
            is InterfaceDeclaration -> visitor.visit(node)
            is ListVariable -> visitor.visit(node)
            is MethodDeclaration -> visitor.visit(node)
            is MethodInvocation -> visitor.visit(node)
            is PostfixExpression -> visitor.visit(node)
            is PrefixExpression -> visitor.visit(node)
            is Program -> visitor.visit(node)
            is Quote -> visitor.visit(node)
            is Reference -> visitor.visit(node)
            is ReflectionVariable -> visitor.visit(node)
            is ReturnStatement -> visitor.visit(node)
            is Scalar -> visitor.visit(node)
            is StaticFieldAccess -> visitor.visit(node)
            is StaticMethodInvocation -> visitor.visit(node)
            is StaticStatement -> visitor.visit(node)
            is SwitchCase -> visitor.visit(node)
            is SwitchStatement -> visitor.visit(node)
            is ThrowStatement -> visitor.visit(node)
            is TryStatement -> visitor.visit(node)
            is UnaryOperation -> visitor.visit(node)
            is Variable -> visitor.visit(node)
            is WhileStatement -> visitor.visit(node)
            is ParenthesisExpression -> visitor.visit(node)
            is NamespaceDeclaration -> visitor.visit(node)
            is UseStatement -> visitor.visit(node)
            is GotoLabel -> visitor.visit(node)
            is GotoStatement -> visitor.visit(node)
            is LambdaFunctionDeclaration -> visitor.visit(node)
            is YieldExpression -> visitor.visit(node)
            is FinallyClause -> visitor.visit(node)
            is EmptyExpression -> visitor.visit(node)
            is ArrowFunctionDeclaration -> visitor.visit(node)
            is ArraySpreadElement -> visitor.visit(node)
        }
    }

}