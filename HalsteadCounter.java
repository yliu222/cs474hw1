/** CS474
 * Ying liu
 * This is the counter function program to support metrics.java program to find the sum and search keyword.
 * Created by Yliu222 on 9/17/14.
 */
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.*;


// declare variables
public class HalsteadCounter {
    int operators = 0;
    int operands = 0;
    Map<String,Integer> operatorMap;
    Map<String,Integer> operandMap;


    public HalsteadCounter() {
        operatorMap = new HashMap<String,Integer>();
        operandMap = new HashMap<String,Integer>();
        operators = 0;
        operands = 0;
    }

    // count functions
    void increment( Map<String,Integer> m, String s ) {
        int count = ( m.containsKey( s ) ) ? m.get(s) : 0;
        m.put( s, count + 1 );
    }

    // find sum of operators
    public void addOperator( Token token ) {
        operators++;
        increment( operatorMap, token.getText() );
    }

    //find sum of operands
    public void addOperand( Token token ) {
        operands++;
        increment( operandMap, token.getText() );
    }

    // counts a token as operator or operand
    public void countToken( Token token ) {
        switch(token.getType()) {


            // identifiers
            case JavaLexer.IDENT:

                // literals/constants
            case JavaLexer.FLOATING_POINT_LITERAL:
            case JavaLexer.DECIMAL_LITERAL:
            case JavaLexer.OCTAL_LITERAL:
            case JavaLexer.HEX_LITERAL:
            case JavaLexer.STRING_LITERAL:
            case JavaLexer.CHARACTER_LITERAL:
            case JavaLexer.NULL:
            case JavaLexer.TRUE:

                // more names of operands
            case JavaLexer.SUPER:
            case JavaLexer.THIS:
            case JavaLexer.FALSE:
                addOperand( token );
                break;


           // operators keyword
            case JavaLexer.PACKAGE:
            case JavaLexer.CASE:
            case JavaLexer.CHAR:
            case JavaLexer.NEW:
            case JavaLexer.FINAL:
            case JavaLexer.IMPORT:
            case JavaLexer.VOID:
            case JavaLexer.RETURN:
            case JavaLexer.IMPLEMENTS:
            case JavaLexer.CONTINUE:
            case JavaLexer.TRANSIENT:
            case JavaLexer.DOUBLE:
            case JavaLexer.PRIVATE:
            case JavaLexer.STATIC:
            case JavaLexer.SWITCH:
            case JavaLexer.BREAK:
            case JavaLexer.ELSE:
            case JavaLexer.STRICTFP:
            case JavaLexer.NATIVE:
            case JavaLexer.THROWS:
            case JavaLexer.INT:
            case JavaLexer.ASSERT:
            case JavaLexer.TRY:
            case JavaLexer.INTERFACE:
            case JavaLexer.LONG:
            case JavaLexer.PUBLIC:
            case JavaLexer.EXTENDS:
            case JavaLexer.FOR_EACH:
            case JavaLexer.BYTE:
            case JavaLexer.VOLATILE:
            case JavaLexer.SHORT:
            case JavaLexer.INSTANCEOF:
            case JavaLexer.ENUM:
            case JavaLexer.FINALLY:
            case JavaLexer.DEFAULT:
            case JavaLexer.CATCH:
            case JavaLexer.THROW:
            case JavaLexer.PROTECTED:
            case JavaLexer.CLASS:
            case JavaLexer.FLOAT:
            case JavaLexer.ABSTRACT:
            case JavaLexer.BOOLEAN:
            case JavaLexer.SYNCHRONIZED:

                // period signs
            case JavaLexer.DOT:
            case JavaLexer.ELLIPSIS:
            case JavaLexer.COMMA:

                // arithmetic operators
            case JavaLexer.PLUS:
            case JavaLexer.MINUS:
            case JavaLexer.EXPONENT:
            case JavaLexer.STAR:
            case JavaLexer.DIV:
            case JavaLexer.MOD:

            case JavaLexer.UNARY_PLUS:
            case JavaLexer.UNARY_MINUS:

            case JavaLexer.INC:
            case JavaLexer.PRE_INC:
            case JavaLexer.POST_INC:

            case JavaLexer.DEC:
            case JavaLexer.PRE_DEC:
            case JavaLexer.POST_DEC:

            case JavaLexer.AND:
            case JavaLexer.OR:
            case JavaLexer.NOT:
            case JavaLexer.LOGICAL_AND:
            case JavaLexer.LOGICAL_OR:
            case JavaLexer.LOGICAL_NOT:
            case JavaLexer.XOR:

            case JavaLexer.SHIFT_LEFT:
            case JavaLexer.SHIFT_RIGHT:

            case JavaLexer.BIT_SHIFT_RIGHT:

                // comparison operators
            case JavaLexer.EQUAL:
            case JavaLexer.NOT_EQUAL:
            case JavaLexer.LESS_THAN:
            case JavaLexer.GREATER_THAN:
            case JavaLexer.LESS_OR_EQUAL:
            case JavaLexer.GREATER_OR_EQUAL:

                // assignments
            case JavaLexer.ASSIGN:
            case JavaLexer.PLUS_ASSIGN:
            case JavaLexer.MINUS_ASSIGN:
            case JavaLexer.STAR_ASSIGN:
            case JavaLexer.DIV_ASSIGN:
            case JavaLexer.MOD_ASSIGN:

            case JavaLexer.BIT_SHIFT_RIGHT_ASSIGN:
            case JavaLexer.SHIFT_LEFT_ASSIGN:
            case JavaLexer.SHIFT_RIGHT_ASSIGN:
            case JavaLexer.OR_ASSIGN:
            case JavaLexer.AND_ASSIGN:
            case JavaLexer.XOR_ASSIGN:

                // branches
            case JavaLexer.IF:

                // loops
            case JavaLexer.WHILE:
            case JavaLexer.DO:
            case JavaLexer.FOR:

                // will be counted
            case JavaLexer.CAST_EXPR:
            case JavaLexer.QUESTION:
            case JavaLexer.AT:
            case JavaLexer.DOTSTAR:
            case JavaLexer.SEMI:
            case JavaLexer.COLON:
                addOperator( token );
                break;


            // count comments
            case JavaLexer.COMMENT:
            case JavaLexer.LINE_COMMENT:
            case JavaLexer.WS:

                // parenthesis
            case JavaLexer.LPAREN:
            case JavaLexer.RPAREN:
            case JavaLexer.LBRACK:
            case JavaLexer.RBRACK:
            case JavaLexer.LCURLY:
            case JavaLexer.RCURLY:


        }
    }

    public Halstead.HalsteadResults calculateHalstead() {
        Halstead halstead = new Halstead();
        return halstead.calculateMetrics( operatorMap, operandMap );
    }


}
