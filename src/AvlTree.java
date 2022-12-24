/*
* Siyanda Mvunyiswa
* AVL Trees
*/


import java.io.*;
import java.util.Scanner;

public class AvlTree {

    private static AvlNode root;

    public AvlTree() {
        root = null;
    }

    public static void insert(Book book) {
        root = insert(book, root);
    }

    //Provided insert
    private static AvlNode insert(Book book, AvlNode t) {
        if (t == null) {
            t = new AvlNode(book);
        } else if (book.getId() < t.getBook().getId()) {
            t.setLeft(insert(book, t.getLeft()));
            if (height(t.getLeft()) - height(t.getRight()) == 2) {
                if (book.getId() < t.getLeft().getBook().getId()) {
                    t = rotLeft(t);
                } else {
                    t = doubleRotLeft(t);
                }
            }
        } else if (book.getId() > t.getBook().getId()) {
            t.setRight(insert(book, t.getRight()));
            if (height(t.getRight()) - height(t.getLeft()) == 2) {
                if (book.getId() > t.getBook().getId()) {
                    t = rotRight(t);
                } else {
                    t = doubleRotRight(t);
                }
            }
        } else {
            //if both book ID's overlap do nothing and skip
        }
        t.setHeight(max(height(t.getLeft()), height(t.getRight())) + 1);
        return t;
    }

    public static void printTree() {
        if (root == null) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    private static void printTree(AvlNode t) {
        if (t != null) {
            printTree(t.getLeft());
            System.out.println(t.getBook().getId());
            printTree(t.getRight());
        }
    }

    private static int height(AvlNode t) {
        return t == null ? -1 : t.getHeight();
    }

    private static int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    //rotates the tree with imbalance on left child
    private static AvlNode rotLeft(AvlNode node) {
        AvlNode current = node.getLeft();  //takes left of root node and assigns to current
        node.setLeft(current.getRight());  //takes the right of our left node and sets it to left of root node
        current.setRight(node);       //sets root node to right of root.left
        return current;             //root.left is the new root and is returned
    }

    //rotates the tree with imbalance on right child
    private static AvlNode rotRight(AvlNode node) {
        AvlNode current = node.getRight();  //takes right of root node and assigns to current
        node.setRight(current.getLeft());   //takes the left of our left node and sets it to right of root node
        current.setLeft(node);            //sets root node to left of root.right
        return current;                 //root.right is the new root and is returned
    }

    //double rotation on parent using left child
    private static AvlNode doubleRotLeft(AvlNode node) {
        node.setLeft(rotRight(node.getLeft()));  //calls rotRight for rotation on left child
        return rotLeft(node);                   //calls rotLeft for rotation on parent
    }

    //double rotation on parent using right child
    private static AvlNode doubleRotRight(AvlNode node) {
        node.setRight(rotLeft(node.getRight()));  //calls rotLeft for rotation on right child
        return rotRight(node);                    //calls rotRight for rotation on parent
    }

    //prints the contents of the AVL Tree to a txt file
    private static void printTreeToFile(AvlNode t, PrintWriter pw) {  //takes an AVL node and PrintWriter type as parameters
        if (t != null) {
            printTreeToFile(t.getLeft(), pw);  //calls printTreeToFile recursively on the left child
            pw.println(t.getBook().getTitle());  //prints the book title on the txt file
            pw.println(t.getBook().getId()       //prints the book id on the txt file
                    + "," + t.getBook().getAuthor()  //prints the book author on the txt file
                    + "," + t.getBook().getPrice());  //prints the book price on the txt file
            printTreeToFile(t.getRight(), pw);   //calls printTreeToFile recursively on the right child
        }
    }

    public static void main(String[] args) {
        try{
            File data = new File("input1.txt");  //accesses the file input1.txt on our path
            Scanner scan = new Scanner(data);             //scans the data in input1.txt

            scan.nextLine();                              //ignores first lines since it's the heading

            while(scan.hasNext()){                        //checks if there is a next line in our txt file, and if there is, execute the loop
                String line = scan.nextLine();            //assigns the first line to variable line as a string
                String[] split = line.split("#");   //splits the line in an array of string with the separator "#"
                String title = split[0];                  //assigns first index of split to title
                int id = Integer.parseInt(split[1]);      //assigns second index as parsed integer of split to id
                String author = split[2];                 //assigns third index of split to author
                double price = Double.parseDouble(split[3]);  //assigns fourth index as parsed double of split to price
                Book newBook = new Book(title, id, author, price);  //creates a book with title, id, author and price as parameters
                insert(newBook);                          //inserts the book into AVL Tree
            }

            printTree(root);                             //prints the AVL Tree with respect to the root node

            FileWriter fw = new FileWriter("output1.txt");  //creates a new txt file, output1.txt, in our directory
            PrintWriter pw = new PrintWriter(fw);                   //prepares the file to print the contents of the AVL Trees

            printTreeToFile(root, pw);                              //calls printTreeToFile with root and the PrintWriter pw as parameters
            pw.close();                                   //closes our txt file
        }
        catch(IOException e){
            System.out.println("File not found/created"); //if the file is not created or found, this should be executed
        }
    }
}
