node("worker2"){
    stage('create shell script'){
        sh """
            touch hello.sh
        """
    }
    stage('write to the file echo command'){
        sh """
            echo 'df -h'>hello.sh
        """
    }
    stage('list files'){
        sh """
            ls -la
        """
    }
    stage('cat hello.sh'){
        sh """
            cat hello.sh
        """
    }
    stage('make file executable script'){
        sh '''
            chmod +x hello.sh
        '''
    }
    stage('list the files'){
        sh '''
           ls -la
        '''
    }
    stage('execute hello.sh command'){
        sh '''
           ./hello.sh
           ls
        '''
    }
}