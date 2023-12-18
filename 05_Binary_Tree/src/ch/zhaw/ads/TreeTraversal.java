package ch.zhaw.ads;

import java.util.LinkedList;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {
    private final TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    public void inorder(Visitor<T> vis) {
        if(root != null) {
            new TreeTraversal<T>(root.left).inorder(vis);
            vis.visit(root.getValue());
            new TreeTraversal<T>(root.right).inorder(vis);
        }
    }

    public void preorder(Visitor<T> vis) {
        if(root != null) {
            vis.visit(root.getValue());
            new TreeTraversal<T>(root.left).preorder(vis);
            new TreeTraversal<T>(root.right).preorder(vis);
        }
    }

    public void postorder(Visitor<T> vis) {
        if(root != null) {
            new TreeTraversal<T>(root.left).postorder(vis);
            new TreeTraversal<T>(root.right).postorder(vis);
            vis.visit(root.getValue());
        }
    }

    @Override
    public void levelorder(Visitor<T> vistor) {
        LinkedList<TreeNode<T>> listQue = new LinkedList<>();
        listQue.add(root);

        while (!listQue.isEmpty()){
            if(listQue.getFirst() != null){
                vistor.visit(listQue.getFirst().getValue());
                listQue.addLast(listQue.getFirst().left);
                listQue.addLast(listQue.getFirst().right);
            }
            listQue.removeFirst();
        }
    }

    @Override
    public void interval(T min, T max, Visitor<T> vistor) {
        if(root != null){
            boolean minBool = root.getValue().compareTo(min) >= 0;
            boolean maxBool = root.getValue().compareTo(max) <= 0;

            if (minBool && maxBool){
                vistor.visit(root.getValue());
            }
            if(minBool){
                new TreeTraversal<T>(root.left).interval(min, max, vistor);
            }
            if(maxBool){
                new TreeTraversal<T>(root.right).interval(min, max, vistor);
            }
        }

    }
}
