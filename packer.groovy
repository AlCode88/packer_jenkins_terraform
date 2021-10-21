node('worker1'){
    stage('Install Apache'){
        sh '''
            ls
            git pull -u origin main
        '''
    }
}