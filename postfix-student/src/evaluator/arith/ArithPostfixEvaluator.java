package evaluator.arith;

import language.Operand;
import language.Operator;
import language.arith.SubOperator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;
import language.arith.*;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;
	
	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator(){
        // TODO Initialize to your LinkedStack
		stack = new LinkedStack<Operand<Integer>>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		Integer result=new Integer(0);
		Operator<Integer> operator=null;
		
	    
	    if(expr.length()>2 && !(expr.contains("+") || expr.contains("-") || expr.contains("*") || expr.contains("/") || expr.contains("!")) )
		{
	    	throw new IllegalPostfixExpressionException("string don't contain any operator ");
		}
	    else
	    {
		ArithPostfixParser parser = new ArithPostfixParser(expr);
				
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){ 
			case OPERAND:
			// TODO What do we do when we see an operand?
				stack.push(token.getOperand());
				result=stack.top().getValue();
								break;
			case OPERATOR:
            // TODO What do we do when we see an operator?
				
				switch(token.getOperator().toString())
				{
					case "+" :
							  operator = new PlusOperator();
							  
							  operator.setOperand(0, stack.top());
							  stack.pop();
							  operator.setOperand(1, stack.top()); //2 1
							  result = operator.performOperation().getValue();
							  if(!stack.isEmpty())
							  {    stack.pop();//2
							  	   stack.push(new Operand<Integer>(result));
							  }
							  
				     break;
					 case "-" :
						  operator = new SubOperator();
						  
						  operator.setOperand(1, stack.top());
						  stack.pop();
						  operator.setOperand(0, stack.top()); //2 1
						  result = operator.performOperation().getValue();
						  if(!stack.isEmpty())
						  {    stack.pop();//2
						  	   stack.push(new Operand<Integer>(result));
						  }
						  
			         break;
					 case "*" :
						  operator = new MultOperator();
						  
						  operator.setOperand(0, stack.top());
						  stack.pop();
						  operator.setOperand(1, stack.top()); //2 1
						  result = operator.performOperation().getValue();
						  if(!stack.isEmpty())
						  {    stack.pop();//2
						  	   stack.push(new Operand<Integer>(result));
						  }
						  
				     break;
					 case "/" :
						  operator = new DivOperator();
						  
						  operator.setOperand(0, stack.top());
						  stack.pop();
						  operator.setOperand(1, stack.top()); //2 1
						  result = operator.performOperation().getValue();
						  if(!stack.isEmpty())
						  {    stack.pop();//2
						  	   stack.push(new Operand<Integer>(result));
						  }
						  
				      break;
					 case "!" :
						  operator = new NegateOperator();
						 
						  if(!stack.isEmpty())
						  {
							  operator.setOperand(0, stack.top());
							  result = operator.performOperation().getValue();
							  stack.pop();
							  stack.push(new Operand<Integer>(result));
						  }	  	
			        break;
			        default :
			        	throw new IllegalPostfixExpressionException("parser  returned an invalid operator: " + token.getOperator().toString());
			       //break;
			    
			     
						         	
				}
				
				break;
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}						
		}	
	    }  
		// TODO What do we return?
		//return null;
		return result;
	}

}
