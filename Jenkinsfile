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
            volumeMounts:
             - mountPath: /var/run/docker.sock
               name: docker-sock

          volumes:
          - name: docker-sock
            hostPath:
              path: /var/run/docker.sock
        '''
    }
  }

  stages {

    stage("Compile") {
      steps {
        container('openjdk') {
          sh "mvn clean compile package -DskipTests"
        }
      }
    }

    stage("Tests") {
      steps {
        container('openjdk') {
          sh "mvn test"
        }
      }
    }

  }

}




