node("worker1"){
    stage ("Workspace cleanup") {
        deleteDir()
        sh 'ls -al'
    }
    stage('Check the packer version'){
        sh """
            packer version
        """
    }
    // new stage test
}
