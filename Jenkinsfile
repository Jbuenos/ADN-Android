pipeline {
  agent {
    label 'Slave_Mac'
  }

  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
 	disableConcurrentBuilds()
  }

  tools {
    jdk 'JDK8_Mac'
  }

  stages{
  
    stage('Compile') {
      steps {
        echo "------------>Compile<------------"
	sh 'chmod +x ./gradlew'
    	sh './gradlew build -x test'

      }
    } 

    stage('Unit Tests') {
      steps{
        echo "------------>Unit Tests<------------"
	sh './gradlew clean'
        sh './gradlew test'
        sh './gradlew jacocoTestReport'
      }
    }

    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        withSonarQubeEnv('Sonar') {
sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
        }
      }
    } 
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
    }
    failure {
      echo 'This will run only if failed'
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}
