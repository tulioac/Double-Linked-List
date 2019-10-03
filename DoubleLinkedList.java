class DoubleLinkedList {

    private Node head;

    public DoubleLinkedList() {
        this.head = new Node(null, new Node(), null);
    }

    public static void main(String[] args) {
        DoubleLinkedList linked = new DoubleLinkedList();

        System.out.println(linked);
        linked.addLast(5);
        System.out.println(linked);
        linked.addLast(6);
        System.out.println(linked);
        linked.addLast(2);
        System.out.println(linked);
        linked.add(0, 7);
        System.out.println(linked);
        System.out.println();
        linked.remove(1);
       System.out.println(linked);
    }

    // Recursivos
    public void addLast(int elemento) {
        if (this.head.isNil()) {
            this.head.setValue(elemento);
        } else {
            this.addLast(this.head, elemento);
        }
    }

    private void addLast(Node node, int elemento) {
        if (node.isNil()) {
            node.setValue(elemento);
            node.setNext(new Node());
        } else {
            addLast(node.getNext(), elemento);
        }
    }

    public void addFirst(int elemento) {
        if (this.head.isNil())
            this.head.setValue(elemento);
        else {
            Node newNode = new Node(this.head.getValue(), this.head.getNext(), this.head);

            this.head.setValue(elemento);
            this.head.setNext(newNode);
        }
    }

    public int size() {
        return this.size(this.head);
    }

    private int size(Node node) {
        if (node.isNil())
            return 0;

        return 1 + this.size(node.getNext());
    }

    public int soma() {
        return this.soma(this.head);
    }

    private int soma(Node node) {
        if (node.isNil())
            return 0;

        return node.getValue() + this.soma(node.getNext());
    }

    public boolean isSorted() {
        return this.isSorted(this.head);
    }

    private boolean isSorted(Node node) {
        if (node.isNil() || node.getNext().isNil())
            return true;

        if (node.getValue() < node.getNext().getValue())
            return this.isSorted(node.getNext());

        return false;
    }

    @Override
    public String toString() {
        return this.toString(this.head);
    }

    private String toString(Node node) {
        if (node.isNil())
            return "";

        return node.getValue() + " " + this.toString(node.getNext());
    }

    // Iterativos
    public void add(int index, int elemento) {
        if (index == this.size()) {
            addLast(elemento);
        } else if(index == 0) {
            addFirst(elemento);
        } else if (index > 0 && index < this.size()) {
            Node node = this.head;

            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }

            Node next = node.getNext();

            Node newNode = new Node(elemento, next, node);

            next.setPrevious(newNode);
            node.setNext(newNode);
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < this.size() && !this.head.isNil()) {

            if (index == 0) {
                this.head = this.head.getNext();
            } else {


                Node previous = this.head;

                for (int i = 0; i < index - 1; i++) {
                    previous = previous.getNext();
                }

                Node toRemove = previous.getNext();
                Node next = toRemove.getNext();

                previous.setNext(next);
                next.setPrevious(previous);
            }
        }
    }

}

class Node {

    private Integer value;
    private Node next;
    private Node previous;

    public Node(Integer value, Node next, Node previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public Node() {
        this(null, null, null);
    }

    public boolean isNil() {
        return this.value == null;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return this.previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Valor " + this.getValue();
    }
}
