package bstree;

import java.util.Arrays;
import java.util.Random;

public class BST_Tests {
    public static void main(String[] args) { 
    	BST testBST = new BST();
    	int [] values = new int[20];

    	// create test values
        fillRandom(values);

        // create test BST from values
        for(int value : values) {
            testBST.addKey(value);
        }
        System.out.println("testBST created.");

        // print inorder recursively
        System.out.print("inorder (recursive): ");
        testBST.inOrderRec(testBST.root);
        System.out.println();

        // print inorder iteratively
        System.out.print("inorder (iterative): ");
        testBST.inOrder();
        System.out.println();

        // print preorder
        System.out.print("preorder (recursive): ");
        testBST.preOrder(testBST.root);
        System.out.println();

        // print postorder
        System.out.print("postorder (recursive): ");
        testBST.postOrder(testBST.root);
        System.out.println();

        // print level order
        System.out.print("level order (recursive): ");
        testBST.levelOrder(testBST.root);
        System.out.println();

        // print min value
        System.out.println("minimum value: " + testBST.minValue());

        // print max value
        System.out.println("maximum value: " + testBST.maxValue());

        // print height
        System.out.println("tree height: " + testBST.height(testBST.root));

        // is random value a leaf in tree:
        int temp = randomArrayMember(values);
        System.out.println(temp + " is a leaf: " + testBST.isLeaf(testBST.root, temp));
        temp = randomArrayMember(values);
        System.out.println(temp + " is a leaf: " + testBST.isLeaf(testBST.root, temp));

        // iterative add
        temp = (int)(Math.random()*100 + 1);
        testBST.add(temp);
        System.out.println(temp + " added iteratively to testBST.");

        // recursive remove
        temp = randomArrayMember(values);
        testBST.removeKey(temp);
        System.out.println(temp + " recursively removed from testBST.");

        // iterative remove
        temp = randomArrayMember(values);
        testBST.remove(temp);
        System.out.println(temp + " iteratively removed from testBST.");

        // print final form
        System.out.print("level order: ");
        testBST.levelOrder(testBST.root);
        System.out.println();

    }

    private static void fillRandom(int[] array) {
        for (int i = 0; i < array.length ; i++) {
            array[i] = (int)(Math.random()*100 + 1);
        }
        System.out.println("Numbers generated: " + Arrays.toString(array));
    }

    private static int randomArrayMember(int[] array) {
        int random = new Random().nextInt(array.length);
        return array[random];
    }
}
