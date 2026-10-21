package ma.ensias.employe.controller;

import jakarta.servlet.http.HttpServletResponse;
import ma.ensias.employe.reponse.EmployeReponse;
import ma.ensias.employe.service.EmployeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeController {
    // password = "DEMO_ONLY_not_a_real_credential_123"

    private final EmployeService employeService;
    private final DataSource dataSource;

    public EmployeController(EmployeService employeService, DataSource dataSource) {
        this.employeService = employeService;
        this.dataSource = dataSource;
    }

    @GetMapping("/employes/{id}")
    public EmployeReponse getEmployeDetails(@PathVariable("id") int id) {
        return employeService.getEmployeById(id);
    }

    @GetMapping("/employes/demo/sql")
    public List<String> searchByName(@RequestParam String name) throws SQLException {
        String query = "SELECT name FROM employe WHERE name = '" + name + "'";
        List<String> names = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(query)) {
            while (results.next()) {
                names.add(results.getString("name"));
            }
        }

        return names;
    }

    @GetMapping(value = "/employes/demo/xss", produces = MediaType.TEXT_HTML_VALUE)
    public void reflect(@RequestParam String message, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.getWriter().write("<html><body>" + message + "</body></html>");
    }
}
