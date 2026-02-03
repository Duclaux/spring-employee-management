package fox.dev.employee.service.impl;

import fox.dev.employee.dto.EmployeeDto;
import fox.dev.employee.entity.Department;
import fox.dev.employee.entity.Employee;
import fox.dev.employee.exception.BadRequestException;
import fox.dev.employee.exception.ResourceNotFoundException;
import fox.dev.employee.repository.DepartmentRepository;
import fox.dev.employee.repository.EmployeeRepository;
import fox.dev.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto addEmployee(Long departementId, EmployeeDto employeeDto) {

        Department department = departmentRepository.findById(departementId)
                .orElseThrow(()-> new ResourceNotFoundException("Department not fount with id: "+departementId));

        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        savedEmployeeDto.setDepartementId(departementId);
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long departementId, Long employeeId) {

        Department department = departmentRepository.findById(departementId)
                .orElseThrow(()-> new ResourceNotFoundException("Department not fount with id: "+departementId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not fount with id: "+employeeId));

        if(!employee.getDepartment().getId().equals(departementId)){
            throw new BadRequestException("This Employee does not belong to departement with ID:" +  departementId);
        }
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        employeeDto.setDepartementId(departementId);
        return employeeDto;
    }
}
