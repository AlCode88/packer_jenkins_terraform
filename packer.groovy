properties([
    parameters([
        choice(choices: ['dev', 'qa', 'prod'], description: 'Select environment to apply', name: 'env'), 
    ])
])

if(params.env == 'dev'){
    environment="dev"
}
else if(params.env == 'qa'){
    environment="qa"
}
else{
    environment="prod"
}


node('worker1'){
    stage('Install Apache'){
        sh '''
            git pull -u origin main
            ls
        '''
    }
    withEnv(['REGION=us-east-1', 'PACKER_AMI_NAME=$environment_packerimage']) {
        withCredentials([usernamePassword(credentialsId: 'aws-jenkins-user', passwordVariable: 'AWS_SECRET_ACCESS_KEY', usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
            stage('Validate Packer'){
                sh '''
                   packer validate packer.json
                '''
            }
            stage('Packer Build'){
                sh '''
                   packer build packer.json
                '''
            }
        }
    }
}
