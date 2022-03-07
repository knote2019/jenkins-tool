
env.CI_CUDA_DOCKER_IMAGE = params.CI_CUDA_DOCKER_IMAGE.trim()
env.CI_COREX_DOCKER_IMAGE = params.CI_COREX_DOCKER_IMAGE.trim()

env.CI_CUDA_HOST_LABEL = params.CI_CUDA_HOST_LABEL.trim()
env.CI_COREX_HOST_LABEL = params.CI_COREX_HOST_LABEL.trim()

pipeline {
    agent { node { label "${CI_CUDA_HOST_LABEL}" } }

    stages {
        stage('read jenkins config') {
            steps {
                script {
                    printParams()
                    sh """
                        echo "CI_CUDA_DOCKER_IMAGE=${CI_CUDA_DOCKER_IMAGE}"
                        echo "CI_COREX_DOCKER_IMAGE=${CI_COREX_DOCKER_IMAGE}"

                        echo "CI_CUDA_HOST_LABEL=${CI_CUDA_HOST_LABEL}"
                        echo "CI_COREX_HOST_LABEL=${CI_COREX_HOST_LABEL}"
                    """
                }
            }
        }

        stage('CUDA'){
            agent { node { label "${CI_CUDA_HOST_LABEL}" } }
            when { expression { params.gen_ixblas_golden == true } }
            steps{
                script{
                    def dockerImage = docker.image(env.CI_CUDA_DOCKER_IMAGE)
                    dockerImage.pull()
                    dockerImage.inside("--hostname ci_gen_ixblas_golden --network host -v ${env.WORKSPACE}:/workspace --pid=host --privileged --cap-add=ALL") {
                        sh """
                            echo "test cuda"
                        """
                    }
                }
            }
        }

        stage('COREX'){
            agent { node { label "${CI_COREX_HOST_LABEL}" } }
            when { expression { params.run_ixblas_test == true } }
            stages {
                stage('TEST') {
                    steps {
                        script{
                            def dockerImage = docker.image(env.CI_COREX_DOCKER_IMAGE)
                            dockerImage.pull()
                            dockerImage.inside("--hostname ci_run_ixblas_test --network host -v ${env.WORKSPACE}:/workspace --pid=host --privileged --cap-add=ALL") {
                                sh """
                                    echo "test corex"
                                """
                            }
                        }
                    }
                }
                stage('REPORT') {
                    steps {
                        script{
                            sh """
                                cd ${env.WORKSPACE}
                                rm -rf test_report
                                mkdir test_report
                                mv test_report.xml test_report
                                ls -l
                                ls -l test_report
                            """
                            allure([
                                includeProperties: false,
                                jdk: '',
                                results: [[path: 'test_report']]
                            ])
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                sh """
                    echo "=================================================="
                    hostname
                    pwd; ls -l; ip a | grep 'inet 10';
                    echo "=================================================="
                """
                cleanWs()
                log.blue('Finish')
            }
        }
    }

}
