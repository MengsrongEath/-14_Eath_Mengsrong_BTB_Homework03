class SalaryEmployee extends StaffMember{
    private double salary;
    private double bunus;

    public SalaryEmployee(int id, String name , String address, double salary, double bunus){
        super(id , name, address);
        this.salary = salary;
        this.bunus = bunus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBunus() {
        return bunus;
    }

    public void setBunus(double bunus) {
        this.bunus = bunus;
    }

    @Override
    public double pay (){
        return salary + bunus;
    }
}
