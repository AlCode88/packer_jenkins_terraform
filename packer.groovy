properties([
    parameters([
        choice(choices: ['dev', 'qa', 'prod'], description: 'Select environment to apply', name: 'env')
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

//image_name=$params.env-packer-{timestamp}

node('worker1'){
    stage('Install Apache'){
        sh '''
            git pull -u origin main
            ls
        '''
    }
    withEnv(["REGION=us-east-1", "PACKER_AMI_NAME=$environment"]) {
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
    stage('Create Instance'){
        build job: 'terrafrom-ec2', parameters: [
            string(name: 'env', value: "$params.env"), 
            booleanParam(name: 'checkmark', value: true)
        ]
}
