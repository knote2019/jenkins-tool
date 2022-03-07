pipeline {
    agent {
        docker { image 'node:16.13.1-alpine' }
    }
    stages {
        stage('Test1') {
            steps {
                sh 'node --version'
            }
        }
        stage('Test2') {
            steps {
                sh 'node --version'
            }
        }
        stage('Test3') {
            steps {
                sh 'node --version'
            }
        }
    }
}