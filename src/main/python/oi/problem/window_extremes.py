from typing import List, Union

from helper.enumerated_object import EnumeratedObject
from oi.data_structure.monotonic_queue import MonotonicQueue


# Leetcode 239
def get_extreme_values_in_window(input_arr: List[int], window_size: int, get_max: bool) -> List[Union[None, int]]:
    monotonic_queue = MonotonicQueue(EnumeratedObject.compare, get_max)

    # output[i] keeps the extreme value of the window that ends on index i
    output = []

    for enumerated in enumerate(input_arr):
        enumerated = EnumeratedObject(enumerated)
        monotonic_queue.add(enumerated)

        # calculate the window left index
        window_left_index = enumerated.index - window_size + 1
        # as we move the window, it's possible that the cumulated extreme in the queue is no longer
        # valid. In that case, we need to evict it
        if monotonic_queue.peek_extreme().index < window_left_index:
            monotonic_queue.evict_extreme()

        extreme_value = monotonic_queue.peek_extreme().value
        # at index window_size - 1, we will be looking at the end of the first window. Since then,
        # we can start outputting the extreme values
        if enumerated.index >= window_size - 1:
            output.append(extreme_value)
        else:
            pass
            # depending on whether we think a partial window is valid,
            # we can return either none or the extreme value at that point
            # output.append(None) # output.append(extreme_value)
    return output


def get_section_extreme_values():
    test_input_1 = [1, 3, -1, -3, 5, 3, 6, 7]
    test_input_2 = [7, 6, 5, 4, 3, 2, 1]
    window_size = 3

    print(f"-----max values in window of {window_size}-----")
    print(f"data: {test_input_1}")
    print(f"out : {get_extreme_values_in_window(test_input_1, window_size, True)}")
    print(f"data: {test_input_2}")
    print(f"out : {get_extreme_values_in_window(test_input_2, window_size, True)}")

    print(f"-----min values in window of {window_size}-----")
    print(f"data: {test_input_1}")
    print(f"out : {get_extreme_values_in_window(test_input_1, window_size, False)}")
    print(f"data: {test_input_2}")
    print(f"out : {get_extreme_values_in_window(test_input_2, window_size, False)}")


if __name__ == "__main__":
    get_section_extreme_values()
