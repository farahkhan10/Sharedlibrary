def call(string createPipeline) {
    pipeline {
	    agent any
	    environment {
	      username = 'fkhan'
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
}  

	
