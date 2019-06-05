pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        build 'mvn clean install'
      }
    }
    stage('test') {
      parallel {
        stage('test') {
          steps {
            unstable 'test'
          }
        }
        stage('') {
          steps {
            sleep 10
          }
        }
      }
    }
    stage('deploy') {
      steps {
        git(url: '11', branch: '21', changelog: true)
      }
    }
  }
}