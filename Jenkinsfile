node("worker1"){
    stage('Install yum utils'){
        sh """
           yum install -y yum-utils
        """
    }
    stage('Install packer'){
        sh """
           yum install packer -y
        """
     }
}
