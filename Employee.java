public class Employee {
    private String FamNamePat; //ФИО
    private String position;
    private String phone;
    private int salary;
    private int age;
    private int id;

    /*
        Средний возраст и зарплата по идее можно реилозовать хранением в статик переменных общего возраста
        и общей зарплаты. Для определения средней выличины делим на кол-во экземпляров (counter).
     */
    private static int counter = 0;//количество экземпляров класса
    private static double allSalary = 0; //придется делать такого типа, чтобы получить дробный результат
    private static double allAge = 0;
    private static double averageSalary = 0;
    private static double averageAge = 0;

    public static double getAverageSalary() {
        return averageSalary;
    }

    public static double getAverageAge() {
        return averageAge;
    }


    public Employee(String FamNamePat, String position, String phone, int salary, int age) {
        this.FamNamePat = FamNamePat;
        this.position = position;
        this.phone = phone;
        this.salary = salary;
        this.age = age;

        this.id = ++Employee.counter; //присваиваем id (начиная с 1)
        Employee.allAge += age;
        Employee.allSalary += salary;
        Employee.averageSalary = Employee.allSalary / Employee.counter;
        Employee.averageAge = Employee.allAge / Employee.counter;
    }

    public String getFamNamePat() {
        return FamNamePat;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void getInfo() { // Инфо о сотруднике, ФИО + должность
        System.out.printf("Employee name is: %s, position is %s\n",getFamNamePat(), getPosition());
    }

    public void getInfoMore() { // Инфо о сотруднике (ALL)
        System.out.printf("Employee name is: %s, position is %s, phone : \"%s\", salary = %d, age = %d \n", getFamNamePat(), getPosition(), getPhone(), getSalary(), getAge());
    }
}
