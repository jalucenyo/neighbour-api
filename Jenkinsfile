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
            env:
              - name: NODE_TYPE
                value: DEFAULT
              - name: CLUSTER_USERNAME
                value: Administrator
              - name: CLUSTER_PASSWORD
                value: 12345678
              - name: CLUSTER_NAME
                value: Testing
              - name: SERVICES
                value: data,index,query,fts
              - name: CLUSTER_RAMSIZE
                value: 256
              - name: BUCKET
                value: test
              - name: BUCKET_RAMSIZE
                value: 128
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




