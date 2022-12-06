pipeline {

  agent {
      docker { image 'maven:3.8-openjdk-18' }
  }

  stages {

    stage("Clone the project") {
      git branch: 'master', url: 'https://github.com/jalucenyo/neighbour-api.git'
    }

    stage("Compile") {
      sh "./mvnw clean install -DskipTests"
    }

    stage("Tests") {
      steps ("Running tests") {
        sh "./mvnw test "
      }
    }

  }

}