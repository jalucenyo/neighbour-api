pipeline {

  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: openjdk
            image: maven:3.8-openjdk-18
            command:
            - cat
            tty: true
        '''
    }
  }

  stages {

    stage("Compile") {
      steps ("Running tests") {
        container('openjdk') {
          sh "mvn clean compile package -DskipTests"
        }
      }
    }

    stage("Tests") {
      steps ("Running tests") {
        container('openjdk') {
          sh "mvn test"
        }
      }
    }

  }

}




