import jenkins

jenkins_server = jenkins.Jenkins('http://10.150.9.97:8080',
                                 username='xxx',
                                 password='xxx')
user = jenkins_server.get_whoami()
version = jenkins_server.get_version()
print('Hello %s from Jenkins %s' % (user['fullName'], version))

views = jenkins_server.get_views()
nodes = jenkins_server.get_nodes()

job_name = 'build_kvm_qcow2_image'

lastCompletedBuild_number = jenkins_server.get_job_info(
    job_name)['lastCompletedBuild']['number']

job_info = jenkins_server.get_job_info(job_name)
build_info = jenkins_server.get_build_info(job_name, lastCompletedBuild_number)

print(job_info)
print(build_info)
