pipeline {
    agent any

    tools {
        maven "Maven 3.9.6"
    }

    stages {
        stage('Hello') {
            steps {
                script {
                    echo 'Hello world!'
                }
            }
        }

        stage('Maven Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build('awesomeproject:lastest')
                }
            }
        }
    }
}