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
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker image prune"
                    echo "yes"
                    dockerImage = docker.build('awesomeproject:lastest')
                }
            }
        }

        stage('Start Docker Container') {
            steps {
                script {
                    try {
                        bat "docker stop awesomeproject"
                        bat "docker rm awesomeproject"
                    }
                    catch (Exception e) {
                        echo "404 Not found : awesomeproject not found"
                    }
                    bat "docker run --name awesomeproject -d -p 33470:8080 awesomeproject:lastest awesome-project.jar"
                }
            }
        }
    }
}