@Library('farahkhan10-library@master') _

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

