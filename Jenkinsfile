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


        stage('UNIT testing'){
            
            steps{
                
                script{
                   
                    sh 'mvn test -DskipTests'
                }
            }
        }
        


        stage('Maven build'){
            
            steps{
                
                script{
                    
                    sh 'mvn compile'
                }
            }
        }

         stage('Maven package'){
            
            steps{
                
                script{
                    
                    sh 'mvn package -DskipTests'
                }
            }
        }

        stage("Sonarqube Analysis") {
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'sonarqube') {
                        sh "mvn sonar:sonar"
                    }
                }
            }

        }

        stage("Quality Gate") {
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonarqube'
                }
            }

        }




      
        }
        
}
