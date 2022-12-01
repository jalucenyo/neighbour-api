package com.lucenyo.neighbour

import com.fasterxml.jackson.databind.ObjectMapper
import com.lucenyo.neighbour.people.application.out.PersonRepository
import com.lucenyo.neighbour.people.domain.Person
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.access.SecurityConfig
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.ResourceUtils
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.couchbase.BucketDefinition
import org.testcontainers.couchbase.CouchbaseContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName
import java.util.*


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = [SecurityConfig::class])
abstract class DatabaseBaseTest {

  @Autowired
  protected lateinit var context: WebApplicationContext
  @Autowired
  protected lateinit var objectMapper: ObjectMapper

  protected lateinit var mockMvc: MockMvc

  @Autowired
  protected lateinit var personRepository: PersonRepository


  @BeforeEach
  fun setup() {
    mockMvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply<DefaultMockMvcBuilder>(springSecurity())
      .build()

  }

  companion object {

    @Container
    val container = CouchbaseContainer(DockerImageName.parse("couchbase/server")).apply {
      withCredentials("Administrator", "12345678")
      withBucket(BucketDefinition("testbucket"))
      start()
    }

    @JvmStatic
    @DynamicPropertySource
    fun properties(registry: DynamicPropertyRegistry) {
      registry.add("spring.couchbase.connection-string") { container.connectionString }
      registry.add("spring.couchbase.username") { container.username }
      registry.add("spring.couchbase.password") { container.password }
      registry.add("spring.data.couchbase.bucket-name"){ "testbucket" }
    }

//    val container = PostgreSQLContainer(DockerImageName.parse("postgres:12")).apply {
//      withDatabaseName("neighbour")
//      withUsername("postgres")
//      withPassword("postgres")
//      start()
//    }

//    @JvmStatic
//    @DynamicPropertySource
//    fun properties(registry: DynamicPropertyRegistry) {
//      registry.add("spring.datasource.url", container::getJdbcUrl);
//      registry.add("spring.datasource.password", container::getPassword);
//      registry.add("spring.datasource.username", container::getUsername);
//    }

  }

  @Test
  fun `container database is running`(){
    Assertions.assertTrue(container.isRunning);
  }

  fun registerUser(): Pair<String, OAuth2User> {

    val firstName = RandomStringUtils.randomAlphabetic(15)
    val lastName = RandomStringUtils.randomAlphabetic(15)
    val userId = "${firstName}_${lastName}"
    val id = UUID.randomUUID()

    val userOauth = DefaultOAuth2User(
      AuthorityUtils.createAuthorityList("SCOPE_message:read"),
      mapOf(Pair("user_name", userId)),
      "user_name")

    personRepository.create(Person(
      id = id,
      firstName = firstName,
      lastName = lastName,
      userId = userId
    ))

    return Pair(id.toString(), userOauth)
  }

  protected fun readFile(name: String): String {
    return ResourceUtils.getFile(javaClass.classLoader.getResource(name)!!).readText()
  }

  protected fun toJsonString(obj: Any): String {
    return objectMapper.writeValueAsString(obj);
  }

}
