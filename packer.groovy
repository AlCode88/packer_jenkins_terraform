node('worker1'){
    stage('Install Apache'){
          git 'https://github.com/AlCode88/packer_jenkins_terraform.git'
        sh 'ls'
    }
}