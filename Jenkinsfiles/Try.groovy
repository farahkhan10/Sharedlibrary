@Library('jenkins-library') _
pipeline {
  agent any
  environment {
    username = 'atayyab'
  }
  stages {
    stage('checkout') {
      steps {
            echo 'checkout'
            //echo username
      }
    }
    stage('find_changed') {
      /*agent {
        docker {
          image 'centos:latest'
        }
      }*/
      steps {
        echo 'find_changed'
        sh 'ls -la'
      }
    }
    stage('syntax_check') {
      parallel {
        stage('lint') {
          steps {
            echo 'Lint'
            sh 'ls -la'
            sh 'echo $username'
          }
        }
        stage('security_scan') {
          when {
            branch 'develop'
          }
          steps {
            echo "Security Scan"
          }
        }
      }
    }
    stage('unit_testing') {
      when {
        branch 'develop'
      }
      steps {
        echo "Unit Testing"
      }
    }
    stage('version') {
      when {
        branch 'develop'
      }
      steps {
        echo 'version'
      }
    }
    stage('package') {
      steps {
        echo 'build'
      }
    }
    stage('publish') {
      steps {
        echo "Push Artifacts"
      }
    }
    stage('docker_build') {
      parallel {
        stage('docker_lint') {
          steps {
            echo "Docker Lint"
          }
        }
        stage('image_build') {
          steps {
            echo "Docker Build"
          }           
        }
      }
    }
    stage('docker_tags') {
      steps {
        echo "Docker Tags"
      }
    }
    stage('helm_lint') {
      steps {
        echo "Helm Lint"
      }
    }
    stage('Deployment') {
      parallel {
        stage('Development') {
          when {
            branch 'develop'
          }
          steps {
            echo "Deploy Development Environment"
          }
        }
        stage('Production') {
          when {
            branch 'Production'
          }
          steps {
            echo "Deploy Production Environment"
          }
        }
        stage('developer') {
          steps {
            echo "Developer Code Execution"
          }
        }
      }
    }
  }     
}
