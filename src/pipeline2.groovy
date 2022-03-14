pipeline {
    agent none
    stages {
        stage('host1'){
            agent { node { label "10.115.0.251" } }
            options {
                timestamps()
                ansiColor('xterm')
            }
            stages {
                stage('host1 host') {
                    steps {
                        sh "echo 'host1 host';pwd; ls -l"
                    }
                }

                stage('host1 docker') {
                    agent {
                        docker {
                            image "10.150.9.98:80/devops_tools/jenkins-agent:master"
                        }
                    }
                    options {
                        timestamps()
                        ansiColor('xterm')
                    }
                    stages {
                        stage('Test1') {
                            steps {
                                sh "echo 'Test1';pwd; ls -l"
                            }
                        }
                        stage('Test2') {
                            steps {
                                sh "echo 'Test2';pwd; ls -l"
                            }
                        }
                        stage('Test3') {
                            steps {
                                allure([
                                  includeProperties: false,
                                  jdk: '',
                                  results: [[path: '/']]
                                ])
                            }
                        }
                    }
                    post {
                        always {
                            echo 'host1 docker POST'
                        }
                    }
                }
            }
            post {
                always {
                    echo 'host1 POST'
                }
            }
        }

        stage('host2'){
            agent { node { label "10.115.0.251" } }
            options {
                timestamps()
                ansiColor('xterm')
            }
            stages {
                stage('host2 host') {
                    steps {
                        sh "echo 'host2 host';pwd; ls -l"
                    }
                }
                stage('host2 docker') {
                    agent {
                        docker {
                            image "10.150.9.98:80/devops_tools/jenkins-agent:master"
                        }
                    }
                    options {
                        timestamps()
                        ansiColor('xterm')
                    }
                    stages {
                        stage('Test1') {
                            steps {
                                sh "echo 'Test1';pwd; ls -l"
                            }
                        }
                        stage('Test2') {
                            steps {
                                sh "echo 'Test2';pwd; ls -l"
                            }
                        }
                        stage('Test3') {
                            steps {
                                allure([
                                  includeProperties: false,
                                  jdk: '',
                                  results: [[path: '/']]
                                ])
                            }
                        }
                    }
                    post {
                        always {
                            echo 'host2 docker POST'
                        }
                    }
                }
            }
            post {
                always {
                    echo 'host2 POST'
                }
            }
        }
    }
}