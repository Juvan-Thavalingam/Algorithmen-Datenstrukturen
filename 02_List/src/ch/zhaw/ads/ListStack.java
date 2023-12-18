package ch.zhaw.ads;

import java.util.LinkedList;
import java.util.List;

public class ListStack implements Stack {

    private List stackList;

    public ListStack(){
        removeAll();
    }

    @Override
    public void push(Object x) throws StackOverflowError {
        stackList.add(0,x);
    }

    @Override
    public Object pop() {
        if (isEmpty()){
            return null;
        }
        Object topItem = stackList.get(0);
        stackList.remove(0);
        return topItem;
    }

    @Override
    public boolean isEmpty() {
        if(stackList.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public Object peek() {
        if (isEmpty()){
            return null;
        }
        return stackList.get(0);
    }

    @Override
    public void removeAll() {
        stackList = new LinkedList();
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
