public class Main {

    public static Employee[] raiseSalary(Employee[] empArr) {
        for (int i = 0; i < empArr.length; i++) {
            if (empArr[i].getAge() > 45) {
                empArr[i].setSalary(empArr[i].getSalary() + 5000);
            }
        }
        return empArr;
    }

    public static void main(String[] args) {
        Employee[] empArr = new Employee[]
                {
                 new Employee("Boris Denisov", "Direcor", "111111", 20000, 60),
                 new Employee("Evgen Denisov", "Deputy director", "222222", 17500, 50),
                 new Employee("Aleks Broflovsky", "Deliveryman", "333333", 10000, 20),
                 new Employee("Eric Nobody", "Repairer", "000000", 999, 42),
                 new Employee("Emma Novice", "Accountant", "999999", 15000, 35)
                };

        for (int i = 0; i < empArr.length; i++) {
            if (empArr[i].getAge() > 40) {
                empArr[i].getInfo();
            }
        }
        System.out.println();
        for (int i = 0; i < empArr.length; i++) {
            empArr[i].getInfoMore();
        }
        System.out.println();
        empArr = raiseSalary(empArr); //руководство повышает зарплату сами себе
        for (int i = 0; i < empArr.length; i++) {
            empArr[i].getInfoMore();
        }

        //средняя зарплата
        System.out.println(Employee.getAverageSalary());

        //средний возраст
        System.out.println(Employee.getAverageAge());
    }
}
