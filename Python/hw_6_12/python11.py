class MealyError(Exception):
    def __init__(self, msg):
        super().__init__(msg)


class Mili:
    states = {
        "A": {
            "stash": (0, "B"),
            "build": (1, "E")
        },
        "B": {
            "model": (2, "C")
        },
        "C": {
            "build": (3, "D"),
            "stash": (4, "F")
        },
        "D": {
            "model": (5, "E"),
            "build": (6, "G")
        },
        "E": {
            "build": (7, "F")
        },
        "F": {
            "model": (10, "D"),
            "stash": (8, "G"),
            "build": (9, "H")
        },
        "G": {
            "model": (11, "H"),
        },
        "H": {

        }
    }

    def __init__(self, other=None):
        if other:
            self.current_state = other.current_state
        else:
            self.current_state = "A"

    def stash(self):
        if "stash" in self.states[self.current_state]:
            value, new_state = self.states[self.current_state]["stash"]
            self.current_state = new_state
            return value
        else:
            raise MealyError("stash")

    def model(self):
        if "model" in self.states[self.current_state]:
            value, new_state = self.states[self.current_state]["model"]
            self.current_state = new_state
            return value
        else:
            raise MealyError("model")

    def build(self):
        if "build" in self.states[self.current_state]:
            value, new_state = self.states[self.current_state]["build"]
            self.current_state = new_state
            return value
        else:
            raise MealyError("build")


def main():
    return Mili()


def test():
    mili = main()
    visited = {"A": 0, "B": 0, "C": 0, "D": 0, "E": 0, "F": 0, "G": 0, "H": 0}

    def process_event(mili_instance, event):
        mili_copy = Mili(mili_instance)
        try:
            getattr(mili_copy, event)()
        except MealyError:
            pass
        recurr(mili_copy)

    def recurr(mili_instance):
        if visited[mili_instance.current_state] == 1:
            return
        else:
            visited[mili_instance.current_state] += 1

        events = ['stash', 'model', 'build']
        for event in events:
            process_event(mili_instance, event)

    recurr(mili)
