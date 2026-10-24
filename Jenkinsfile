pipeline {
    agent any

    tools {
        maven 'Maven-3.10'
    }

    environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN')
        SONAR_PROJECT_KEY = 'Anuradha122118_java-devsecops-app'
        DOCKER_IMAGE = 'java-devsecops-app'
        TRIVY_IMAGE = 'aquasec/trivy:0.76.1'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'feature/update-message',
                    url: 'https://github.com/Anuradha122118/java-devsecops-app.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                sh '''
                    mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                    -Dsonar.projectKey=$SONAR_PROJECT_KEY \
                    -Dsonar.organization=anuradha122118 \
                    -Dsonar.token=$SONAR_TOKEN
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                    docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .
                    docker images ${DOCKER_IMAGE}
                '''
            }
        }

        stage('Trivy Image Scan') {
            steps {
                sh '''
                    docker run --rm \
                      -v /var/run/docker.sock:/var/run/docker.sock \
                      -v trivy-cache:/root/.cache/ \
                      ${TRIVY_IMAGE} image \
                      --scanners vuln \
                      --severity HIGH,CRITICAL \
                      --exit-code 1 \
                      ${DOCKER_IMAGE}:${BUILD_NUMBER}
                '''
            }
        }
    }

    post {
        success {
            echo 'Build, tests, SonarQube analysis, Docker build, and Trivy scan completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check Console Output for details.'
        }
    }
}
