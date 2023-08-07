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

        stage('maven install'){
            
            steps{
                
                script{
                   
                    sh 'mvn clean install -DskipTests'
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
                    def filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    artifactPath = filesByGlob[0].path;

                    
                    nexusArtifactUploader artifacts:
                        [
                            [
                             artifactId: 'demo', 
                             classifier: '', 
                             file: 'artifactPath', 
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
