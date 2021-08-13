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
        EMAIL_TO = 'otuslogintest@gmail.com'
    }
    parameters {
        string(name: 'GIT_URL', defaultValue: 'https://github.com/ElenaMayorova/Otus.git', description: 'The target git url')
        string(name: 'GIT_BRANCH', defaultValue: 'master', description: 'The target git branch')
        string(name: 'EMAIL_RECIPIENT', defaultValue: 'otuslogintest@gmail.com', description: 'Default recipient')
        choice(name: 'BROWSER_NAME', choices: ['chrome', 'firefox'], description: 'Pick the target browser in Selenoid')
        choice(name: 'BROWSER_VERSION', choices: ['86.0', '92.0', '85.0'], description: 'Pick the target browser version in Selenoid')
    }

    stages {
        stage('Pull from GitHub') {
            steps {
                slackSend(message: "Jenkins in progress ...")
                git ([
                        url: "${params.GIT_URL}",
                        branch: "${params.GIT_BRANCH}"
                ])
            }
        }
        stage('Run maven clean test') {
            steps {
            slackSend(message: "Run maven clean test...")
                sh 'mvn clean test -Dtest=Lesson7 -Dbrowser_name=$BROWSER_NAME -Dbrowser_version=$BROWSER_VERSION'
            }
        }
        stage('Backup and Reports') {
            steps {
            slackSend(message: "Backup and Reports...")
                archiveArtifacts artifacts: '**/target/', fingerprint: true
            }
            post {
                always {
                    script {

                        // Формирование отчета
                        allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'target/allure-results']]
                        ])
                        println('allure report created')

                        // Узнаем ветку репозитория
                        def branch = sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD\n').trim().tokenize().last()
                        println("branch= " + branch)

                        // Достаем информацию по тестам из junit репорта
                        def summary = junit testResults: '**/target/surefire-reports/*.xml'
                        println("summary generated")

                        // Текст оповещения
                        def message = "${currentBuild.currentResult}: Job ${env.JOB_NAME}, build ${env.BUILD_NUMBER}, branch ${branch}\nTest Summary - ${summary.totalCount}, Failures: ${summary.failCount}, Skipped: ${summary.skipCount}, Passed: ${summary.passCount}\nMore info at: ${env.BUILD_URL}\nElapsed: ${currentBuild.durationString}"
                        println("message= " + message)
                        slackSend color: 'good', message: message

                        emailext body: '''${SCRIPT, template="groovy-html.template"}''',
                                mimeType: 'text/html',
                                subject: "[Jenkins] ${currentBuild.fullDisplayName}",
                                to: "${params.EMAIL_RECIPIENT}",
                                replyTo: "${params.EMAIL_RECIPIENT}",
                                recipientProviders: [[$class: 'CulpritsRecipientProvider']]
                    }
                }
            }
        }
    }
}

