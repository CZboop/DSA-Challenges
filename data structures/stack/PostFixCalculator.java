import java.util.Stack;

public class PostFixCalculator {
    public static void main(String[] args) {
        System.out.println(postFixCalc("275*+5-"));
    }

//    should work for postfix expressions as long as there's not too many/too few operators, or operators in the wrong place
//    currently otherwise throws exception
    public static int postFixCalc(String expression){
        try {
            Stack<Integer> calcStack = new Stack();
            for (int i = 0; i < expression.length(); i++) {
                char character = expression.charAt(i);
                if (Character.isDigit(character)) {
//                if it's a number add to stack
                    calcStack.push(Character.getNumericValue(character));
// else operator applied to top 2 digits on the stack
                } else {
//                    reverse order due to make operator correct despite lifo
                    int elem2 = calcStack.pop();
                    int elem1 = calcStack.pop();
                    int result;
//                    getting result of operation
                    switch (character) {
                        case '+':
                            result = elem1 + elem2;
                            break;
                        case '-':
                            result = elem1 - elem2;
                            break;
                        case '*':
                            result = elem1 * elem2;
                            break;
                        default:
                            result = elem1 / elem2;
                    }
//                    pushing result to replace the pair of digits that were taken to evaluate
                    calcStack.push(result);
                }
            }
//            case where answer will be wrong but won't be an error - too many digits for operators
            if (calcStack.size()==1){
                return calcStack.pop();
            }
            else {
                throw new IllegalArgumentException("Invalid postfix expression");
            }
//            catching error for trying to pop from empty stack etc
        } catch(Exception e){
            throw new IllegalArgumentException("Invalid postfix expression");
        }
    }
}
