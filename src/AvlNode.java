
class AvlNode {

    private Book book;
    private int height;
    private AvlNode left;
    private AvlNode right;

    public AvlNode(Book book) {
        this.book = book;
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    public Book getBook() {
        return book;
    }

    public int getHeight() {
        return height;
    }

    public AvlNode getLeft() {
        return left;
    }

    public AvlNode getRight() {
        return right;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLeft(AvlNode left) {
        this.left = left;
    }

    public void setRight(AvlNode right) {
        this.right = right;
    }

    public AvlNode(Book book, int height, AvlNode left, AvlNode right) {
        this.book = book;
        this.height = height;
        this.left = left;
        this.right = right;
    }
}
