package ua.edu.ucu.queuePackage;

public class ImmutableLinkedList implements ImmutableList {
    private class Node {
        private Object element = null;
        private Node next = null;
        private Node previous = null;

        Node(Object element, Node next, Node previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }

        public void setElement(Object newElement) {
            this.element = newElement;
        }

        public void setNext(Node nextNode) {
            this.next = nextNode;
        }

        public void setPrevious(Node previousNode) {
            this.previous = previousNode;
        }

        public Object getElement() {
            return this.element;
        }

        public Node getNext() {
            return this.next;
        }

        public Node getPrevious() {
            return this.previous;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public ImmutableLinkedList(Object[] elems) {
        for (Object element : elems) {
            Node node = new Node(element, null, null);
            if (this.size != 0) {
                this.tail.setNext(node);
            }
            else if (this.size == 0) {
                this.head = node;
            }
            size++;
            this.tail = node;
        }
    }

    @Override
    public ImmutableLinkedList add(Object a) {
        Object[] newList = new Object[this.size + 1];
        newList[this.size] = a;
        Object[] currentList = this.toArray();
        if (this.isEmpty() == false) {
            System.arraycopy(currentList, 0, newList,
                    0, this.size);
        }
        return new ImmutableLinkedList(newList);
    }

    @Override
    public ImmutableLinkedList add(int index, Object elem)
            throws IndexOutOfBoundsException {
        return addAll(index, new Object[] {elem});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] a) {
        return addAll(this.size, a);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] a)
            throws IndexOutOfBoundsException {
        if ((index == 0) == false) {
            if ((index - 1 >= size) || (index < 0)) {
                throw new IndexOutOfBoundsException();
            }
        }
        Object[] newList = new Object[this.size + a.length];
        Object[] currentList = this.toArray();
        System.arraycopy(currentList, 0, newList,
                0, index);
        System.arraycopy(currentList, index, newList,
                index + a.length,
                this.size - index);
        System.arraycopy(a, 0, newList, index, a.length);
        return new ImmutableLinkedList(newList);
    }

    @Override
    public Object get(int index)
            throws IndexOutOfBoundsException {
        if ((index >= size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
        Node current = this.head;
        int i;
        for (i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    public Object getFirst() {
        if (this.isEmpty() == false) {
            return this.head.getElement();
        }
        return null;
    }

    public Object getLast() {
        if (this.isEmpty() == false) {
            return this.tail.getElement();
        }
        return null;
    }

    @Override
    public ImmutableLinkedList remove(int index)
            throws IndexOutOfBoundsException {
        if ((index >= size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newList = new Object[this.size - 1];
        Object[] currentList = this.toArray();
        System.arraycopy(currentList, 0, newList,
                0, index);
        System.arraycopy(currentList, index + 1,
                newList, index,
                this.size - 1 - index);
        return new ImmutableLinkedList(newList);
    }

    @Override
    public ImmutableLinkedList set(int index, Object a)
            throws IndexOutOfBoundsException {
        if ((index >= size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
        Object[] currentList = this.toArray();
        currentList[index] = a;
        return new ImmutableLinkedList(currentList);
    }

    @Override
    public int indexOf(Object a) {
        int i = 0;
        Node indexNode = this.head;
        while (indexNode != null) {
            if (indexNode.getElement().equals(a)) {
                return i;
            }
            indexNode = indexNode.getNext();
            i++;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return  new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] list = new Object[this.size];
        if (this.size > 0) {
            Node current = this.head;
            int i;
            for (i = 0; i < this.size; i++) {
                list[i] = current.getElement();
                current = current.getNext();
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuffer stbuf = new StringBuffer();
        Node indexNode = this.head;
        while (indexNode != null) {
            stbuf.append(indexNode.getElement());
            if (indexNode.getNext() != null) {
                stbuf.append(", ");
            }
            indexNode = indexNode.getNext();
        }
        return stbuf.toString();
    }
}

