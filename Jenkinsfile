pipeline{
    
    agent any 
    
    stages {
        
        stage('Git Checkout'){
            
            steps{
                
                script{
                    
                    git branch: 'spring', credentialsId: 'github', url: 'https://github.com/ousamabouaziz/SpringBoot.git'
                }
            }
        }

           stage('Maven validation'){
            
            steps{
                
                script{
                    
                    sh 'mvn validate'
                }
            }
        }




         stage('Maven compilation'){
            
            steps{
                
                script{
                    
                    sh 'mvn compile'
                }
            }
        }


        stage('maven testing'){
            
            steps{
                
                script{
                   
                    sh 'mvn test -DskipTests'
                }
            }
        }

        
        


        stage("Sonarqube Analysis") {
            steps {
                script {
                    
                    withSonarQubeEnv(credentialsId: 'sonarqube') {
                        sh "mvn clean package -DskipTests sonar:sonar"
                    }
                }
            }

        }

       

        

        stage("Uploader to Nexus") {
            steps {
                script {
                    def pom = readMavenPom file: "pom.xml";
                    
                    nexusArtifactUploader artifacts:
                        [
                            [
                             artifactId: 'demo', 
                             classifier: '', 
                             file: 'target/demo.jar', 
                             type: 'jar'
                            ]
                        ],
                        credentialsId: 'nexus',
                        groupId: 'com.example', 
                        nexusUrl: 'localhost:8081/', 
                        nexusVersion: 'nexus3', 
                        protocol: 'http', 
                        repository: 'springboot-release', 
                        version: '$(pom.version)'
                    
                }
            }

        }



        


      
        }
        
}
