package com.christian.incident;


import com.christian.incident.web.dto.request.IncidentCreateDto;
import com.christian.incident.web.dto.response.IncidentResponseDto;
import com.christian.incident.web.exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/Incident/incident-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/Incident/incident-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class IncidentTest {
    @Autowired
    WebTestClient testClient;

    @Test
    // Test -> Create Incident With Valid Information
    public void createIncident_WithValidInformation_ReturnCode201() {
        IncidentResponseDto responseBody = testClient
                .post()
                .uri("/api/v1/incident")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new IncidentCreateDto("Lampada Queimada", "Apartamento 610"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(IncidentResponseDto.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.id()).isNotNull();
        Assertions.assertThat(responseBody.incident()).isEqualTo("Lampada Queimada");
        Assertions.assertThat(responseBody.location()).isEqualTo("Apartamento 610");
        Assertions.assertThat(responseBody.status()).isEqualTo("OPEN");
        Assertions.assertThat(responseBody.createdAt()).isNotNull();
    }

    @Test
    // Test -> Incident With Information Invalid
    public void createIncident_WithInvalidInformationIncident_ReturnCode422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/incident")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new IncidentCreateDto("", "Apartamento 610"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    }


    @Test
    // Test -> Incident With Less Than 6 Characters
    public void createIncident_WithIncidentLessThan6Characters_ReturnCode422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/incident")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new IncidentCreateDto("lampa", "Apartamento 610"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
   // Test -> Incident With location Less Than 6 Characters
    public void createIncident_WithLocationLessThan6Characters_ReturnCode422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/incident")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new IncidentCreateDto("lampada Queimada", "Apart"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    // Test -> Incident With Location Information Null
    public void createIncident_WithNullLocation_ReturnCode422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/incident")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new IncidentCreateDto("lampada Queimada", null))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
    }

    @Test
    // Test -> Incident With Location Invalid
    public void createIncident_WithInvalidInformationLocation_ReturnCode422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/incident")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new IncidentCreateDto("Lampada Queimada", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    }

    @Test
    // Test -> Incident with Empty Information
    public void createIncident_WithInformationEmpty_ReturnCode422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/incident")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new IncidentCreateDto("", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

}



