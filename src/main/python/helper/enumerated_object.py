class EnumeratedObject:
    def __init__(self, in_arr):
        self.index = in_arr[0]
        self.value = in_arr[1]

    @staticmethod
    def compare(o1, o2):
        return o1.value - o2.value
