node("worker1"){
    stage('Install yum utils'){
        sh """
           yum install -y yum-utils
        """
    }
    //stage('Download Packer'){
    //    sh """
    //       wget https://releases.hashicorp.com/packer/1.7.7/packer_1.7.7_linux_amd64.zip
    //       ls
    //    """
    //}
    //stage('Unzip Packer'){
    //    sh """
    //       unzip packer_1.7.7_linux_amd64.zip
    //       ls
    //    """
    //}
    stage('Remove packer zip file'){
        sh """
           rm -rf packer*.zip.*
           ls
        """
    }
    stage('Remove packer zip file'){
        sh """
           mv packer /usr/bin/
           ls
        """
    }
    stage('Check packer version'){
        sh """
           packer version
        """
    }
}
