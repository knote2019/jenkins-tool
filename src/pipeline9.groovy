pipeline {
  agent {
    kubernetes {
      cloud 'kubernetes'
      label 'mypod'
      yaml """
apiVersion: v1
kind: Pod
spec:
  hostNetwork: true
  containers:
  - name: maven
    image: maven:3.3.9-jdk-8-alpine
    command: ['cat']
    tty: true
    volumeMounts:
  　- name: stores
      mountPath: /stores
  volumes:
  - name: stores
    hostPath:
      path: /stores
      type: DirectoryOrCreate
"""
    }
  }
  stages {
    stage('Run maven') {
      steps {
        container('maven') {
          sh 'mvn -version; ls -l /stores'
        }
      }
    }
  }
}
