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
                sh "pwd; ls -l; echo ${env.WORKSPACE}"
            }
        }
        stage('Test2') {
            steps {
                sh "ls -l /workspace; touch /workspace/kenny.txt"
            }
        }
        stage('Test3') {
            steps {
                sh "ls -l /workspace;"
                allure([
                  includeProperties: false,
                  jdk: '',
                  results: [[path: '/']]
                ])
            }
        }
    }
}