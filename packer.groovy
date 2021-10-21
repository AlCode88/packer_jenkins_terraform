node('worker1'){
    stage('Install Apache'){
        sh '''
            ls
            git pull -u origin main
        '''
    }
    stage('List all the files'){
        sh '''
            ls
            git pull -u origin main
        '''
    }
}