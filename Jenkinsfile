pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Validate Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run UI Tests - Chrome') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn clean test -Dtest=UiTestRunner -Dbrowser=chrome'
                }
            }
        }

        stage('Run UI Tests - Firefox') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn clean test -Dtest=UiTestRunner -Dbrowser=firefox'
                }
            }
        }

        stage('Run API Tests - Karate') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn clean test -Dtest=ApiTestRunner'
                }
            }
        }
    }

    post {
        always {
            echo 'Archiving reports and screenshots...'

            archiveArtifacts artifacts: 'reports/**, screenshots/**, target/karate-reports/**, target/reports/**', allowEmptyArchive: true

            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }

        success {
            echo 'Pipeline finished successfully.'
        }

        unstable {
            echo 'Pipeline finished with unstable status. This is expected if the intentional failure test was executed.'
        }

        failure {
            echo 'Pipeline failed. Review reports and screenshots.'
        }
    }
}
