package ma.ensias.employe.configuration;

import ma.ensias.employe.repository.EmployeRepo;
import ma.ensias.employe.service.EmployeService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeConfig {
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    @Bean
    public EmployeService employeBean(EmployeRepo employeRepo, ModelMapper mapper) {
        return new EmployeService(employeRepo, mapper);
    }
}
