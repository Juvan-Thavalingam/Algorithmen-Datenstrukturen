package ch.zhaw.ads;

@SuppressWarnings("unchecked")
public class WellformedXmlServer implements CommandExecutor {
    private ListStack listStack;
    private String regex = ".+?";
    private String split = ">";
    private String CLOSE = "/";
    public boolean checkWellformed(String command) {
        listStack = new ListStack();
        String[] block = command.replace("<", "").split(split);

        if (command.matches(regex)){
            for (int i = 0; i < block.length; i++) {
                if(block[i].length() > 2){
                    block[i] = block[i].substring(0, 1);
                }

                if (listStack.isEmpty() && block[i].contains("CLOSE") && !block[0].substring(2).contains("CLOSE")) {
                    return false;
                }

                if (block[i].contains(CLOSE)){
                    if (listStack.isEmpty() || !block[i].contains(listStack.pop().toString())){
                        return block[i].matches("[A-Za-z]+/");
                    }
                } else {
                    listStack.push(block[i]);
                }
            }
        }

        return listStack.isEmpty();
    }
    public String execute(String command) {
        // TODO
        return command;
    }
}