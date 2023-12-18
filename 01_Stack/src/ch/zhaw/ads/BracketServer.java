package ch.zhaw.ads;

public class BracketServer implements CommandExecutor {

    public static final String BRACKETS = "[{()}]";
    public static final String END = ")}]";
    private int index;
    private ListStack listStack;

    public BracketServer(){
        listStack = new ListStack();
    }

    public boolean checkBrackets(String content) {
        index = 0;

        while(index <= content.length()-1){
            String check = getNextBracket(content);
            if(END.contains(check)){
                if(check.equals(")") && !listStack.pop().toString().equals("(") ||
                        check.equals("}") && !listStack.pop().toString().equals("{") ||
                        check.equals("]") && !listStack.pop().toString().equals("[")){
                    return false;
                }
            } else {
                listStack.push(check);
            }
        }
        return true;
    }

    public String getNextBracket(String content){
        for (int i = index; i < content.length(); i++){
            if(BRACKETS.contains(String.valueOf(content.charAt(i)))){
                index = i+1;
                return String.valueOf(content.charAt(i));
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