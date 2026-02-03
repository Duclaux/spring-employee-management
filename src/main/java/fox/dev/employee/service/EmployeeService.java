package fox.dev.employee.service;

import fox.dev.employee.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto addEmployee(Long departementId, EmployeeDto employeeDto);
}
