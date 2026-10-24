pipeline {
    agent any

    tools {
        maven 'Maven-3.10'
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
    }

    post {
        success {
            echo 'Java build and tests passed!'
        }
        failure {
            echo 'Build or tests failed. Check the console output.'
        }
    }
}
