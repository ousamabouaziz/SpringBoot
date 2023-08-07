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

                    pom = readMavenPom file: "pom.xml";
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                    nexusArtifactUploader artifacts:
                        [
                            [
                                artifactId: 'demo', 
                             classifier: '', 
                             file: 'target/', 
                             type: 'jar'
                            ]
                        ],
                        credentialsId: 'nexus',
                        groupId: 'com.example', 
                        nexusUrl: 'localhost:8081/', 
                        nexusVersion: 'nexus3', 
                        protocol: 'http', 
                        repository: 'springboot-release', 
                        version: '0.0.1-SNAPSHOT'
                    
                }
            }

        }



        


      
        }
        
}
