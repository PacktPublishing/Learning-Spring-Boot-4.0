package com.learningspringboot4;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.postgresql.PostgreSQLContainer;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
class VideoRepositoryTestcontainersTest {

  @Autowired
  VideoRepository repository;

  @Container
  @ServiceConnection
  static final PostgreSQLContainer database =
          new PostgreSQLContainer(DockerImageName.parse("postgres:17-alpine"))
                  .withDatabaseName("testdb")
                  .withUsername("postgres")
                  .withPassword("postgres");

  @BeforeEach
  void setUp() {
    repository.saveAll(List.of(
            new VideoEntity("alice",
                    "Need HELP with your SPRING BOOT 4 App?",
                    "SPRING BOOT 4 will only speed things up."),
            new VideoEntity("alice",
                    "Don't do THIS to your own CODE!",
                    "As a pro developer, never ever EVER do this to your code."),
            new VideoEntity("bob",
                    "SECRETS to fix BROKEN CODE!",
                    "Discover ways to not only debug your code")
    ));
  }

  @Test
  void findAllShouldProduceAllVideos() {
    assertThat(repository.findAll()).hasSize(3);
  }

  @Test
  void findByName() {
    assertThat(repository.findByNameContainsIgnoreCase("SPRING BOOT 4")).hasSize(1);
  }

  @Test
  void findByNameOrDescription() {
    assertThat(repository.findByNameContainsOrDescriptionContainsAllIgnoreCase("CODE", "your code"))
            .hasSize(2);
  }
}