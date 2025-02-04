package Week1;

class Node {
	int data;
	Node next;
	
	public Node(int data){
	    data = data;
		next = null;
	}
}


public class CycleLinkedList {
    public static boolean isCycle(Node head) {
    	Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
    	Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(7);
        head.next.next.next = new Node(2);
        head.next.next.next.next = head.next; 

        System.out.println(isCycle(head));
	}
}

