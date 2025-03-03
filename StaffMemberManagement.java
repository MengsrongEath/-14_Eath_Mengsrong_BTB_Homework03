import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

class StaffMemberManagement {
    List <StaffMember> employee = new ArrayList<>(
            List.of(
                        new Volunteer(1,"John Dev", "PP",580),
                        new SalaryEmployee(2,"John Speed", "KPC",700, 40),
                        new HourlySalaryEmployee(3,"Dev Web", "KPS",20 , 20)
            )
    );

    int id = 3;

    private Scanner scanner = new Scanner(System.in);

    public void menu(){
        while (true){
            CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
            Table t = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE);
            t.setColumnWidth(0, 50, 70);

            t.addCell("STAFF MANAGEMENT SYSTEM", center);
            t.addCell(" ".repeat(3) + "1. Insert Employee");
            t.addCell(" ".repeat(3) + "2. Update Employee");
            t.addCell(" ".repeat(3) + "3. Display Employee");
            t.addCell(" ".repeat(3) + "4. Remove Employee");
            t.addCell(" ".repeat(3) + "5. Exit");
            System.out.println(t.render());
            System.out.println("--------------------------------------");
            System.out.print("-> Choose an option(): ");

            int option = Integer.parseInt(scanner.nextLine().trim());

            switch (option){
                case 1 ->insertEmployee();
                case 2 -> updateEmployee();
                case 3 ->displayEmployee();
                case 4 ->removeEmployee();
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void insertEmployee(){
        System.out.println("==========* Insert Employee *==========");
        System.out.println("Choose Type:");
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        t.setColumnWidth(0, 0, 70);
        t.addCell("1. Volunteer", center);
        t.addCell("2. Salaried", center);
        t.addCell("3. Hourly", center);
        t.addCell("4. Back", center);
        System.out.println(t.render());

        System.out.print("=> Enter Type Number:");

        int chose = scanner.nextInt();
        if (chose == 4 ) return;


        switch (chose) {
            case 1:
                id++;
                System.out.print("ID: " + id);
                System.out.print("\n=> Enter Name:");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.print("=> Enter Address:");
                String address = scanner.nextLine();
                System.out.print("=> Enter Salary:");
                double salary = scanner.nextDouble();

                employee.add(new Volunteer(id ,name,address, salary));
//                System.out.println(employee);
                break;
            case 2:
                id++;
                System.out.print("ID: " + id);
                System.out.print("\n=> Enter Name:");
                scanner.nextLine();
                String nameSalaryEm = scanner.nextLine();
                System.out.print("=> Enter Address:");
                String addressSalaryEm = scanner.nextLine();
                System.out.print("=> Enter Salary:");
                double SalaryEm = scanner.nextDouble();
                System.out.print("=> Enter Bonus:");
                double bonus = scanner.nextDouble();
                employee.add(new SalaryEmployee(id ,nameSalaryEm,addressSalaryEm,SalaryEm , bonus));
//                System.out.println(employee);
                break;
            case 3:
                id++;
                System.out.print("ID: " + id);
                System.out.print("\n=> Enter Name:");
                String nameHourSalary = scanner.nextLine();
                scanner.nextLine();
                System.out.print("=> Enter Address:");
                String addressHourSalary = scanner.nextLine();
                System.out.print("=> Enter Hour Worked:");
                int hourWorked = scanner.nextInt();
                System.out.print("=> Enter Rate:");
                double rate = scanner.nextDouble();
                employee.add(new HourlySalaryEmployee(id,nameHourSalary,addressHourSalary, hourWorked,rate));
//                System.out.println(employee);
                break;
        }

    }

    public void displayEmployee(){
        System.out.println("==========* Insert Employee *==========");
        if (employee.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }

        System.out.println("======* Display Employee *=======");
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE);
        t.setColumnWidth(0, 15, 20); // Type
        t.setColumnWidth(1, 5, 10);  // ID
        t.setColumnWidth(2, 15, 20); // Name
        t.setColumnWidth(3, 15, 25); // Address
        t.setColumnWidth(4, 10, 15); // Salary
        t.setColumnWidth(5, 10, 15); // Bonus
        t.setColumnWidth(6, 10, 15); // Hours
        t.setColumnWidth(7, 10, 15); // Pay
        t.setColumnWidth(8, 10, 15); // Pay


        // Header row
        t.addCell("Type", center);
        t.addCell("ID", center);
        t.addCell("Name", center);
        t.addCell("Address", center);
        t.addCell("Salary", center);
        t.addCell("Bonus", center);
        t.addCell("Hours", center);
        t.addCell("Rate", center);
        t.addCell("Pay", center);

        for (StaffMember emp : employee){

            if (emp instanceof Volunteer){
                t.addCell( "Volunteer", center);
                t.addCell(String.valueOf(emp.id) , center);
                t.addCell( emp.name , center);
                t.addCell(emp.address , center);
                t.addCell("$" + (((Volunteer) emp).getSalary()) , center);
                t.addCell("---", center );
                t.addCell("---" , center);
                t.addCell("---" , center);
                t.addCell("$" + emp.pay() ,center);
            }else if (emp instanceof SalaryEmployee){
                t.addCell( "Salary Employee" ,center);
                t.addCell(String.valueOf(emp.id) ,center);
                t.addCell( emp.name , center);
                t.addCell(emp.address , center);
                t.addCell("$" + (((SalaryEmployee) emp).getSalary()) ,center);
                t.addCell("$" + (((SalaryEmployee) emp).getBunus()),center );
                t.addCell("---" ,center);
                t.addCell("---" ,center);
                t.addCell("$" + emp.pay() ,center);
            }else if (emp instanceof HourlySalaryEmployee){
                t.addCell( "Hourly Employee" ,center);
                t.addCell(String.valueOf(emp.id),center);
                t.addCell( emp.name ,center);
                t.addCell(emp.address ,center);
                t.addCell("---" ,center);
                t.addCell("---" ,center);
                t.addCell("$" + (((HourlySalaryEmployee) emp).getHourWorked()) ,center);
                t.addCell("$" + (((HourlySalaryEmployee) emp).getRate()),center );
                t.addCell("$" + emp.pay() ,center);
            }

        }
        System.out.println(t.render());
    }

    public void updateEmployee() {
        System.out.println("==========* Update Employee *==========");
        System.out.print("=> Enter or Search ID to update: ");
        int searchId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;

        for (StaffMember emp : employee) {
            if (emp.id == searchId) {
                found = true;

                System.out.println("\n======* Current Details *=======");
                CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
                Table t = new Table(6, BorderStyle.UNICODE_ROUND_BOX_WIDE);
                t.setColumnWidth(0, 15, 20); // Type
                t.setColumnWidth(1, 5, 10);  // ID
                t.setColumnWidth(2, 15, 20); // Name
                t.setColumnWidth(3, 15, 25); // Address
                t.setColumnWidth(4, 10, 15); // Salary
                t.setColumnWidth(5, 10, 15); // Pay


                t.addCell("Type", center);
                t.addCell("ID", center);
                t.addCell("Name", center);
                t.addCell("Address", center);
                t.addCell("Salary", center);
                t.addCell("Pay", center);

                if (emp instanceof Volunteer) {
                    t.addCell("Volunteer", center);
                    t.addCell(String.valueOf(emp.id), center);
                    t.addCell(emp.name, center);
                    t.addCell(emp.address, center);
                    t.addCell("$" + ((Volunteer) emp).getSalary(), center);
                    t.addCell("$" + emp.pay(), center);
                } else if (emp instanceof SalaryEmployee) {
                    t.addCell("Salary Employee", center);
                    t.addCell(String.valueOf(emp.id), center);
                    t.addCell(emp.name, center);
                    t.addCell(emp.address, center);
                    t.addCell("$" + ((SalaryEmployee) emp).getSalary(), center);
                    t.addCell("$" + emp.pay(), center);
                } else if (emp instanceof HourlySalaryEmployee) {
                    t.addCell("Hourly Employee", center);
                    t.addCell(String.valueOf(emp.id), center);
                    t.addCell(emp.name, center);
                    t.addCell(emp.address, center);
                    t.addCell("---", center);
                    t.addCell("$" + emp.pay(), center);
                }

                System.out.println(t.render());

                System.out.println("\nChoose one coloumn to update:");
                System.out.println("1. Name");
                System.out.println("2. Address");
                System.out.println("3. Salary");
                System.out.println("4. Bonus");
                System.out.println("5. Hours & Rate");
                System.out.println("6. Cancel");
                System.out.print("=> Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Change Name To: ");
                        emp.name = scanner.nextLine();
                        System.out.println("Name updated successfully!");
                        break;
                    case 2:
                        System.out.print("Change Address To: ");
                        emp.address = scanner.nextLine();
                        System.out.println("Address updated successfully!");
                        break;
                    case 3:
                        if (emp instanceof SalaryEmployee) {
                            System.out.print("Change Salary To: ");
                            ((SalaryEmployee) emp).setSalary(scanner.nextDouble());
                            System.out.println("Salary updated successfully!");
                        } else if (emp instanceof Volunteer) {
                            System.out.print("Change Salary To: ");
                            ((Volunteer) emp).setSalary(scanner.nextDouble());
                            System.out.println("Salary updated successfully!");
                        } else {
                            System.out.println("This employee type does not have a salary.");
                        }
                        break;
                    case 4:
                        if (emp instanceof SalaryEmployee) {
                            System.out.print("Change Bonus To: ");
                            ((SalaryEmployee) emp).setBunus(scanner.nextDouble());
                            System.out.println("Bonus updated successfully!");
                        } else {
                            System.out.println("This employee type does not have a bonus.");
                        }
                        break;
                    case 5:
                        if (emp instanceof HourlySalaryEmployee) {
                            System.out.print("Enter new Hours Worked: ");
                            ((HourlySalaryEmployee) emp).setHourWorked(scanner.nextInt());
                            System.out.print("Enter new Rate: ");
                            ((HourlySalaryEmployee) emp).setRate(scanner.nextDouble());
                            System.out.println("Hours & Rate updated successfully!");
                        } else {
                            System.out.println("This employee type does not have hourly work.");
                        }
                        break;
                    case 6:
                        System.out.println("Update canceled.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }

                break;
            }
        }

        if (!found) {
            System.out.println("Employee with ID " + searchId + " not found.");
        }
    }

    public void removeEmployee() {
        System.out.println("==========* Remove Employee *==========");
        System.out.print("=> Enter the ID of the employee to remove: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        for (StaffMember emp : employee) {
            if (emp.id == searchId) {
                found = true;

                System.out.println("\n======* Employee Found *=======");
                CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
                Table t = new Table(6, BorderStyle.UNICODE_ROUND_BOX_WIDE);
                t.setColumnWidth(0, 15, 20);
                t.setColumnWidth(1, 5, 10);
                t.setColumnWidth(2, 15, 20);
                t.setColumnWidth(3, 15, 25);
                t.setColumnWidth(4, 10, 15);
                t.setColumnWidth(5, 10, 15);

                t.addCell("Type", center);
                t.addCell("ID", center);
                t.addCell("Name", center);
                t.addCell("Address", center);
                t.addCell("Salary", center);
                t.addCell("Pay", center);

                if (emp instanceof Volunteer) {
                    t.addCell("Volunteer", center);
                    t.addCell(String.valueOf(emp.id), center);
                    t.addCell(emp.name, center);
                    t.addCell(emp.address, center);
                    t.addCell("$" + ((Volunteer) emp).getSalary(), center);
                    t.addCell("$" + emp.pay(), center);
                } else if (emp instanceof SalaryEmployee) {
                    t.addCell("Salary Employee", center);
                    t.addCell(String.valueOf(emp.id), center);
                    t.addCell(emp.name, center);
                    t.addCell(emp.address, center);
                    t.addCell("$" + ((SalaryEmployee) emp).getSalary(), center);
                    t.addCell("$" + emp.pay(), center);
                } else if (emp instanceof HourlySalaryEmployee) {
                    t.addCell("Hourly Employee", center);
                    t.addCell(String.valueOf(emp.id), center);
                    t.addCell(emp.name, center);
                    t.addCell(emp.address, center);
                    t.addCell("---", center);
                    t.addCell("$" + emp.pay(), center);
                }

                System.out.println(t.render());

                System.out.print("\nAre you sure you want to remove this employee? (yes/no): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();

                if (confirmation.equals("yes")) {
                    employee.remove(emp);
                    System.out.println("Employee with ID " + searchId + " has been removed successfully!");
                } else {
                    System.out.println("Employee removal canceled.");
                }

                return;
            }
        }

        if (!found) {
            System.out.println("Employee with ID " + searchId + " not found.");
        }
    }




}

