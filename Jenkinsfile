node("worker1"){
    stage('Install yum utils'){
        sh """
           yum install -y yum-utils
        """
    }
    stage('Install packer'){
        sh """
           wget https://releases.hashicorp.com/packer/1.7.7/packer_1.7.7_linux_amd64.zip
           ls
        """
     }
}
