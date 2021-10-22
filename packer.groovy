properties([
    parameters([
        choice(choices: ['dev', 'qa', 'prod'], description: 'Select environment to apply', name: 'env'),
        choice(choices: ['us-east-1', 'us-east-2'], description: 'Select region', name: 'region') 
    ])
])


if(params.env == 'dev'){
    environment="devPackerImage"
}
else if(params.env == 'qa'){
    environment="qaPackerImage"
}
else{
    environment="prodPackerImage"
}

if(params.region == 'us-east-1'){
    region="us-east-1"
}
else {
   region="us-east-2"
}

//image_name=$params.env-packer-{timestamp}

node('worker1'){
    stage('Install Apache'){
        sh '''
            git pull -u origin main
            ls
        '''
    }
    withEnv(["REGION=$region", "PACKER_AMI_NAME=$environment"]) {
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
