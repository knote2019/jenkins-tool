...
stage('GTEST: test report'){
    agent { node { label "${CUDA_HOST_LABEL}" } }
    when { expression { params.run_cuda_test == true } }
    steps{
        script{
            sh """
                cd ${env.WORKSPACE}
                rm -rf runtime_result
                mkdir runtime_result
                mv ___*.xml runtime_result
            """
            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'runtime_result']]
            ])
        }
    }
}
...