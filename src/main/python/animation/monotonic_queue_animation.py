from manim import Scene, Rectangle, DARK_BLUE, BLUE, Text, VGroup, UP, RIGHT, FadeOut, MoveToTarget, DOWN, LEFT, Write

from helper.enumerated_object import EnumeratedObject
from oi.data_structure.monotonic_queue import MonotonicQueue


class NumberArrayBox:
    def __init__(self, label, init_data=None):
        self.box = Rectangle(fill_color=DARK_BLUE, stroke_color=BLUE, height=1, width=8)
        self.label = Text(label).next_to(self.box, UP)
        self.head = Text(text=":").next_to(self.box, LEFT)
        self.anchor = self.head
        self.obj = VGroup(self.label, self.box, self.head)
        self.data_to_mobject = {}
        if init_data is not None:
            for data in init_data:
                self.add(data)

    def create_new_number_text(self, data):
        return Text(text=str(data.value)).next_to(self.anchor, 2 * RIGHT)

    def add_text_and_data(self, text, data):
        self.obj.add(text)
        self.data_to_mobject[data] = text
        self.anchor = text

    def add(self, data):
        text = self.create_new_number_text(data)
        self.add_text_and_data(text, data)

    def move_out_to(self, scene, data, target):
        to_remove = self._remove_one(data)
        to_remove.target = target
        scene.play(MoveToTarget(to_remove), run_time=0.2)
        self._remove_gaps(scene)
        scene.remove(to_remove)

    def remove_many(self, scene, data_arr):
        for data in data_arr:
            to_remove = self._remove_one(data)
            scene.play(FadeOut(to_remove), run_time=0.4)
        self._remove_gaps(scene)

    def _remove_one(self, data):
        if data not in self.data_to_mobject:
            raise IndexError()
        to_remove = self.data_to_mobject[data]
        self.data_to_mobject.pop(data)
        self.obj.remove(to_remove)
        return to_remove

    def _remove_gaps(self, scene):
        self.anchor = self.head
        for j in range(3, len(self.obj)):
            scene.play(self.obj[j].animate.next_to(self.anchor, 2 * RIGHT), run_time=0.2)
            self.anchor = self.obj[j]


def add_and_calc_evicted(mq, data):
    before = set(mq.base_queue)
    mq.add(data)
    after = set(mq.base_queue)
    result = list(before.difference(after))
    result.sort(key=lambda x: x.index, reverse=True)
    return result


class MonotonicQueueAnimation(Scene):

    def animate_add_operation(self, data, title):
        enumerated_objects = [EnumeratedObject([i, v]) for i, v in enumerate(data)]
        self.play(Write(Text(title)))
        input_data = NumberArrayBox("Test Input", init_data=enumerated_objects)
        queue = NumberArrayBox("Monotonic Queue")
        input_data.obj.move_to(UP * 2.5)
        queue.obj.move_to(DOWN * 2.5)
        self.add(input_data.obj)
        self.add(queue.obj)

        mq = MonotonicQueue(EnumeratedObject.compare, True)

        for enumerated_object in enumerated_objects:
            new_text = queue.create_new_number_text(enumerated_object)
            input_data.move_out_to(self, enumerated_object, new_text)
            queue.add_text_and_data(new_text, enumerated_object)
            evicted = add_and_calc_evicted(mq, enumerated_object)
            queue.remove_many(self, evicted)
            self.wait(1)

    def construct(self):
        test_input_1 = [1, 3, -1, -3, 5, 3, 6, 7]
        self.animate_add_operation(test_input_1, "Demo of add operation: test 1")
        self.clear()
        self.wait(1)
        test_input_2 = [6, 5, 4, 3, 2, 1]
        self.animate_add_operation(test_input_2, "Demo of add operation: test 2")
        self.clear()
        self.wait(1)
        test_input_3 = [1, 2, 3, 4, 5, 6]
        self.animate_add_operation(test_input_3, "Demo of add operation: test 3")
