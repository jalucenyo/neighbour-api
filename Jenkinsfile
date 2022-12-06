pipeline {

  agent { docker { image 'maven:3.8-openjdk-18' } }

  tools {
      maven 'maven3.8'
  }

  stages {

    stage("Compile") {
      steps ("Running tests") {
        sh "mvn clean install -DskipTests"
      }
    }

    stage("Tests") {
      steps ("Running tests") {
        sh "mvn test "
      }
    }

  }

}




