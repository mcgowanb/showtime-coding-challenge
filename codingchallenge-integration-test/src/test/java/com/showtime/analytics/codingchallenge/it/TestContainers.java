package com.showtime.analytics.codingchallenge.it;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class TestContainers {

    private static final GenericContainer<?> DATABASE_CONTAINER;
    private static final GenericContainer<?> REDIS_CONTAINER;

    static{
        DATABASE_CONTAINER = new PostgreSQLContainer<>("postgres")
                .withDatabaseName("coding_challenge")
                .withUsername("it-test-user")
                .withPassword("it-test-password")
                .withLogConsumer(new Slf4jLogConsumer(log))
                .waitingFor(Wait.forLogMessage(".*database system is ready to accept connections.*\\n", 1));

        DATABASE_CONTAINER.start();

        REDIS_CONTAINER = new GenericContainer<>(DockerImageName.parse("redis:alpine"))
                .withExposedPorts(6379)
                .withLogConsumer(new Slf4jLogConsumer(log))
                .waitingFor(Wait.forLogMessage(".*Ready to accept connections*\\n", 1));

        REDIS_CONTAINER.start();


//        log.info("DB_DEFAULT_HOST {}", DATABASE_CONTAINER.getHost())
        System.setProperty("DB_DEFAULT_HOST", DATABASE_CONTAINER.getHost());
        System.setProperty("DB_DEFAULT_PORT", DATABASE_CONTAINER.getMappedPort(5432).toString());
        System.setProperty("DB_USER", "it-test-user");
        System.setProperty("DB_PASS", "it-test-password");
        System.setProperty("REDIS_PORT", REDIS_CONTAINER.getMappedPort(6379).toString());
    }

}
