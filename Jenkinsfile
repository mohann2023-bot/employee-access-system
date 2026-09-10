pipeline {
    agent any
    
    stages {
        stage('Compile Project') {
            steps {
                // Changing 'sh' to 'bat' makes it run correctly on Windows servers
                bat 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
