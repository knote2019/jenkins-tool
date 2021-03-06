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
      image: 10.150.9.98:80/devops_tools/jenkins-agent:master
      command: ['cat']
      tty: true
      volumeMounts:
      - name: nfs-stores
        mountPath: /stores
        readOnly: true
    volumes:
    - name: nfs-stores
      hostPath:
        path: /stores
"""
        }
    }

    options {
        timestamps()
        skipDefaultCheckout()
    }

    stages {
        stage('install') {
            steps {
                container('maven') {
                    sh 'ls -l /stores'
                }
            }
        }
        stage('test') {
            steps {
                container('maven') {
                    sh 'ls -l /stores'
                }
            }
        }
        stage('report') {
            steps {
                container('maven') {
                    sh 'ls -l /stores'
                }
            }
        }
    }
}
