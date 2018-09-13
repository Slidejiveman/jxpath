import org.apache.commons.jxpath.JXPathContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on example at this site:
 * https://www.javaworld.com/article/2077700/data-storage/java-object-queries-using-jxpath.html
 */
public class Main {
    public static void main(String[] args) {

        // Create objects to reference with JXPath
        Company company = new Company();
        Department sales = new Department();
        Department scheduling = new Department();
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        // initialize values
        employee1.setAge(45);
        employee1.setJobTitle("Sales Manager");
        employee1.setName("Bess");

        employee2.setAge(18);
        employee2.setJobTitle("Intern Scheduler");
        employee2.setName("Marty");

        sales.setName("Sales");
        scheduling.setName("Scheduling");

        // Link up objects so they are not null and properly structured
        List salesEmployeeList = new ArrayList();
        salesEmployeeList.add(employee1);
        sales.setEmployeeList(salesEmployeeList);
        List schedulingEmployeeList = new ArrayList();
        schedulingEmployeeList.add(employee2);
        scheduling.setEmployeeList(schedulingEmployeeList);
        List departmentList = new ArrayList();
        departmentList.add(sales);
        departmentList.add(scheduling);
        company.setName("Acme");
        company.setDepartmentList(departmentList);


        // Context holds the state of the object given to it.
        JXPathContext context = JXPathContext.newContext(company);

        // retrieve the Company, which is the root of the context
        Company c = (Company)context.getValue(".");
        System.out.println(c.toString());

        // retrieve an employee, which is a child of a department
        // which is a child of the company.
        // Notice how the attribute names are used to find the result.
        Employee emp = (Employee)context.getValue("/departmentList/employeeList[name='Marty']");
        System.out.println(emp.toString());
    }
}
