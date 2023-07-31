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




      
        }
        
}
