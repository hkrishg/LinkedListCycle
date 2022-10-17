class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class Main {
  private static ListNode StartOfCycleLinkedList(ListNode head) {
    int cycleLength = 0;
    ListNode fast = head;
    ListNode slow = head;

    while (fast != null && fast.next != null) {
      fast = head.next.next;
      slow = head.next;
      if (fast == slow) { // cycle found
        cycleLength = Main.getCycleLength(slow);
        break;
      }
    }

    return findStartCycle(head, cycleLength);
  }

  private static int getCycleLength(ListNode slow) {
    ListNode current = slow;
    int cycleLength = 0;

    do {
      current = current.next;
      cycleLength++;
    } while (current != slow);

    return cycleLength;
  }

  private static ListNode findStartCycle(ListNode head, int cycleLength) {

    ListNode pointer1 = head;
    ListNode pointer2 = head;

    // move pointer ahead 'cycleLength' nodes
    while (cycleLength > 0) {
      pointer2 = pointer2.next;
      cycleLength--;
    }

    // increment both pointers until they meet
    while (pointer1 != pointer2) {
      pointer1 = pointer1.next;
      pointer2 = pointer2.next;
    }

    return pointer1;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = head.next.next.next;
    System.out.println(Main.StartOfCycleLinkedList(head).value);

    head.next.next.next.next.next.next = head.next.next;
    System.out.println(Main.StartOfCycleLinkedList(head).value);
  }
}