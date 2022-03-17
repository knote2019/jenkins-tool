podTemplate(
    cloud: "kubernetes",
    namespace: "default",
    name: "jenkins-pod-slave-1",
    label: "jenkins-pod-slave-1",
    yaml: readTrusted('pod.yaml'),
) {
    node("jenkins-pod-slave-1") {
        container("maven") {
            stage("install") {
                sh 'ls -l /stores'
            }
            stage("test") {
                sh 'ls -l /stores'
            }
            stage("report") {
                sh 'ls -l /stores'
            }
        }
    }
}
