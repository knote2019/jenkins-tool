podTemplate(
    name: 'jenkins-slave'
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
    node("jenkins-slave") {
        container("runner") {
            stage("Checkout") {
                sh 'ls -l /stores'
            }
            stage("Build") {
                sh 'ls -l /stores'
            }
        }
    }
}
