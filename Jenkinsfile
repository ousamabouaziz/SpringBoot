pipeline {

  agent any
  
  stages {
    stage('Checkout') {
      steps {
        sh 'echo passed'
        //git branch: 'spring', url: 'https://github.com/ousamabouaziz/SpringBoot.git'
      }
    }
    stage('Build and Test') {
      steps {
        
        sh 'mvn validate'
        sh 'mvn test'
        sh 'mvn verify -DskipUnitTests'
        sh 'mvn clean package'
      }
    }
    stage('Static Code Analysis') {
      environment {
        SONAR_URL = "http://localhost:9000"
      }
      steps {
        withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_AUTH_TOKEN')]) {
          sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
        }
      }
    }
    stage('Build and Push Docker Image') {
      environment {
        DOCKER_IMAGE = "oussema/springtest1 :${BUILD_NUMBER}"
        // DOCKERFILE_LOCATION = "/Dockerfile"
        REGISTRY_CREDENTIALS = credentials('docker-cred')
      }
      steps {
        script {
            sh 'docker build -t ${DOCKER_IMAGE} .'
            def dockerImage = docker.image("${DOCKER_IMAGE}")
            docker.withRegistry('https://index.docker.io/v1/', "docker-cred") {
                dockerImage.push()
            }
        }
      }
    } 
  }
}
