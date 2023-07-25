pipeline{
    
    agent any 
    
    stages {
        
        stage('Git Checkout'){
            
            steps{
                
                script{
                    
                    git branch: 'spring', url: 'https://github.com/ousamabouaziz/SpringBoot.git'
                }
            }
        }


        stage('UNIT testing'){
            
            steps{
                
                script{

                    sh 'mvn clean install'
                    sh 'mvn test'
                }
            }
        }
        stage('Integration testing'){
            
            steps{
                
                script{
                    
                    sh 'mvn verify -DskipUnitTests'
                }
            }
        }




      
        }
        
}
