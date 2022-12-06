node {
  stage("Clone the project") {
    git branch: 'master', url: 'https://github.com/jalucenyo/neighbour-api.git'
  }

  stage("Compile") {
    sh "./mvnw clean install -DskipTests"
  }

  stage("Tests") {
    stage("Running tests") {
      sh "./mvnw test "
    }

  }
}