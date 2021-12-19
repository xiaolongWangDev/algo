package tree;

import java.util.List;

public class CompanyParty extends TreeTraversal {

    private static class Info {
        int attendingHappiness;
        int absentHappiness;

        public Info(int attendingHappiness, int absentHappiness) {
            this.attendingHappiness = attendingHappiness;
            this.absentHappiness = absentHappiness;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "attendingHappiness=" + attendingHappiness +
                    ", absentHappiness=" + absentHappiness +
                    '}';
        }
    }

    private static class Employee {
        int happiness;
        List<Employee> subordinates;

        public Employee(int happiness, List<Employee> subordinates) {
            this.happiness = happiness;
            this.subordinates = subordinates;
        }
    }

    public Info maxHappiness(Employee e) {
        if (e.subordinates.isEmpty()) return new Info(e.happiness, 0);

        int meAttendingHappiness = e.happiness;
        int meAbsentHappiness = 0;
        for (Employee sub : e.subordinates) {
            Info info = maxHappiness(sub);
            meAttendingHappiness += info.absentHappiness;
            meAbsentHappiness += Math.max(info.absentHappiness, info.attendingHappiness);
        }
        return new Info(meAttendingHappiness, meAbsentHappiness);
    }


    public static void main(String[] args) {

        Employee boss = new Employee(70,
                List.of(new Employee(3, List.of(new Employee(100, List.of()))),
                        new Employee(4, List.of(new Employee(200, List.of()))),
                        new Employee(5, List.of(new Employee(300, List.of())))));

        CompanyParty algo = new CompanyParty();
        System.out.println(algo.maxHappiness(boss));

        boss = new Employee(10,
                List.of(new Employee(3, List.of(new Employee(60, List.of()))),
                        new Employee(20, List.of(new Employee(3, List.of()))),
                        new Employee(40, List.of(new Employee(5, List.of()), new Employee(6, List.of())))));

        System.out.println(algo.maxHappiness(boss));
    }
}
