pipeline {
    agent any
    
    stages {
        stage('Compile Project') {
            steps {
                // This builds the project structure and downloads necessary parts
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                // This runs the JUnit verification tests you wrote earlier
                sh 'mvn test'
            }
        }
    }
}
