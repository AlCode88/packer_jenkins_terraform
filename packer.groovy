node('worker1'){
    stage('Install Apache'){
        sh '''
            git pull
            sh 'ls'
        '''
    }
}