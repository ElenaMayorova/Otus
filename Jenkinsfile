pipeline {
    agent any
    tools {
        maven 'maven 3.6.3'
    }

    triggers {
        githubPush()
    }

    environment {
        LC_ALL = 'en_US.UTF-8'
        LANG    = 'en_US.UTF-8'
        LANGUAGE = 'en_US.UTF-8'
        EMAIL_TO = 'liliiashaikina3@gmail.com'
    }

    parameters {
        string(name: 'GIT_URL', defaultValue: 'https://github.com/ElenaMayorova/Otus.git', description: 'The target git url')
        string(name: 'GIT_BRANCH', defaultValue: 'jenkins', description: 'The target git branch')
        choice(name: 'BROWSER_NAME', choices: ['chrome', 'firefox'], description: 'Pick the target browser in Selenoid')
        choice(name: 'BROWSER_VERSION', choices: ['86.0', '85.0', '78.0'], description: 'Pick the target browser version in Selenoid')
    }

    stages {

        stage('Run maven clean test') {
            steps {
		// sh меняем на bat, если операционная система Windows
                sh 'mvn clean test -Dbrowser_name=$BROWSER_NAME -Dbrowser_version=$BROWSER_VERSION'
            }
        }

    }
}