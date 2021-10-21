node('worker1'){
    stage('Install Apache'){
        sh '''
            git pull -u origin main
            sh 'ls'
        '''
    }
}