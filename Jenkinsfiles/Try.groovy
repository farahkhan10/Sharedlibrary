@Library('jenkins-library') _
pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                HelloWorld 'testing'
            }
        }
    }
}
