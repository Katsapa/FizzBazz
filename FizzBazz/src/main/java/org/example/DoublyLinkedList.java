package org.example;

public class DoublyLinkedList<T> {
    private static class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int size;
    private Node<T> head;
    private Node<T> tail;

    DoublyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void addLast(T data){
        Node<T> node = new Node<>(data);
        if(tail == null){
            head = node;
            tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void addFirst(T data){
        var newNode = new Node<T>(data);
        if(head == null){
            head = newNode;
            tail = newNode;
        } else{
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void add(T data, int index){

        checkIndex(index);

        if (index == 0){
            addFirst(data);
            return;
        }
        if (index == size()){
            addLast(data);
            return;
        }

        Node<T> current = nodeAt(index);
        Node<T> newNode = new Node<>(data);

        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    public T removeFirst(){
        if(head == null) throw new RuntimeException("Список пуст");
        T data = head.data;
        if(head==tail){
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }

    public T removeLast(){
        if(tail == null) throw new RuntimeException("Список пуст");
        T data = tail.data;
        if(head==tail){
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    public T remove(int index){
        checkIndex(index);

        if(index == 0) return removeFirst();
        if(index == size()) return removeLast();

        Node<T> node = nodeAt(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.data;
    }

    public boolean removeByValue(T value){
        Node<T> current = head;
        while(current != null){
            if(current.data.equals(value)){
                if(current == head) {
                    removeFirst();
                    return true;
                }
                if(current == tail) {
                    removeLast();
                    return true;
                }
                current.next.prev = current.prev;
                current.prev.next = current.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index){
        checkIndex(index);
        return nodeAt(index).data;
    }

    public T getFirst(){
        if(head == null) throw new RuntimeException("Список пуст");
        return head.data;
    }

    public T getLast(){
        if(tail == null) throw new RuntimeException("Список пуст");
        return tail.data;
    }

    public boolean contains(T data){
            Node<T> current = head;

            while(current != null){
                if(current.data.equals(data)) return true;
                current = current.next;
            }
            return false;
    }

    public int indexOf(T data){
        Node<T> current = head;
        int index = 0;
        while(current != null){
            if(current.data.equals(data)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" <=> ");
            current = current.next;
        }
        return sb.append("]").toString();
    }

    public int size(){ return size; }

    public boolean isEmpty(){ return size == 0; }

    private Node<T> nodeAt(int index){
        if(size() / 2 > index){
            Node<T> current = head;
            for(int i = 0; i < index; i++) current = head.next;
            return current;
        } else {
            Node<T> current = tail;
            for(int i = size; i > index; i--) current = tail.prev;
            return current;
        }
    }

    private void checkIndex(int index) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("The index is out of range with id: " +  size());
        }
    }

}
