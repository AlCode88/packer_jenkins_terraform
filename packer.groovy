node('worker1'){
    stage('Install Apache'){
        sh '''
            sh 'ls'
            git pull -u origin main
        '''
    }
}