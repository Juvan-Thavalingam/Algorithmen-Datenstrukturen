package ch.zhaw.ads;

public class BracketServer implements CommandExecutor {

    public static final String BRACKETS = "<[{()}]>";
    public static final String SPECIAL = "/**/";
    public static final String END = ")}]*/>";
    private int index;
    private ListStack listStack;

    public boolean checkBrackets(String content) {
        index = 0;
        listStack = new ListStack();

        while(index <= content.length()-1){
            String check = getNextBracket(content);
            if(END.contains(check)){
                if(!listStack.isEmpty()){
                    if(check.equals(")") && !listStack.pop().toString().equals("(") ||
                            check.equals("}") && !listStack.pop().toString().equals("{") ||
                            check.equals("]") && !listStack.pop().toString().equals("[") ||
                            check.equals(">") && !listStack.pop().toString().equals("<") ||
                            check.equals("*/") && !listStack.pop().toString().equals("/*")){
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                listStack.push(check);
            }
        }
        return listStack.isEmpty();
    }

    public String getNextBracket(String content){
        for (int i = index; i < content.length(); i++){
            if(BRACKETS.contains(String.valueOf(content.charAt(i)))){
                index = i+1;
                return String.valueOf(content.charAt(i));
            } else if(SPECIAL.contains(content.substring(i , i+2))){
                index = i+2;
                return content.substring(i,i+2);
            }
        }
        index = content.length();
        return null;
    }

    @Override
    public String execute(String command) throws Exception {
        return null;
    }
}