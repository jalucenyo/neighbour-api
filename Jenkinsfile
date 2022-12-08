pipeline {

  agent {
    kubernetes {
      defaultContainer 'openjdk'
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

          - name: couchbase
            image: couchbase:community-6.5.0
            tty: true

        '''
    }
  }

  stages {

    stage("Compile") {
      steps {
        sh "mvn clean compile package -DskipTests"
      }
    }

    stage("Tests") {
      steps {
        sh "mvn test"
      }
    }

  }

}




