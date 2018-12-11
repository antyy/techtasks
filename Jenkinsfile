pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                dir('soccer-manager'){
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
        stage('Test') {
                    steps {
                        dir('soccer-manager'){
                            sh 'mvn test'
                        }
                    }
                    post {
                        always {
                            junit 'soccer-manager/target/surefire-reports/*.xml'
                        }
                    }
           }
    }
}