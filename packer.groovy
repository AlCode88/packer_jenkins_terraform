node('worker1'){
    stage('Install Apache'){
        sh '''
            ls
            git pull -u origin main
        '''
    }
    withEnv(['REGION=us-east-1', 'PACKER_AMI_NAME=packerimage-{{timestamp}}']) {
        withCredentials([usernamePassword(credentialsId: 'aws-jenkins-user', passwordVariable: 'AWS_SECRET_ACCESS_KEY', usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
            stage('Validate Packer'){
                sh '''
                    packer validate packer.json
                '''
            }
            stage('Validate built'){
                sh '''
                    packer build packer.json
                '''
            }
        }
    }
}
