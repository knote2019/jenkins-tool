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
                            image "node:16.13.1-alpine"
                        }
                    }
                    stages {
                        stage('Test1') {
                            steps {
                                sh "echo -e '\E[1;31m Test1';pwd; ls -l"
                            }
                        }
                        stage('Test2') {
                            steps {
                                sh "echo 'Test2';pwd; ls -l"
                            }
                        }
                        stage('Test3') {
                            steps {
                                sh "echo 'Test3';pwd; ls -l"
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
                            image "node:16.13.1-alpine"
                        }
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
                                sh "echo 'Test3';pwd; ls -l"
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