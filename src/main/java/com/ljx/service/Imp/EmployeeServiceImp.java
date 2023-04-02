package com.ljx.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljx.entity.Employee;
import com.ljx.mapper.EmployeeMapper;
import com.ljx.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
