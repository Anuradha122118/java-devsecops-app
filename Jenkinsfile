pipeline {
    agent any

    tools {
        maven 'Maven-3.10'
    }

    environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN')
        SONAR_PROJECT_KEY = 'Anuradha122118_java-devsecops-app'
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
    }

    post {
        success {
            echo 'Build, tests, and SonarQube analysis completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check Console Output for details.'
        }
    }
}
