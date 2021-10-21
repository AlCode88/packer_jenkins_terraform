node('worker1'){
    stage('Install Apache'){
        sh '''
            ls
            git pull -u origin main
        '''
    }
    withEnv(['REGION=us-east-1', 'PACKER_AMI_NAE=packerimage1']) {
        withCredentials([usernamePassword(credentialsId: 'aws-jenkins-user', passwordVariable: 'AWS_SECRET_ACCESS_KEY', usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
            stage('List all the files'){
                sh '''
                    packer validate packer.json
                '''
            }
        }
    }
}
