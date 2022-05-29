from collections import deque


class MonotonicQueue:
    """
    A data structure that a user can add elements in.
    The user can only observe the extreme value.
    The following features are guaranteed:
    1. after adding an element, the extreme will not diminish
    2. after evicting the extreme, the extreme will diminish

    Under the hood, at any time:
    The elements in the deque are monotonic.
    And they maintain the order that they are added in.

    As what monotonic queue is, it does not keep all added elements.
    """
    def __init__(self, comparator, monotonic_descending: bool):
        """
        :param comparator: a function to compare elements
        :param monotonic_descending: keep the max or the min to the left. descending means max, otherwise min.
        """
        # this is a double-ended queue because we need to be able to remove elements from both ends
        # the most extreme value is kept to the left
        self.base_queue = deque()
        self.comparator = comparator
        self.monotonic_descending = monotonic_descending

    def _is_invalid(self, prev_val, current_val):
        if self.monotonic_descending:
            return self.comparator(prev_val, current_val) <= 0
        else:
            return self.comparator(prev_val, current_val) >= 0

    def add(self, element):
        # before adding a new element to the queue, we need to make sure there's no existing element
        # that will break the monotonicity after current value is added.
        # if there is, keep evicting till the remaining are all valid
        while len(self.base_queue) > 0 and self._is_invalid(self.base_queue[-1], element):
            # evicting from the right end, because:
            # if it's invalid, we can pop and check next
            # otherwise, we don't need to check further
            self.base_queue.pop()

        # add current element
        self.base_queue.append(element)

    def peek_extreme(self):
        if len(self.base_queue) > 0:
            return self.base_queue[0]
        else:
            raise RuntimeError("queue is empty")

    def evict_extreme(self):
        if len(self.base_queue) > 0:
            self.base_queue.popleft()
        else:
            raise RuntimeError("queue is empty")

    def is_empty(self):
        return len(self.base_queue) == 0
