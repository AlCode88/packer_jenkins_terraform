node("worker1"){
    stage ("Workspace cleanup") {
        deleteDir()
        sh 'ls -al'
    }
    stage('Remove Packer on CentOS'){
        sh """
            rm -rf /usr/sbin/packer
        """
    }
    stage('Remove Packer on CentOS'){
        sh """
            packer
        """
    }
}
