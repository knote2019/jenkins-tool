pipeline {
    agent none
    stages {
        stage('host1'){
            agent { node { label "10.115.0.251" } }
            options {
                timestamps ()
                ansiColor('xterm')
            }
            stages {
                stage('host1 docker') {
                    agent {
                        docker {
                            image "node:16.13.1-alpine"
                        }
                    }
                    stages {
                        stage('Test1') {
                            steps {
                                sh "echo 'Test1'"
                            }
                        }
                        stage('Test2') {
                            steps {
                                sh "echo 'Test2'"
                            }
                        }
                        stage('Test3') {
                            steps {
                                sh "echo 'Test3'"
                            }
                        }
                    }
                }
            }
        }

        stage('host2'){
            agent { node { label "10.115.0.251" } }
            options {
                timestamps ()
                ansiColor('xterm')
            }
            stages {
                stage('host2 docker') {
                    agent {
                        docker {
                            image "node:16.13.1-alpine"
                        }
                    }
                    stages {
                        stage('Test1') {
                            steps {
                                sh "echo 'Test1'"
                            }
                        }
                        stage('Test2') {
                            steps {
                                sh "echo 'Test2'"
                            }
                        }
                        stage('Test3') {
                            steps {
                                sh "echo 'Test3'"
                            }
                        }
                    }
                }
            }
        }
    }
}