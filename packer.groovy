node('worker1'){
    stage('Install Apache'){
       sh '''
       git https://github.com/AlCode88/packer_jenkins_terraform.git
       ls
       ''' 
    }
}