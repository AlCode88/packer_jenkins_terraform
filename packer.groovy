properties([
    parameters([
        choice(choices: ['dev', 'qa', 'prod'], description: 'Select environment to apply', name: 'env'), 
    ])
])

if(params.env == 'dev'){
    execute="packer.json"
}
else if(params.env == 'qa'){
    execute="packer_2.json"
}
else{
    execute="packer_3.json"
}


node('worker1'){
    stage('Install Apache'){
        sh '''
            git pull -u origin main
            ls
        '''
    }
    withEnv(['REGION=us-east-1', 'PACKER_AMI_NAME=packerimage']) {
        withCredentials([usernamePassword(credentialsId: 'aws-jenkins-user', passwordVariable: 'AWS_SECRET_ACCESS_KEY', usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
            //stage('Validate Packer'){
            //    sh '''
            //        packer validate $execute
            //    '''
            //}
            stage('Pcker Build'){
                sh '''
                packer build $execute
                '''
            }
        }
    }
}
