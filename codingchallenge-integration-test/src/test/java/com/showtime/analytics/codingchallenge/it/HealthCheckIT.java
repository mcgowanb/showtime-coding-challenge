package test.java.com.showtime.analytics.codingchallenge.it;

import com.showtime.analytics.codingchallenge.Application;
import com.showtime.analytics.codingchallenge.it.TestContainers;
import io.restassured.RestAssured;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@ActiveProfiles(profiles = "it")
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthCheckIT extends TestContainers {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void verify_health_check() {
        given()
                .when()
                .get("/health")
                .then()
                .body("status", is("UP"));
    }
}
