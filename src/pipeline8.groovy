podTemplate(
    cloud: "kubernetes",
    namespace: "default",
    name: "jenkins-pod-slave-1",
    label: "jenkins-pod-slave-1",
    yaml: """
apiVersion: v1
kind: Pod
spec:
    hostNetwork: true
    containers:
    - name: maven
      image: 10.150.9.98:80/devops_tools/jenkins-agent:master
      command: ['cat']
      tty: true
      securityContext:
        privileged: true
      volumeMounts:
      - name: nfs-stores
        mountPath: /stores
        readOnly: true
    volumes:
    - name: nfs-stores
      hostPath:
      path: /stores
""",
) {
    node("jenkins-pod-slave-1") {
        container("maven") {
            stage("Checkout") {
                sh 'ls -l /stores'
            }
            stage("Build") {
                sh 'ls -l /stores'
            }
        }
    }
}
