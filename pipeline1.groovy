pipeline {
    agent {
        docker {
            image "node:16.13.1-alpine"
            args "-v ${env.WORKSPACE}:/workspace"
        }
    }
    stages {
        stage('Test1') {
            steps {
                sh "pwd;ls -l;node --version;echo ${env.WORKSPACE}"
            }
        }
        stage('Test2') {
            steps {
                sh "ls -l /workspace;node --version"
            }
        }
        stage('Test3') {
            steps {
                sh "node --version"
            }
        }
    }
}