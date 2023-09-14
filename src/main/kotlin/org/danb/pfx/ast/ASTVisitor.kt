package org.danb.pfx.ast

import org.danb.pfx.model.common.*
import org.danb.pfx.model.common.VariableType
import org.danb.pfx.model.methods.Method
import org.danb.pfx.model.methods.MethodParameter
import org.danb.pfx.model.namespace.NamespaceNode
import org.danb.pfx.model.statements.imports.UseStatementNode
import org.danb.pfx.model.statements.imports.UseStatementNodePart
import org.danb.pfx.utils.getModifierFromInteger
import org.eclipse.php.core.ast.nodes.*
import org.eclipse.php.core.ast.visitor.AbstractVisitor

class ASTVisitor : AbstractVisitor() {
    //todo create context companion object with current file, class, method and variable

    lateinit var currentFile: FileModel
    private lateinit var currentClass: Class
    private lateinit var currentMethod: Method
    private lateinit var currentField: Field
    private lateinit var currentVariable: VariableBaseNode

    override fun preVisit(node: ASTNode?) {
        super.preVisit(node)
    }

    override fun postVisit(node: ASTNode?) {
        super.postVisit(node)
    }

    override fun visit(node: ASTNode?): Boolean {
        when (node) {
            is ArrayAccess -> this.visit(node)
            is ArrayCreation -> this.visit(node)
            is Assignment -> this.visit(node)
            is ASTError -> this.visit(node)
            is BackTickExpression -> this.visit(node)
            is Block -> this.visit(node)
            is BreakStatement -> this.visit(node)
            is CastExpression -> this.visit(node)
            is CatchClause -> this.visit(node)
            is StaticConstantAccess -> this.visit(node)
            is ClassDeclaration -> this.visit(node)
            is ClassInstanceCreation -> this.visit(node)
            is ClassName -> this.visit(node)
            is CloneExpression -> this.visit(node)
            is Comment -> this.visit(node)
            is ConditionalExpression -> this.visit(node)
            is ContinueStatement -> this.visit(node)
            is DeclareStatement -> this.visit(node)
            is DoStatement -> this.visit(node)
            is EchoStatement -> this.visit(node)
            is EmptyStatement -> this.visit(node)
            is ExpressionStatement -> this.visit(node)
            is FieldAccess -> this.visit(node)
            is FieldsDeclaration -> this.visit(node)
            is ForEachStatement -> this.visit(node)
            is FunctionDeclaration -> this.visit(node)
            is FunctionInvocation -> this.visit(node)
            is FunctionName -> this.visit(node)
            is GlobalStatement -> this.visit(node)
            is Identifier -> this.visit(node)
            is IfStatement -> this.visit(node)
            is IgnoreError -> this.visit(node)
            is Include -> this.visit(node)
            is InfixExpression -> this.visit(node)
            is InLineHtml -> this.visit(node)
            is InstanceOfExpression -> this.visit(node)
            is InterfaceDeclaration -> this.visit(node)
            is ListVariable -> this.visit(node)
            is MethodDeclaration -> this.visit(node)
            is MethodInvocation -> this.visit(node)
            is PostfixExpression -> this.visit(node)
            is PrefixExpression -> this.visit(node)
            is Program -> this.visit(node)
            is Quote -> this.visit(node)
            is Reference -> this.visit(node)
            is ReflectionVariable -> this.visit(node)
            is ReturnStatement -> this.visit(node)
            is Scalar -> this.visit(node)
            is StaticFieldAccess -> this.visit(node)
            is StaticMethodInvocation -> this.visit(node)
            is StaticStatement -> this.visit(node)
            is SwitchCase -> this.visit(node)
            is SwitchStatement -> this.visit(node)
            is ThrowStatement -> this.visit(node)
            is TryStatement -> this.visit(node)
            is UnaryOperation -> this.visit(node)
            is Variable -> this.visit(node)
            is WhileStatement -> this.visit(node)
            is ParenthesisExpression -> this.visit(node)
            is NamespaceDeclaration -> this.visit(node)
            is UseStatement -> this.visit(node)
            is GotoLabel -> this.visit(node)
            is GotoStatement -> this.visit(node)
            is LambdaFunctionDeclaration -> this.visit(node)
            is YieldExpression -> this.visit(node)
            is FinallyClause -> this.visit(node)
            is EmptyExpression -> this.visit(node)
            is ArrowFunctionDeclaration -> this.visit(node)
            is ArraySpreadElement -> this.visit(node)
            else -> return false
        }
        return true
    }

    override fun visit(arrayAccess: ArrayAccess?): Boolean {
        return super.visit(arrayAccess)
    }

    override fun visit(arrayCreation: ArrayCreation?): Boolean {
        println("Inside array creation visitor")
        arrayCreation?.let {
            when (it.parent) {
                is Assignment -> {
                    this.currentVariable.type = VariableType.Array
                    return true
                }
                else -> {}
            }
            return true
        }
        return false
    }

    override fun visit(arraySpreadElement: ArraySpreadElement?): Boolean {
        return super.visit(arraySpreadElement)
    }

    override fun visit(assignment: Assignment?): Boolean {
        println("Inside assignment visitor")
        assignment?.let {
            val variable = VariableBaseNode()
            this.currentVariable = variable
            if (it.leftHandSide != null) {
                this.visit(it.leftHandSide)
            }
            if (it.rightHandSide != null) {
                this.visit(it.rightHandSide)
            }

            if (!this.currentMethod.variables.containsKey(variable.name) && variable.name.isNotEmpty())  {
                this.currentMethod.variables[variable.name] = this.currentVariable
            }
            return true
        }
        return false
    }

    override fun visit(astError: ASTError?): Boolean {
        return super.visit(astError)
    }

    override fun visit(backTickExpression: BackTickExpression?): Boolean {
        return super.visit(backTickExpression)
    }

    override fun visit(block: Block?): Boolean {
        block?.let {
            it.statements().forEach { statement -> this.visit(statement) }
            return true
        }
        return false
    }

    override fun visit(breakStatement: BreakStatement?): Boolean {
        return super.visit(breakStatement)
    }

    override fun visit(castExpression: CastExpression?): Boolean {
        return super.visit(castExpression)
    }

    override fun visit(catchClause: CatchClause?): Boolean {
        return super.visit(catchClause)
    }

    override fun visit(classConstantDeclaration: ConstantDeclaration?): Boolean {
        return super.visit(classConstantDeclaration)
    }

    override fun visit(classDeclaration: ClassDeclaration?): Boolean {
        println("Inside class declaration")
        classDeclaration?.let { clazz ->
            val classObject = Class()
            classObject.name = clazz.name.name
            if (clazz.parent is Program
                || (clazz.parent is Block && clazz.parent.parent is NamespaceDeclaration)) {
                this.currentFile.includedClass = classObject
            }
            currentClass = classObject
            clazz.body.statements().forEach {
                this.visit(it)
            }
            return true
        }
        return false
    }

    override fun visit(classInstanceCreation: ClassInstanceCreation?): Boolean {
        return super.visit(classInstanceCreation)
    }

    override fun visit(className: ClassName?): Boolean {
        return super.visit(className)
    }

    override fun visit(cloneExpression: CloneExpression?): Boolean {
        return super.visit(cloneExpression)
    }

    override fun visit(comment: Comment?): Boolean {
        return super.visit(comment)
    }

    override fun visit(conditionalExpression: ConditionalExpression?): Boolean {
        return super.visit(conditionalExpression)
    }

    override fun visit(continueStatement: ContinueStatement?): Boolean {
        return super.visit(continueStatement)
    }

    override fun visit(declareStatement: DeclareStatement?): Boolean {
        return super.visit(declareStatement)
    }

    override fun visit(doStatement: DoStatement?): Boolean {
        return super.visit(doStatement)
    }

    override fun visit(echoStatement: EchoStatement?): Boolean {
        return super.visit(echoStatement)
    }

    override fun visit(emptyStatement: EmptyStatement?): Boolean {
        return super.visit(emptyStatement)
    }

    override fun visit(emptyExpression: EmptyExpression?): Boolean {
        return super.visit(emptyExpression)
    }

    override fun visit(expressionStatement: ExpressionStatement?): Boolean {
        println("Inside expression statement visitor")
        expressionStatement?.let {expr ->
            when (expr.expression) {
                is Assignment -> this.visit(expr.expression)
                is MethodInvocation -> this.visit(expressionStatement.expression)
                else -> return true
            }
            return true
        }
        return false
    }

    override fun visit(fieldAccess: FieldAccess?): Boolean {
        return super.visit(fieldAccess)
    }

    override fun visit(fieldsDeclaration: FieldsDeclaration?): Boolean {
        println("Inside fields declaration")
        fieldsDeclaration?.let { fd ->

            fd.fields().forEach { field ->
                val fieldNode = Field()
                fieldNode.modifier = getModifierFromInteger(fd.modifier)
                if (fd.parent is Block && fd.parent.parent is ClassDeclaration) {
                    this.currentClass.fields.add(fieldNode)
                }
                this.currentField = fieldNode
                this.visit(field.name)
                if (field.value != null) {
                    this.visit(field.value)
                }
            }

            return true
        }
        return false
    }

    override fun visit(forEachStatement: ForEachStatement?): Boolean {
        println("Inside for statement visitor")
        forEachStatement?.let {
            this.visit(it.statement)
            return true
        }
        return false
    }

    override fun visit(formalParameter: FormalParameter?): Boolean {
        println("Inside method parameter visitor")
        formalParameter?.let { param ->
            //todo use method binding to determine methods imported from external packages
            val methodParameter = MethodParameter()
            val paramName: Variable = param.parameterName as Variable
            methodParameter.parameterName = (paramName.name as Identifier).name
            methodParameter.isMandatory = param.isMandatory
            methodParameter.isVariadic = param.isVariadic
            if (param.parameterType != null) {
                methodParameter.parameterType = getParameterTypeString(param.parameterType)
            }
            methodParameter.isDollared = paramName.isDollared
            this.currentMethod.parameters.add(methodParameter)
            return true
        }
        return false
    }

    private fun getParameterTypeString(parameterType: ASTNode): String {
        return when(parameterType) {
            is NamespaceName -> parameterType.name
            is Identifier -> parameterType.name
            else -> ""
        }
    }

    override fun visit(forStatement: ForStatement?): Boolean {
        return super.visit(forStatement)
    }

    override fun visit(functionDeclaration: FunctionDeclaration?): Boolean {
        return super.visit(functionDeclaration)
    }

    override fun visit(functionInvocation: FunctionInvocation?): Boolean {
        return super.visit(functionInvocation)
    }

    override fun visit(functionName: FunctionName?): Boolean {
        return super.visit(functionName)
    }

    override fun visit(globalStatement: GlobalStatement?): Boolean {
        return super.visit(globalStatement)
    }

    override fun visit(gotoLabel: GotoLabel?): Boolean {
        return super.visit(gotoLabel)
    }

    override fun visit(gotoStatement: GotoStatement?): Boolean {
        return super.visit(gotoStatement)
    }

    override fun visit(identifier: Identifier?): Boolean {
        return super.visit(identifier)
    }

    override fun visit(ifStatement: IfStatement?): Boolean {
        println("Inside if statement visitor")
        ifStatement?.let {
            if (it.trueStatement != null) {
                this.visit(it.trueStatement)
            }
            if (it.falseStatement != null) {
                this.visit(it.falseStatement)
            }
            return true
        }
        return false
    }

    override fun visit(ignoreError: IgnoreError?): Boolean {
        return super.visit(ignoreError)
    }

    override fun visit(include: Include?): Boolean {
        return super.visit(include)
    }

    override fun visit(infixExpression: InfixExpression?): Boolean {
        return super.visit(infixExpression)
    }

    override fun visit(inLineHtml: InLineHtml?): Boolean {
        return super.visit(inLineHtml)
    }

    override fun visit(instanceOfExpression: InstanceOfExpression?): Boolean {
        return super.visit(instanceOfExpression)
    }

    override fun visit(interfaceDeclaration: InterfaceDeclaration?): Boolean {
        return super.visit(interfaceDeclaration)
    }

    override fun visit(lambdaFunctionDeclaration: LambdaFunctionDeclaration?): Boolean {
        return super.visit(lambdaFunctionDeclaration)
    }

    override fun visit(arrowFunctionDeclaration: ArrowFunctionDeclaration?): Boolean {
        return super.visit(arrowFunctionDeclaration)
    }

    override fun visit(anonymousClassDeclaration: AnonymousClassDeclaration?): Boolean {
        return super.visit(anonymousClassDeclaration)
    }

    override fun visit(listVariable: ListVariable?): Boolean {
        return super.visit(listVariable)
    }

    override fun visit(methodDeclaration: MethodDeclaration?): Boolean {
        println("Inside method declaration visitor")
        methodDeclaration?.let { method ->
            val methodObject = Method()
            val function = method.function
            methodObject.modifier = getModifierFromInteger(method.modifier)
            methodObject.name = function.functionName.name
            if (method.parent is Block && method.parent.parent is ClassDeclaration) {
                this.currentClass.methods.add(methodObject)
            }
            this.currentMethod = methodObject

            function.formalParameters().forEach {
                this.visit(it)
            }
            function.body.statements().forEach {
                this.visit(it)
            }
            return true
        }
        return false
    }

    override fun visit(methodInvocation: MethodInvocation?): Boolean {
        return super.visit(methodInvocation)
    }

    override fun visit(namespaceDeclaration: NamespaceDeclaration?): Boolean {
        println("Inside namespace declaration visitor ${namespaceDeclaration?.ast?.toString()}")
        namespaceDeclaration?.let { namespace ->
            val namespaceNode = NamespaceNode()
            namespace.name.segments().forEach {
                namespaceNode.nameSegments.add(it.name)
            }
            this.currentFile.namespace = namespaceNode
            val statements = namespaceDeclaration.body?.statements()
            statements?.forEach {
                this.visit(it)
            }
            return true
        }
        return false
    }

    override fun visit(namespaceName: NamespaceName?): Boolean {
        return super.visit(namespaceName)
    }

    override fun visit(parenthesisExpression: ParenthesisExpression?): Boolean {
        return super.visit(parenthesisExpression)
    }

    override fun visit(postfixExpression: PostfixExpression?): Boolean {
        return super.visit(postfixExpression)
    }

    override fun visit(prefixExpression: PrefixExpression?): Boolean {
        return super.visit(prefixExpression)
    }

    override fun visit(program: Program?): Boolean {
        println("Inside program visitor: ${program?.ast.toString()}")
        program?.let {
            it.statements().forEach {statement ->
                this.visit(statement)
            }
            return true
        }
        return false
    }

    override fun visit(quote: Quote?): Boolean {
        return super.visit(quote)
    }

    override fun visit(reference: Reference?): Boolean {
        return super.visit(reference)
    }

    override fun visit(reflectionVariable: ReflectionVariable?): Boolean {
        return super.visit(reflectionVariable)
    }

    override fun visit(returnStatement: ReturnStatement?): Boolean {
        println("Inside return statement visitor")
        returnStatement?.let {
            return true
        }
        return false
    }

    override fun visit(returnType: ReturnType?): Boolean {
        return super.visit(returnType)
    }

    override fun visit(YieldExpression: YieldExpression?): Boolean {
        return super.visit(YieldExpression)
    }

    override fun visit(scalar: Scalar?): Boolean {
        println("Inside scalar visitor")
        scalar?.let { sc ->
            when (sc.parent) {
                is SingleFieldDeclaration -> {
                    this.currentField.type = VariableType from sc.scalarType
                    this.currentField.value = sc.stringValue
                }
                is Assignment -> {
                    this.currentVariable.type = VariableType from sc.scalarType
                }
                else -> {}
            }
            return true
        }
        return false
    }

    override fun visit(singleFieldDeclaration: SingleFieldDeclaration?): Boolean {
        return super.visit(singleFieldDeclaration)
    }

    override fun visit(classConstantAccess: StaticConstantAccess?): Boolean {
        return super.visit(classConstantAccess)
    }

    override fun visit(staticFieldAccess: StaticFieldAccess?): Boolean {
        return super.visit(staticFieldAccess)
    }

    override fun visit(staticMethodInvocation: StaticMethodInvocation?): Boolean {
        return super.visit(staticMethodInvocation)
    }

    override fun visit(staticStatement: StaticStatement?): Boolean {
        return super.visit(staticStatement)
    }

    override fun visit(switchCase: SwitchCase?): Boolean {
        return super.visit(switchCase)
    }

    override fun visit(switchStatement: SwitchStatement?): Boolean {
        return super.visit(switchStatement)
    }

    override fun visit(throwStatement: ThrowStatement?): Boolean {
        return super.visit(throwStatement)
    }

    override fun visit(tryStatement: TryStatement?): Boolean {
        return super.visit(tryStatement)
    }

    override fun visit(unaryOperation: UnaryOperation?): Boolean {
        return super.visit(unaryOperation)
    }

    override fun visit(useStatement: UseStatement?): Boolean {
        println("Inside useStatement: $useStatement")
        useStatement?.let { statement ->
            val useStatementNode = UseStatementNode()
            statement.parts().forEach {
                val useStatementNodePart = UseStatementNodePart()
                it.name.segments().forEach { segment ->
                    useStatementNodePart.nameSegments.add(segment.name)
                }
                useStatementNode.statementParts.add(useStatementNodePart)
                this.currentFile.useStatements.add(useStatementNode)
            }
            return true
        }

        return false
    }

    override fun visit(variable: Variable?): Boolean {
        println("Inside variable visitor")
        variable?.let {varb ->
            when (varb.parent) {
                is SingleFieldDeclaration -> {
                    this.currentField.name = (varb.name as Identifier).name
                    this.currentField.isDollared = varb.isDollared
                }
                is Assignment -> {
                    this.currentVariable.name = (varb.name as Identifier).name
                    this.currentVariable.isDollared = varb.isDollared
                }
                else -> {}
            }
            return true
        }
        return false
    }

    override fun visit(whileStatement: WhileStatement?): Boolean {
        return super.visit(whileStatement)
    }

    override fun visit(node: FullyQualifiedTraitMethodReference?): Boolean {
        return super.visit(node)
    }

    override fun visit(node: TraitAlias?): Boolean {
        return super.visit(node)
    }

    override fun visit(node: TraitAliasStatement?): Boolean {
        return super.visit(node)
    }

    override fun visit(node: TraitDeclaration?): Boolean {
        return super.visit(node)
    }

    override fun visit(node: TraitPrecedence?): Boolean {
        return super.visit(node)
    }

    override fun visit(node: TraitPrecedenceStatement?): Boolean {
        return super.visit(node)
    }

    override fun visit(node: TraitUseStatement?): Boolean {
        return super.visit(node)
    }

    override fun visit(node: FinallyClause?): Boolean {
        return super.visit(node)
    }

    override fun endVisit(arrayAccess: ArrayAccess?) {
        super.endVisit(arrayAccess)
    }

    override fun endVisit(arrayCreation: ArrayCreation?) {
        super.endVisit(arrayCreation)
    }

    override fun endVisit(arrayElement: ArrayElement?) {
        super.endVisit(arrayElement)
    }

    override fun endVisit(arraySpreadElement: ArraySpreadElement?) {
        super.endVisit(arraySpreadElement)
    }

    override fun endVisit(astError: ASTError?) {
        super.endVisit(astError)
    }

    override fun endVisit(backTickExpression: BackTickExpression?) {
        super.endVisit(backTickExpression)
    }

    override fun endVisit(block: Block?) {
        super.endVisit(block)
    }

    override fun endVisit(breakStatement: BreakStatement?) {
        super.endVisit(breakStatement)
    }

    override fun endVisit(castExpression: CastExpression?) {
        super.endVisit(castExpression)
    }

    override fun endVisit(catchClause: CatchClause?) {
        super.endVisit(catchClause)
    }

    override fun endVisit(classConstantDeclaration: ConstantDeclaration?) {
        super.endVisit(classConstantDeclaration)
    }

    override fun endVisit(classDeclaration: ClassDeclaration?) {
        super.endVisit(classDeclaration)
    }

    override fun endVisit(classInstanceCreation: ClassInstanceCreation?) {
        super.endVisit(classInstanceCreation)
    }

    override fun endVisit(className: ClassName?) {
        super.endVisit(className)
    }

    override fun endVisit(cloneExpression: CloneExpression?) {
        super.endVisit(cloneExpression)
    }

    override fun endVisit(comment: Comment?) {
        super.endVisit(comment)
    }

    override fun endVisit(conditionalExpression: ConditionalExpression?) {
        super.endVisit(conditionalExpression)
    }

    override fun endVisit(continueStatement: ContinueStatement?) {
        super.endVisit(continueStatement)
    }

    override fun endVisit(declareStatement: DeclareStatement?) {
        super.endVisit(declareStatement)
    }

    override fun endVisit(doStatement: DoStatement?) {
        super.endVisit(doStatement)
    }

    override fun endVisit(echoStatement: EchoStatement?) {
        super.endVisit(echoStatement)
    }

    override fun endVisit(emptyStatement: EmptyStatement?) {
        super.endVisit(emptyStatement)
    }

    override fun endVisit(emptyExpression: EmptyExpression?) {
        super.endVisit(emptyExpression)
    }

    override fun endVisit(expressionStatement: ExpressionStatement?) {
        super.endVisit(expressionStatement)
    }

    override fun endVisit(fieldAccess: FieldAccess?) {
        super.endVisit(fieldAccess)
    }

    override fun endVisit(fieldsDeclaration: FieldsDeclaration?) {
        super.endVisit(fieldsDeclaration)
    }

    override fun endVisit(forEachStatement: ForEachStatement?) {
        super.endVisit(forEachStatement)
    }

    override fun endVisit(formalParameter: FormalParameter?) {
        super.endVisit(formalParameter)
    }

    override fun endVisit(forStatement: ForStatement?) {
        super.endVisit(forStatement)
    }

    override fun endVisit(functionDeclaration: FunctionDeclaration?) {
        super.endVisit(functionDeclaration)
    }

    override fun endVisit(functionInvocation: FunctionInvocation?) {
        super.endVisit(functionInvocation)
    }

    override fun endVisit(functionName: FunctionName?) {
        super.endVisit(functionName)
    }

    override fun endVisit(globalStatement: GlobalStatement?) {
        super.endVisit(globalStatement)
    }

    override fun endVisit(gotoLabel: GotoLabel?) {
        super.endVisit(gotoLabel)
    }

    override fun endVisit(gotoStatement: GotoStatement?) {
        super.endVisit(gotoStatement)
    }

    override fun endVisit(identifier: Identifier?) {
        super.endVisit(identifier)
    }

    override fun endVisit(ifStatement: IfStatement?) {
        super.endVisit(ifStatement)
    }

    override fun endVisit(ignoreError: IgnoreError?) {
        super.endVisit(ignoreError)
    }

    override fun endVisit(include: Include?) {
        super.endVisit(include)
    }

    override fun endVisit(infixExpression: InfixExpression?) {
        super.endVisit(infixExpression)
    }

    override fun endVisit(inLineHtml: InLineHtml?) {
        super.endVisit(inLineHtml)
    }

    override fun endVisit(instanceOfExpression: InstanceOfExpression?) {
        super.endVisit(instanceOfExpression)
    }

    override fun endVisit(interfaceDeclaration: InterfaceDeclaration?) {
        super.endVisit(interfaceDeclaration)
    }

    override fun endVisit(lambdaFunctionDeclaration: LambdaFunctionDeclaration?) {
        super.endVisit(lambdaFunctionDeclaration)
    }

    override fun endVisit(arrowFunctionDeclaration: ArrowFunctionDeclaration?) {
        super.endVisit(arrowFunctionDeclaration)
    }

    override fun endVisit(anonymousClassDeclaration: AnonymousClassDeclaration?) {
        super.endVisit(anonymousClassDeclaration)
    }

    override fun endVisit(listVariable: ListVariable?) {
        super.endVisit(listVariable)
    }

    override fun endVisit(methodDeclaration: MethodDeclaration?) {
        super.endVisit(methodDeclaration)
    }

    override fun endVisit(methodInvocation: MethodInvocation?) {
        super.endVisit(methodInvocation)
    }

    override fun endVisit(namespaceDeclaration: NamespaceDeclaration?) {
        super.endVisit(namespaceDeclaration)
    }

    override fun endVisit(namespaceName: NamespaceName?) {
        super.endVisit(namespaceName)
    }

    override fun endVisit(parenthesisExpression: ParenthesisExpression?) {
        super.endVisit(parenthesisExpression)
    }

    override fun endVisit(postfixExpression: PostfixExpression?) {
        super.endVisit(postfixExpression)
    }

    override fun endVisit(prefixExpression: PrefixExpression?) {
        super.endVisit(prefixExpression)
    }

    override fun endVisit(program: Program?) {
        super.endVisit(program)
    }

    override fun endVisit(quote: Quote?) {
        super.endVisit(quote)
    }

    override fun endVisit(reference: Reference?) {
        super.endVisit(reference)
    }

    override fun endVisit(reflectionVariable: ReflectionVariable?) {
        super.endVisit(reflectionVariable)
    }

    override fun endVisit(returnStatement: ReturnStatement?) {
        super.endVisit(returnStatement)
    }

    override fun endVisit(returnType: ReturnType?) {
        super.endVisit(returnType)
    }

    override fun endVisit(yieldExpression: YieldExpression?) {
        super.endVisit(yieldExpression)
    }

    override fun endVisit(scalar: Scalar?) {
        super.endVisit(scalar)
    }

    override fun endVisit(singleFieldDeclaration: SingleFieldDeclaration?) {
        super.endVisit(singleFieldDeclaration)
    }

    override fun endVisit(staticConstantAccess: StaticConstantAccess?) {
        super.endVisit(staticConstantAccess)
    }

    override fun endVisit(staticFieldAccess: StaticFieldAccess?) {
        super.endVisit(staticFieldAccess)
    }

    override fun endVisit(staticMethodInvocation: StaticMethodInvocation?) {
        super.endVisit(staticMethodInvocation)
    }

    override fun endVisit(staticStatement: StaticStatement?) {
        super.endVisit(staticStatement)
    }

    override fun endVisit(switchCase: SwitchCase?) {
        super.endVisit(switchCase)
    }

    override fun endVisit(switchStatement: SwitchStatement?) {
        super.endVisit(switchStatement)
    }

    override fun endVisit(throwStatement: ThrowStatement?) {
        super.endVisit(throwStatement)
    }

    override fun endVisit(tryStatement: TryStatement?) {
        super.endVisit(tryStatement)
    }

    override fun endVisit(unaryOperation: UnaryOperation?) {
        super.endVisit(unaryOperation)
    }

    override fun endVisit(useStatement: UseStatement?) {
        super.endVisit(useStatement)
    }

    override fun endVisit(variable: Variable?) {
        super.endVisit(variable)
    }

    override fun endVisit(whileStatement: WhileStatement?) {
        super.endVisit(whileStatement)
    }

    override fun endVisit(assignment: Assignment?) {
        super.endVisit(assignment)
    }

    override fun endVisit(node: ASTNode?) {
        super.endVisit(node)
    }

    override fun endVisit(node: FullyQualifiedTraitMethodReference?) {
        super.endVisit(node)
    }

    override fun endVisit(node: TraitAlias?) {
        super.endVisit(node)
    }

    override fun endVisit(node: TraitAliasStatement?) {
        super.endVisit(node)
    }

    override fun endVisit(node: TraitDeclaration?) {
        super.endVisit(node)
    }

    override fun endVisit(node: TraitPrecedence?) {
        super.endVisit(node)
    }

    override fun endVisit(node: TraitPrecedenceStatement?) {
        super.endVisit(node)
    }

    override fun endVisit(node: TraitUseStatement?) {
        super.endVisit(node)
    }

    override fun endVisit(node: FinallyClause?) {
        super.endVisit(node)
    }
}