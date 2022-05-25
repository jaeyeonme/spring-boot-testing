package io.jaeyeon.springboottesting.repository;

import io.jaeyeon.springboottesting.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryIT {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void beforeEach() {
        employee = Employee.builder()
                .firstName("Jaeyeon")
                .lastName("Cho")
                .email("cjyeon1022@gmail.com")
                .build();
    }

    @DisplayName("JUnit test for save employee operation")
    @Test
    void givenEmployeeObject_whenSave_thenReturnSavedEmployee() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();


        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for get all employees operation")
    @Test
    void givenEmployeesList_whenFindAll_thenEmployeeList() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("Brin")
                .lastName("Sergey")
                .email("brin@google.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for get employee by id operation")
    @Test
    void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    @DisplayName("JUnit for get employee by email operation")
    @Test
    void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    @DisplayName("JUnit test for update employee operation")
    @Test
    void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdateEmployee() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("updateEmail@google.com");
        savedEmployee.setFirstName("Name");
        savedEmployee.setLastName("Updated");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("updateEmail@google.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Name");
    }

    @DisplayName("JUnit test for delete employee operation")
    @Test
    void givenEmployeeObject_whenDelete_thenRemove() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        employeeRepository.save(employee);
        String firstName = "Jaeyeon";
        String lastName = "Cho";

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for custom query using JPQL with Named params")
    @Test
    void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        employeeRepository.save(employee);
        String firstName = "Jaeyeon";
        String lastName = "Cho";

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject() throws Exception {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Jaeyeon")
//                .lastName("Cho")
//                .email("cjyeon1022@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }
}