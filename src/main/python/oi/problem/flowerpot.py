from typing import List, Tuple, Union

from oi.data_structure.monotonic_queue import MonotonicQueue


class IndexedPoint:
    def __init__(self, args: Tuple[int, List[int]]):
        self.index = args[0]
        self.x = args[1][0]
        self.y = args[1][1]


def y_comparator(a: IndexedPoint, b: IndexedPoint):
    return a.y - b.y


def min_valid_width(coordinates: List[List[int]], D: int) -> int:
    """
    A problem to exercise monotonic queue, function monotonicity.

    # https://oi-wiki.org/ds/monotonous-queue/
    # https://www.luogu.com.cn/problem/P2698

    :param coordinates: [x, y] of each rain drop. 1 ≤ N ≤ 100000
    :param D: required length of time. 1 ≤ D ≤ 1000000
    :return: the minimum width of basket needed to collect d length of rain water
    """

    # this is to keep the points that are descending by Y coordinate in the window [left, right]
    max_queue = MonotonicQueue(y_comparator, True)
    # this is to keep the points that are ascending by Y coordinate in the window [left, right]
    min_queue = MonotonicQueue(y_comparator, False)

    # sort the original data by X coordinate
    coordinates.sort(key=lambda x: x[0])

    # add the index to the data so that we know we can decide whether to evict a point when window left bound moves
    data = [IndexedPoint(enumerated) for enumerated in enumerate(coordinates)]

    # a window covering the first point. Note, nothing is added to the queue at this point
    left = right = 0

    # variable to track the result width
    min_width: Union[None, int] = None

    # sliding window
    while right < len(data):
        # right move until we've found the tracked max and min difference is >= D. which is a valid solution
        # or when right is at the end of data
        while right < len(data) \
                and (max_queue.is_empty() or max_queue.peek_extreme().y - min_queue.peek_extreme().y < D):
            data_to_add = data[right]
            max_queue.add(data_to_add)
            min_queue.add(data_to_add)
            right += 1

        # enter if it's a valid solution
        # in the loop, we keep trying to update min_width and push the window left bound right
        while max_queue.peek_extreme().y - min_queue.peek_extreme().y >= D:
            # try to update the min_width
            if min_width is None:
                min_width = abs(max_queue.peek_extreme().x - min_queue.peek_extreme().x)
            else:
                min_width = min(min_width, abs(max_queue.peek_extreme().x - min_queue.peek_extreme().x))
            # if any extreme value is to become valid, evict it now
            if max_queue.peek_extreme().index <= left:
                max_queue.evict_extreme()
            if min_queue.peek_extreme().index <= left:
                min_queue.evict_extreme()
            # move the window left bound
            left += 1

        # at this point pushing left bound won't give us a valid solution,
        # if the right bound cannot be pushed either,
        # the loop will end

    return min_width


def main():
    test_input = [[6, 3], [2, 4], [3, 10], [12, 15]]
    d = 5

    print(f"out : {min_valid_width(test_input, d)}")


if __name__ == "__main__":
    main()
