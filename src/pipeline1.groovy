pipeline {
    agent {
        docker {
            image "10.150.9.98:80/devops_tools/jenkins-agent:master"
            args "--entrypoint=''"
        }
    }
    options {
        timestamps()
    }
    stages {
        stage('Test1') {
            steps {
                sh "pwd; ls -l; node --version; echo ${env.WORKSPACE}"
            }
        }
        stage('Test2') {
            steps {
                sh "ls -l /workspace; node --version; touch /workspace/kenny.txt"
            }
        }
        stage('Test3') {
            steps {
                sh "ls -l /workspace; node --version"
                allure([
                  includeProperties: false,
                  jdk: '',
                  results: [[path: '/']]
                ])
            }
        }
    }
}