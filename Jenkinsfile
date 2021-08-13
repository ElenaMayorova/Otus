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
            string(name: 'EMAIL_NOTIFICATION', defaultValue: 'otuslogintest@gmail.com', description: 'default email')
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
                archiveArtifacts artifacts: 'target/**/*.*', fingerprint: true
            }
            post {
                always {
                    script {
                    // Узнаем ветку репозитория
                                          def branch = bat(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD\n').trim().tokenize().last()
                                          println("branch= " + branch)
                    // Достаем информацию по тестам из junit репорта
                                          def summary = junit testResults: '**/target/surefire-reports/*.xml'
                                          println("summary generated")
                    // Текст оповещения
                                          def message = "${currentBuild.currentResult}: Job ${env.JOB_NAME}, build ${env.BUILD_NUMBER}, branch ${branch}\nTest Summary - ${summary.totalCount}, Failures: ${summary.failCount}, Skipped: ${summary.skipCount}, Passed: ${summary.passCount}\nMore info at: ${env.BUILD_URL}"
                                           println("message= " + message)


 //            def emailMessage = "${currentBuild.currentResult}: Job '${env.JOB_NAME}', Build ${env.BUILD_NUMBER}, Branch ${branch}. \nPassed time: ${currentBuild.durationString}. \n\nTESTS:\nTotal = ${summary.totalCount},\nFailures = ${summary.failCount},\nSkipped = ${summary.skipCount},\nPassed = ${summary.passCount} \n\nMore info at: ${env.BUILD_URL}"
 //  println ("email message=" + emailMessage )
//    emailext(
//        subject: "Jenkins Report",
//        body: emailMessage,
 //       to: "otuslogintest@gmail.com",
 //       from: "jenkins@code-maven.com"    )

    def colorCode = '#FF0000'
    if (currentBuild.currentResult == 'SUCCESS') {
        colorCode = '#00FF00'
    }
    def slackMessage = "${currentBuild.currentResult}: Job '${env.JOB_NAME}', Build ${env.BUILD_NUMBER}. \nPassed time: ${currentBuild.durationString}. \n\nTESTS:\nTotal = ${summary.totalCount},\nFailures = ${summary.failCount},\nSkipped = ${summary.skipCount},\nPassed = ${summary.passCount} \n\nMore info at: ${env.BUILD_URL}"
    slackSend(color: colorCode, message: slackMessage)
// if (currentBuild.currentResult == 'SUCCESS') {
 //                                                                 step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "otuslogintest@gmail.com", sendToIndividuals: true])
 //                                                                 } else {
  //                                                               step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "otuslogintest@gmail.com", sendToIndividuals: true])
 //                                                                        }


                    // Формирование отчета allure
                        println("Generate Allure")
                        allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']]
                        ])
                       println('allure report created')




    }
                }
 failure {
             mail to: 'otuslogintest@gmail.com', from: 'jenkins@example.com',
                 subject: "Example Build: ${env.JOB_NAME} - ${currentBuild.currentResult}",
                 body: "Job  - \"${env.JOB_NAME}\" build: ${env.BUILD_NUMBER}\n\nView the log at:\n ${env.BUILD_URL}\n\nBlue Ocean:\n${env.RUN_DISPLAY_URL}"
                }
}

            }
        }
    }
 }
