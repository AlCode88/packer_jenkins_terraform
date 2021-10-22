properties([
    parameters([
        choice(choices: ['dev', 'qa', 'prod'], description: 'Select environment to apply', name: 'env'), 
        booleanParam(defaultValue: true, description: 'Checkmark to apply or uncheck will destroy', name: 'checkmark')
    ])
])

if(params.env == 'dev'){
    execute="packer build packer.json"
}
else if(params.env == 'qa'){
    execute="packer build packer_2.json"
}
else{
    execute="packer build packer_3.json"
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
                $execution
                '''
            }
        }
    }
}
